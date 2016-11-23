package com.example.zhihudiary.bean;

import java.util.List;

public class AllThemeDailyBean {

	public String limit;
	public List<SingleThemeDailyData> others;
	public List<String> subscribed;
	
	public class SingleThemeDailyData{
		public String color;
		public String description;
		public String id;
		public String name;
		public String thumbnail;
	}

}
