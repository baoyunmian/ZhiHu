package com.example.zhihudiary.pages;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.shapes.OvalShape;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.example.zhihudiary.R;
import com.example.zhihudiary.activity.ArticleActivity;
import com.example.zhihudiary.activity.MainActivity;
import com.example.zhihudiary.bean.SingleThemeDailyBean;
import com.example.zhihudiary.bean.SingleThemeDailyBean.SingleThemeFDailyListDatas;
import com.example.zhihudiary.utils.DensityUtil;
import com.example.zhihudiary.utils.MyContants;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class ThemeDailyBasePage extends BasePage {

	private Gson								mGson;
	private SingleThemeDailyBean				mSingleThemeDailyDatas;
	private BitmapUtils							mBitmapUtils;
	private List<SingleThemeFDailyListDatas>	mListViewDatas	= new ArrayList<SingleThemeDailyBean.SingleThemeFDailyListDatas>();
	private MyAdapter							mAdapter;
	private String								mThemeDailyNumberString;

	public ThemeDailyBasePage(MainActivity mainActivity) {
		super(mainActivity);

	}

	@Override
	protected void initListener() {
		super.initListener();

		lvNews.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (position != 0) {
					String url = MyContants.BASEARTICLESTRING
							+ mSingleThemeDailyDatas.stories.get(position-1).id;

					Intent intent = new Intent(mainActivity,
							ArticleActivity.class);
					intent.putExtra("articleUrl", url);
					mainActivity.startActivity(intent);
				}

			}
		});
	}

	@Override
	public void initData(String themeDailyNumber) {
		this.mThemeDailyNumberString = themeDailyNumber;
		if (mBitmapUtils == null) {

			mBitmapUtils = new BitmapUtils(mainActivity);
		}
		mAdapter = new MyAdapter();
		lvNews.setAdapter(mAdapter);
		vpLunboPic.setVisibility(View.GONE);
		ivPic.setVisibility(View.VISIBLE);
		mFlLunboPoints.setVisibility(View.GONE);
		tvHomeTitle.setVisibility(View.GONE);
		llEditors.setVisibility(View.VISIBLE);

		loadData();
	}

	public void loadData() {

		String url = MyContants.SINGETHEMEPREFIXURL + mThemeDailyNumberString;
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.GET, url, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String jsonDataString = responseInfo.result;
				// System.out.println(jsonDataString);
				parseData(jsonDataString);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO 自动生成的方法存根

			}
		});

	}

	public void parseData(String jsonDataString) {
		if (mGson == null) {

			mGson = new Gson();
		}
		try {

			mSingleThemeDailyDatas = mGson.fromJson(jsonDataString,
					SingleThemeDailyBean.class);
			// System.out.println(mSingleThemeDailyDatas.editors.get(0).bio);

			processData();
		} catch (JsonSyntaxException e) {
			// TODO: handle exception
			Toast.makeText(mainActivity, "网络连接错误", 1).show();
		}

	}

	private void processData() {
		// 1.设置大图片
		setBigPicture();

		// 2.设置标题
		setPictureTitle();

		// 3.设置主编图片
		setEditorsPic();

		// 4.设置ListView数据
		setListViewData();
	}

	private void setListViewData() {
		mListViewDatas = mSingleThemeDailyDatas.stories;
		mAdapter.notifyDataSetChanged();

	}

	private class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO 自动生成的方法存根
			return mListViewDatas.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO 自动生成的方法存根
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO 自动生成的方法存根
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

			String listItemTitle = mSingleThemeDailyDatas.stories.get(position).title;

			viewHolder.textView.setText(listItemTitle);
			viewHolder.imageView.setVisibility(View.GONE);
			/*
			 * if ((mSingleThemeDailyDatas.stories.get(position).images.get(0))
			 * != null &&
			 * (mSingleThemeDailyDatas.stories.get(position).images.get(0)) !=
			 * "") {
			 * 
			 * String listItemImageUrl =
			 * mSingleThemeDailyDatas.stories.get(position).images.get(0);
			 * mBitmapUtils.display(viewHolder.imageView, listItemImageUrl); }
			 */
			return convertView;
		}

	}

	public class ViewHolder {
		ImageView	imageView;
		TextView	textView;
	}

	private void setEditorsPic() {
		llEditorPics.removeAllViews();
		for (int i = 0; i < mSingleThemeDailyDatas.editors.size(); i++) {
			String editorsPicUrl = mSingleThemeDailyDatas.editors.get(i).avatar;
			ImageView imageView = new ImageView(mainActivity);

			imageView.setScaleType(ScaleType.CENTER);
			LayoutParams layoutParams = new LayoutParams(DensityUtil.dip2px(
					mainActivity, 40), DensityUtil.dip2px(mainActivity, 480));
			if (i != 0) {
				layoutParams.leftMargin = DensityUtil.dip2px(mainActivity, 10);
			}
			imageView.setLayoutParams(layoutParams);

			mBitmapUtils.display(imageView, editorsPicUrl);
			llEditorPics.addView(imageView);

		}

	}

	private void setPictureTitle() {
		String pictureTitle = mSingleThemeDailyDatas.description;
		tvLunboTitle.setText(pictureTitle);
	}

	private void setBigPicture() {

		String picUrl = mSingleThemeDailyDatas.image;
		System.out.println(picUrl);
		mBitmapUtils.display(ivPic, picUrl);

	}

}
