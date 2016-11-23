package com.example.zhihudiary.pages;

import com.example.zhihudiary.activity.MainActivity;

public class NoBoringPage extends ThemeDailyBasePage {

	public NoBoringPage(MainActivity mainActivity) {
		super(mainActivity);
		// TODO 自动生成的构造函数存根
	}

	@Override
	public void initData(String themeDailyNumber) {
		tvTitle.setText("不许无聊报");
		super.initData(themeDailyNumber);
	}

}
