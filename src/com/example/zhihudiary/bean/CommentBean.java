package com.example.zhihudiary.bean;

import java.util.List;

public class CommentBean {
	public List<LongCommentData>	comments;

	public class LongCommentData {
		public String	author;
		public String	avatar;
		public String	content;
		public String	id;
		public String	likes;
		public ReplyBean	reply_to;
		public String	time;
		
		public class ReplyBean {
			public String	author;
			public String	content;
			public String	id;
			public String	status;
			
			
		}
	}
	
	
}