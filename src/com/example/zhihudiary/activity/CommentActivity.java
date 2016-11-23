package com.example.zhihudiary.activity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zhihudiary.R;
import com.example.zhihudiary.bean.CommentNumberBean;
import com.example.zhihudiary.bean.CommentBean;
import com.example.zhihudiary.bean.CommentBean.LongCommentData;
import com.example.zhihudiary.utils.MyContants;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class CommentActivity extends Activity {
	private ImageButton	mIbBack;
	private TextView	mTvTitle;
	private ListView	mLvContent;
	private Gson	mGson;
	private TextView	mFootView;
	private TextView	mHeadView;
	private CommentBean	mCommentData;
	private List<LongCommentData>	mLongComments = new ArrayList<CommentBean.LongCommentData>();
	private MyAdapter	mAdapter;
	private BitmapUtils	mBitmapUtils;
	private CommentNumberBean	mCommentNumberData;
	private String	mArticleId;
	private boolean mIsLongCommentUrl = true;//判断是否是长评url
	private List<LongCommentData>	mShortCommentsData = new ArrayList<CommentBean.LongCommentData>();
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		initData();
		initListener();
	}

	private void initListener() {
		mIbBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		mFootView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mShortCommentsData.size() == 0) {
					String shortCommentUrl = MyContants.COMMENTURL + mArticleId + "/short-comments";
					getDataFromNet(shortCommentUrl,false);
				}
				
			}
		});
	}

	private void initData() {
		if (mBitmapUtils == null) {
			mBitmapUtils = new BitmapUtils(CommentActivity.this);
		}
	
		mAdapter = new MyAdapter();
		mLvContent.setAdapter(mAdapter);
		Intent intent = getIntent();
		mArticleId = intent.getStringExtra("articleId");
		mCommentNumberData = (CommentNumberBean) intent.getSerializableExtra("commentNumberData");
//		System.out.println(mCommentNumberData.comments);
//		System.out.println("Id为" + articleId);
		String longCommentUrl = MyContants.COMMENTURL + mArticleId + "/long-comments";
		getDataFromNet(longCommentUrl,true);
	}

	private void getDataFromNet(String url,final boolean isLongCommentUrl) {
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.GET, url, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String longCommentJson = responseInfo.result;
				mIsLongCommentUrl = isLongCommentUrl;
				parseJson(longCommentJson);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				
			}
			
		});
		}

	protected void parseJson(String jsonData) {
		if (mGson == null) {
			
			mGson = new Gson();
		}
		
		mCommentData = mGson.fromJson(jsonData,CommentBean.class);
		if (mIsLongCommentUrl) {
			processData();
		}else {
			processShortCommentData();
		}
		
	}

	private void processShortCommentData() {
		mShortCommentsData = mCommentData.comments;
		mLongComments.addAll(mShortCommentsData);
		mAdapter.notifyDataSetChanged();
	}

	private void processData() {
//		1.为ListView设置数据
		setListViewData();
		
//		2.设置ListView头数据
		setHeaderViewAndFootViewData();
		
//		3.设置标题
		setCommentTitle();
		
	}
	
	private void setCommentTitle() {
		mTvTitle.setText(mCommentNumberData.comments + "条评论");
	}

	private void setHeaderViewAndFootViewData() {
		mHeadView.setText(mCommentNumberData.long_comments + "条长评");
		mFootView.setText(mCommentNumberData.short_comments + "条短评");
	}

	private void setListViewData() {
		mLongComments = mCommentData.comments;
		mAdapter.notifyDataSetChanged();
		
		
	}

	private class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			return mLongComments.size();
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
				convertView = View.inflate(CommentActivity.this, R.layout.comment_list_item, null);
				viewHolder = new ViewHolder();
				viewHolder.ivItemPic = (ImageView) convertView.findViewById(R.id.iv_comment_item_pic);
				viewHolder.tvItemName = (TextView) convertView.findViewById(R.id.tv_comment_item_name);
				viewHolder.tvItemVote = (TextView) convertView.findViewById(R.id.tv_comment_item_vote);
				viewHolder.tvItemContent = (TextView) convertView.findViewById(R.id.tv_comment_item_content);
				viewHolder.tvItemTime = (TextView) convertView.findViewById(R.id.tv_comment_item_time);
				convertView.setTag(viewHolder);
			}else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			
			mBitmapUtils.display(viewHolder.ivItemPic, mLongComments.get(position).avatar);
			viewHolder.tvItemName.setText(mLongComments.get(position).author);
			viewHolder.tvItemVote.setText(mLongComments.get(position).likes);
			viewHolder.tvItemContent.setText(mLongComments.get(position).content);
			
			SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
			long time = Long.parseLong(mLongComments.get(position).time);
			Calendar c = Calendar.getInstance();  
			c.setTimeInMillis(time * 1000);
			String replyTime = format.format(c.getTime());
			viewHolder.tvItemTime.setText(replyTime);
			return convertView;
		}
		
	}
	private class ViewHolder{
		ImageView ivItemPic;
		TextView tvItemName;
		TextView tvItemVote;
		TextView tvItemContent;
		TextView tvItemTime;
	}

	private void initView() {
		setContentView(R.layout.comment_page);
		mIbBack = (ImageButton) findViewById(R.id.ib_comment_back);
		mTvTitle = (TextView) findViewById(R.id.tv_comment_title);
		mLvContent = (ListView) findViewById(R.id.lv_comment_content);
		
		initHeadView();
		initFootView();
		
	}

	private void initFootView() {
		mFootView = new TextView(CommentActivity.this);
		mFootView.setPadding(10, 10, 10, 10);
		mLvContent.addFooterView(mFootView);
	}

	private void initHeadView() {
		mHeadView = new TextView(CommentActivity.this);
		mHeadView.setPadding(10, 10, 10, 10);
		mLvContent.addHeaderView(mHeadView);
		
	}
}
