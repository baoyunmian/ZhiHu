package com.example.zhihudiary.pages;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap.Config;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;

import com.example.zhihudiary.R;
import com.example.zhihudiary.activity.MainActivity;
import com.example.zhihudiary.bean.HomeDataBean;
import com.example.zhihudiary.bean.HomeDataBean.HomeListViewData;
import com.example.zhihudiary.bean.HomeDataBean.HomeLunboData;

import com.example.zhihudiary.utils.DensityUtil;
import com.example.zhihudiary.utils.MyContants;
import com.example.zhihudiary.utils.SpTools;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class DesignPage extends ThemeDailyBasePage {

	public DesignPage(MainActivity mainActivity) {
		super(mainActivity);
		// TODO 自动生成的构造函数存根
	}
	@Override
	public void initData(String themeDailyNumber) {
		tvTitle.setText("设计日报");
		super.initData(themeDailyNumber);
	}

}
