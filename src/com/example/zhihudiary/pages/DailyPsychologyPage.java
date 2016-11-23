package com.example.zhihudiary.pages;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Rect;
import android.graphics.drawable.shapes.OvalShape;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import com.example.zhihudiary.R;
import com.example.zhihudiary.activity.MainActivity;
import com.example.zhihudiary.bean.SingleThemeDailyBean;
import com.example.zhihudiary.bean.SingleThemeDailyBean.SingleThemeFDailyListDatas;
import com.example.zhihudiary.utils.MyContants;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class DailyPsychologyPage extends ThemeDailyBasePage {

	public DailyPsychologyPage(MainActivity mainActivity) {
		super(mainActivity);
		// TODO 自动生成的构造函数存根
	}
	@Override
	public void initData(String themeDailyNumber) {
		tvTitle.setText("日常心理学");
		super.initData(themeDailyNumber);
	}

	
}
