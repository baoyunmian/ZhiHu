package com.example.zhihudiary.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class InterceptViewPager extends ViewPager {

	private float	mDownX;
	private float	mDownY;

	public InterceptViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO 自动生成的构造函数存根
	}

	public InterceptViewPager(Context context) {
		super(context);
		// TODO 自动生成的构造函数存根
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mDownX = ev.getX();
			mDownY = ev.getY();

			break;
		case MotionEvent.ACTION_MOVE:
			float moveX = ev.getX();
			float moveY = ev.getY();
			
			float dX = moveX - mDownX;
			float dY = moveY - mDownY;
			
			if (Math.abs(dX) > Math.abs(dY)) {
				getParent().requestDisallowInterceptTouchEvent(true);
			}

			break;

		
		default:
			break;
		}
		return super.dispatchTouchEvent(ev);
	}

}
