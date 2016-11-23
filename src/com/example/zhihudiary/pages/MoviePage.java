package com.example.zhihudiary.pages;

import com.example.zhihudiary.activity.MainActivity;

public class MoviePage extends ThemeDailyBasePage {

	public MoviePage(MainActivity mainActivity) {
		super(mainActivity);
		// TODO 自动生成的构造函数存根
	}

	@Override
	public void initData(String themeDailyNumber) {
		tvTitle.setText("电影日报");
		super.initData(themeDailyNumber);
	}

}
