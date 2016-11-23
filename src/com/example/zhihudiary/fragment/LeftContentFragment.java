package com.example.zhihudiary.fragment;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zhihudiary.R;

public class LeftContentFragment extends BaseFragment {

	private View			mRoot;
	private LinearLayout	mLlHome;
	private ListView		mLvContent;

	private String[]		mDatas	= new String[] { "日常心理学", "用户推荐日报", "电影日报",
			"不许无聊", "设计日报", "大公司日报", "财经日报", "互联网安全", "开始游戏", "音乐日报", "动漫日报",
			"体育日报"					};

	@Override
	protected View initView() {
		mRoot = View.inflate(mainActivity, R.layout.left_fragent_base, null);
		mLlHome = (LinearLayout) mRoot
				.findViewById(R.id.ll_left_fragement_base_home);
		mLvContent = (ListView) mRoot
				.findViewById(R.id.lv_left_fragment_list_view);

		return mRoot;
	}

	@Override
	protected void initData() {
		super.initData();

		MyAdapter adapter = new MyAdapter();
		mLvContent.setAdapter(adapter);

	}

	@Override
	protected void initListener() {
		super.initListener();
		mLlHome.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mainActivity.getMainContentFragment().switchPage(0);
				mainActivity.getSlidingMenu().toggle();
				mLvContent.setSelector(android.R.color.transparent);
			}
		});
		
		mLvContent.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				mLvContent.setSelector(R.color.menu_listview_selection);
				mainActivity.getMainContentFragment().switchPage(position +1);
				mainActivity.getSlidingMenu().toggle();
			}
		});
	}

	private class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return mDatas.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO 自动生成的方法存根
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO 自动生成的方法存根
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder;
			if (convertView == null) {
				convertView = View.inflate(mainActivity,
						R.layout.left_fragment_list_item, null);
				viewHolder = new ViewHolder();
				viewHolder.tvTitle = (TextView) convertView
						.findViewById(R.id.tv_left_fragment_list_title);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			viewHolder.tvTitle.setText(mDatas[position]);

			return convertView;
		}

	}

	private class ViewHolder {
		TextView	tvTitle;
	}

}
