package com.example.zhihudiary.pages;

import com.example.zhihudiary.activity.MainActivity;

public class MusicPage extends ThemeDailyBasePage {

	public MusicPage(MainActivity mainActivity) {
		super(mainActivity);
		// TODO 自动生成的构造函数存根
	}

	@Override
	public void initData(String themeDailyNumber) {
		tvTitle.setText("音乐日报");
		super.initData(themeDailyNumber);
	}

}
