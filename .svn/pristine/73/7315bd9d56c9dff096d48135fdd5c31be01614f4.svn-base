package com.example.zhihudiary.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhihudiary.R;
import com.example.zhihudiary.bean.ArticleBean;
import com.example.zhihudiary.bean.CommentNumberBean;
import com.example.zhihudiary.utils.MyContants;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class ArticleActivity extends Activity {
	private ImageButton	mIbBack;
	private ImageButton	mIbShare;
	private TextView	mTvComment;
	private TextView	mTvPraise;
	private WebView	mWvContent;
	private String	mArticleUrl;
	private Gson	mGson;
	private ArticleBean	mArticleData;
	private String	mArticleId;
	private boolean mIsArticleUrl = true;//判断是否是article的url
	private CommentNumberBean	mCommentNumberData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	
		initData();
		initListner();
	}

	private void initListner() {
		mIbBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		mTvComment.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (Integer.parseInt(mCommentNumberData.comments) != 0) {
					Intent intent = new Intent(ArticleActivity.this,CommentActivity.class);
					intent.putExtra("articleId", mArticleId);
					
					Bundle bundle = new Bundle();
					bundle.putSerializable("commentNumberData", mCommentNumberData);
					intent.putExtras(bundle);
					startActivity(intent);
				}
				
				
			}
		});
		
		
	}

	private void initData() {
		mArticleUrl = getIntent().getStringExtra("articleUrl");
		
		mArticleId = mArticleUrl.substring(36);
		
		if (TextUtils.isEmpty(mArticleUrl)) {
			Toast.makeText(ArticleActivity.this, "链接错误", 1).show();
		}else {
//			Toast.makeText(ArticleActivity.this, "链接成功", 1).show();
			loadCommentNumberData();
			loadArticleData();
			
			
		
		}
	}

	private void loadArticleData() {
		getDataFromNet(mArticleUrl,true);
	}

	private void loadCommentNumberData() {
		String commentNumberUrl = MyContants.COMMENTNUMBERURL + mArticleId;
		getDataFromNet(commentNumberUrl,false);
	}

	private void getDataFromNet(String url,final boolean isArticleUrl) {
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.GET, url, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String jsonData = responseInfo.result;
				mIsArticleUrl = isArticleUrl;
//				System.out.println(jsonData);
				parseJson(jsonData);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO 自动生成的方法存根
				
			}

		
		});
	}

	protected void parseJson(String jsonData) {
		if (mGson == null) {
			
			mGson = new Gson();
		}
		
		if (mIsArticleUrl) {
			mArticleData = mGson.fromJson(jsonData, ArticleBean.class);
//			System.out.println(mArticleData.css.get(0));
			
			processArticleData();
		}else {
			mCommentNumberData = mGson.fromJson(jsonData, CommentNumberBean.class);
			processCommentNumberData();
		}
	
	}

	private void processCommentNumberData() {
		mTvComment.setText(mCommentNumberData.comments);
		mTvPraise.setText(mCommentNumberData.popularity);
	}

	private void processArticleData() {
		mWvContent.loadUrl(mArticleData.share_url);
	}

	private void initView() {
		setContentView(R.layout.article_page);
		mIbBack = (ImageButton) findViewById(R.id.ib_article_back);
		mIbShare = (ImageButton) findViewById(R.id.ib_article_share);
		mTvComment = (TextView) findViewById(R.id.tv_article_comment);
		mTvPraise = (TextView) findViewById(R.id.tv_article_praise);
		mWvContent = (WebView) findViewById(R.id.wv_article_content);
		WebSettings wvSettings = mWvContent.getSettings();
		wvSettings.setJavaScriptEnabled(true);
		
 	}


}
