package com.example.zhihudiary.fragment;

import com.example.zhihudiary.activity.MainActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {
	private View	root;
	protected MainActivity	mainActivity;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mainActivity = (MainActivity) getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		root = initView();
		return root;
	}

	protected abstract View initView();

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		initData();
		initListener();
		super.onActivityCreated(savedInstanceState);
	}

	protected void initListener() {
		// TODO 自动生成的方法存根
		
	}

	protected void initData() {
		// TODO 自动生成的方法存根
		
	}

}
