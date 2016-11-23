package com.example.zhihudiary.bean;

import java.util.List;

public class SingleThemeDailyBean {

	public String					background;
	public String					color;
	public String					description;
	public List<EditorsDatas>	editors;
	public String					image;
	public String					image_source;
	public String					name;
	public List<SingleThemeFDailyListDatas>					stories;

	public class EditorsDatas {
		public String	avatar;
		public String	bio;
		public String	id;
		public String	name;
		public String	url;
	}
	
	public class SingleThemeFDailyListDatas{
		public String	id;	
		public List<String>	images;
		public String	title;
		public String	type;
	}

}
