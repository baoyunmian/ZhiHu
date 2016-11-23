package com.example.zhihudiary.pages;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zhihudiary.R;
import com.example.zhihudiary.activity.MainActivity;
import com.example.zhihudiary.activity.SettingActivity;

public abstract class BasePage {
	protected ImageButton	ibLogin;

	protected ImageButton	ibSetting;

	protected ImageButton	ibToggleButton;

	protected TextView		tvTitle;

	protected ListView		lvNews;

	protected ViewPager		vpLunboPic;

	protected LinearLayout	llLunboPoints;



	protected TextView		tvLunboTitle;

	protected MainActivity	mainActivity;

	protected TextView		tvHomeTitle;

	protected LinearLayout	llEditorPics;

	protected LinearLayout	llEditors;

	private View			mBaseView;

	protected FrameLayout	mFlLunboPoints;

	protected ImageView	ivPic;

	public BasePage(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
		initView();

		initListener();
	}

	protected void initListener() {
		ibToggleButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mainActivity.getSlidingMenu().toggle();
			}
		});
		
		ibSetting.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mainActivity,SettingActivity.class);
				mainActivity.startActivity(intent);
			}
		});

	}

	public abstract void initData(String themeDailyNumber);

	protected void initView() {
		mBaseView = View.inflate(mainActivity,
				R.layout.main_fragment_base_page, null);
		ibLogin = (ImageButton) mBaseView.findViewById(R.id.ib_base_login);
		ibSetting = (ImageButton) mBaseView.findViewById(R.id.ib_base_setting);
		ibToggleButton = (ImageButton) mBaseView
				.findViewById(R.id.ib_base_toggle);
		tvTitle = (TextView) mBaseView.findViewById(R.id.tv_base_title);

		lvNews = (ListView) mBaseView.findViewById(R.id.lv_main_content_news);
	
		ibToggleButton = (ImageButton) mBaseView
				.findViewById(R.id.ib_base_toggle);
		

	
	/*	vpLunboPic = (ViewPager) mBaseView
				.findViewById(R.id.vp_main_content_luobopic);
		llLunboPoints = (LinearLayout) mBaseView
				.findViewById(R.id.ll_main_content_lunbo_points);

	
		tvLunboTitle = (TextView) mBaseView
				.findViewById(R.id.tv_main_content_news_title);
		tvHomeTitle = (TextView) mBaseView
				.findViewById(R.id.tv_main_content_home_title);
		llEditorPics = (LinearLayout) mBaseView
				.findViewById(R.id.ll_main_content_editor_pics);
		llEditors = (LinearLayout) mBaseView
				.findViewById(R.id.ll_main_content_editors);*/
	
		
		initHeadView();

	}

	private void initHeadView() {
		View headView = View.inflate(mainActivity,
				R.layout.main_content_head_view, null);
		vpLunboPic = (ViewPager) headView
				.findViewById(R.id.vp_main_content_luobopic);
		ivPic = (ImageView) headView.findViewById(R.id.iv_main_content_pic);
		llLunboPoints = (LinearLayout) headView
				.findViewById(R.id.ll_main_content_lunbo_points);

		
		tvLunboTitle = (TextView) headView
				.findViewById(R.id.tv_main_content_news_title);
		tvHomeTitle = (TextView) headView
				.findViewById(R.id.tv_main_content_home_title);
		llEditorPics = (LinearLayout) headView
				.findViewById(R.id.ll_main_content_editor_pics);
		llEditors = (LinearLayout) headView
				.findViewById(R.id.ll_main_content_editors);
		mFlLunboPoints = (FrameLayout) headView.findViewById(R.id.fl_main_content_lunbo_points);
		 lvNews.addHeaderView(headView);
	}

	public View getRoot() {
		return mBaseView;
	}

}
