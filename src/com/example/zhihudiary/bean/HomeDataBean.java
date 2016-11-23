package com.example.zhihudiary.bean;

import java.util.List;

public class HomeDataBean {
	public String					date;
	public List<HomeListViewData>	stories;
	public List<HomeLunboData>		top_stories;

	public class HomeListViewData {
		public String	ga_prefix;
		public String	id;
		public List<String> 	images;
		public String	title;
		public String	type;
		public String	multipic;

	}

	public class HomeLunboData {
		public String	ga_prefix;
		public String	id;
		public String	image;
		public String	title;
		public String	type;
	}

}
