package com.example.zhihudiary.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

import com.example.zhihudiary.R;

public class SplashActivity extends Activity {
	private View	mIvBackground;
	private AlphaAnimation	mAlphaAnimation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		initView();
		initAnimation();
		initListener();
		
	}

	private void initListener() {
		mAlphaAnimation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation arg0) {
			
				
			}
			
			@Override
			public void onAnimationRepeat(Animation arg0) {
				
			}
			
			@Override
			public void onAnimationEnd(Animation arg0) {
				/*try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}*/
				Intent intent = new Intent(SplashActivity.this,MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

	private void initAnimation() {
		/*AnimationSet animationSet = new AnimationSet(true);
		
		ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1, 
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		scaleAnimation.setDuration(2000);
		scaleAnimation.setFillAfter(true);
		animationSet.addAnimation(scaleAnimation);*/
		
		mAlphaAnimation = new AlphaAnimation(0, 1);
		mAlphaAnimation.setDuration(1500);
		mAlphaAnimation.setFillAfter(true);
//		animationSet.addAnimation(alphaAnimation);
	
		mIvBackground.setAnimation(mAlphaAnimation);
	}

	private void initView() {
		setContentView(R.layout.splashlayout);
		mIvBackground = findViewById(R.id.iv_spash_backgroud);
		
	}

}
