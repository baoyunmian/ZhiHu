package com.example.zhihudiary.pages;

import com.example.zhihudiary.activity.MainActivity;

public class InternetPage extends ThemeDailyBasePage {

	public InternetPage(MainActivity mainActivity) {
		super(mainActivity);
		// TODO 自动生成的构造函数存根
	}

	@Override
	public void initData(String themeDailyNumber) {
		tvTitle.setText("互联网安全");
		super.initData(themeDailyNumber);
	}

}
