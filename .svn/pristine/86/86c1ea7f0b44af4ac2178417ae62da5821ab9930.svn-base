package com.example.zhihudiary.pages;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.content.Intent;
import android.graphics.Bitmap.Config;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhihudiary.R;
import com.example.zhihudiary.activity.ArticleActivity;
import com.example.zhihudiary.activity.MainActivity;
import com.example.zhihudiary.bean.HomeDataBean;
import com.example.zhihudiary.bean.HomeDataBean.HomeListViewData;
import com.example.zhihudiary.bean.HomeDataBean.HomeLunboData;
import com.example.zhihudiary.utils.DensityUtil;
import com.example.zhihudiary.utils.MyContants;
import com.example.zhihudiary.utils.SpTools;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class HomeBasePage extends BasePage {

	private Gson					mGson;
	private HomeDataBean			mHomeData;
	private List<HomeLunboData>		mLunboDatas		= new ArrayList<HomeDataBean.HomeLunboData>();
	private List<HomeListViewData>	mListViewDatas	= new ArrayList<HomeDataBean.HomeListViewData>();
	private LunboAdapter			mLunboAdapter;
	private int						selectedPosition;
	private ListViewAdapter			mListViewAdapter;
	private BitmapUtils				mBitmapUtils;

	public HomeBasePage(MainActivity mainActivity) {
		super(mainActivity);
		// TODO 自动生成的构造函数存根
	}

	@Override
	public void initData(String themeDailyNumber) {
		if (mBitmapUtils == null) {
			mBitmapUtils = new BitmapUtils(mainActivity);
			mBitmapUtils.configDefaultBitmapConfig(Config.ARGB_4444);
		}

		ibSetting.setVisibility(View.VISIBLE);
		tvTitle.setText("首页");

		loadData();

	}

	@Override
	protected void initListener() {
		vpLunboPic.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				selectedPosition = position;
				setTextAndSelectPoint();
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO 自动生成的方法存根

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO 自动生成的方法存根

			}
		});
		
		lvNews.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (position != 0) {
					String url = MyContants.BASEARTICLESTRING + mHomeData.stories.get(position-1).id;
					
					Intent intent = new Intent(mainActivity,ArticleActivity.class);
					intent.putExtra("articleUrl", url);
					mainActivity.startActivity(intent);
				}
				
			}
		});
		super.initListener();

	}

	private void loadData() {
		mLunboAdapter = new LunboAdapter();
		vpLunboPic.setAdapter(mLunboAdapter);

		mListViewAdapter = new ListViewAdapter();
		lvNews.setAdapter(mListViewAdapter);

		String jsonCache = SpTools.getString(mainActivity,
				MyContants.HOMELATEST, "");
		if (!TextUtils.isEmpty(jsonCache)) {
			 System.out.println(jsonCache);
			parseData(jsonCache);
		}
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.GET, MyContants.HOMELATEST,
				new RequestCallBack<String>() {

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						String homeJsonData = responseInfo.result;
						SpTools.putString(mainActivity, MyContants.HOMELATEST,
								homeJsonData);
						System.out.println("Data" + homeJsonData);
						if (!TextUtils.isEmpty(homeJsonData) ) {
							parseData(homeJsonData);
						}
						

					}

					@Override
					public void onFailure(HttpException error, String msg) {
						// TODO 自动生成的方法存根

					}
				});
	}

	private void parseData(String homeJsonData) {
		if (mGson == null) {
			mGson = new Gson();
		}
		try {
			mHomeData = mGson.fromJson(homeJsonData, HomeDataBean.class);
			processData();
		} catch (JsonSyntaxException e) {
			Toast.makeText(mainActivity, "网络连接错误", 1).show();
		}
		
		// System.out.println(mHomeData.stories.get(0).images.get(0));

		
	}

	private void processData() {
		// 1.给ViewPager数据
		setLunboData();

		// 2.初始化点
		initLunboPoints();

		// 3.设置标题及点的选中
		setTextAndSelectPoint();

		// 4.为ListView设置数据

		setListViewData();

	}

	private void setListViewData() {
		mListViewDatas = mHomeData.stories;

	}

	private class ListViewAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mListViewDatas.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder;
			if (convertView == null) {
				convertView = View.inflate(mainActivity, R.layout.list_item,
						null);
				viewHolder = new ViewHolder();
				viewHolder.textView = (TextView) convertView
						.findViewById(R.id.tv_list_item_title);
				viewHolder.imageView = (ImageView) convertView
						.findViewById(R.id.iv_list_item_image);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}

			viewHolder.textView.setText(mListViewDatas.get(position).title);
			String listImageUrl = mListViewDatas.get(position).images.get(0);
			if (!TextUtils.isEmpty(listImageUrl)) {
				mBitmapUtils.display(viewHolder.imageView, listImageUrl);
			} else {
				viewHolder.imageView.setVisibility(View.GONE);
			}

			return convertView;
		}

	}

	private class ViewHolder {
		TextView	textView;
		ImageView	imageView;
	}

	private void setTextAndSelectPoint() {
		tvLunboTitle.setText(mHomeData.top_stories.get(selectedPosition).title);
		for (int i = 0; i < mLunboDatas.size(); i++) {
			llLunboPoints.getChildAt(i).setEnabled(i == selectedPosition);

		}
	}

	private void initLunboPoints() {
		llLunboPoints.removeAllViews();
		for (int i = 0; i < mLunboDatas.size(); i++) {
			View view = new View(mainActivity);
			view.setBackgroundResource(R.drawable.selector_lunbo_points);
			view.setEnabled(false);

			LayoutParams layoutParams = new LayoutParams(DensityUtil.dip2px(
					mainActivity, 5), DensityUtil.dip2px(mainActivity, 5));
			layoutParams.leftMargin = DensityUtil.dip2px(mainActivity, 5);
			view.setLayoutParams(layoutParams);
			llLunboPoints.addView(view);

		}
	}

	private class LunboAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return mLunboDatas.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO 自动生成的方法存根
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			
			ImageView imageView = new ImageView(mainActivity);
			imageView.setScaleType(ScaleType.FIT_XY);
			

			mBitmapUtils.display(imageView,
					mHomeData.top_stories.get(position).image);
			
			
			imageView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					String url = MyContants.BASEARTICLESTRING + mHomeData.top_stories.get(vpLunboPic.getCurrentItem()).id;
					
					Intent intent = new Intent(mainActivity,ArticleActivity.class);
					intent.putExtra("articleUrl", url);
					mainActivity.startActivity(intent);
				}
			});

			container.addView(imageView);
			
			return imageView;
		}
	}

	private void setLunboData() {
		mLunboDatas = mHomeData.top_stories;
		mLunboAdapter.notifyDataSetChanged();
	}

}
