package com.example.zhihudiary.activity;

import com.example.zhihudiary.R;
import com.example.zhihudiary.fragment.LeftContentFragment;
import com.example.zhihudiary.fragment.MainContentFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.Window;
import android.widget.FrameLayout;

public class MainActivity extends SlidingFragmentActivity {


	private static final String	MAIN_MENU_TAG	= "MAIN_MENU_TAG";
	private static final String	LEFT_MENU_TAG	= "LEFT_MENU_TAG";
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initView();
        initData();
        
      


    
	}

    private void initData() {
		FragmentManager fragmentManager =getSupportFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		MainContentFragment mainContentFragment = new MainContentFragment();
		transaction.replace(R.id.main_content, mainContentFragment,MAIN_MENU_TAG);
		
		LeftContentFragment leftContentFragment  = new LeftContentFragment();
		transaction.replace(R.id.left_content, leftContentFragment,LEFT_MENU_TAG);
		transaction.commit();
		
	}

	private void initView() {
  	  setContentView(R.layout.maincontent);
  	  setBehindContentView(R.layout.leftcontent);
  	  SlidingMenu slidingMenu = getSlidingMenu();
  	  slidingMenu.setMode(SlidingMenu.LEFT);
  	  slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
  	  slidingMenu.setBehindOffset(100);
  	  
  	
  	  
  }
	public MainContentFragment getMainContentFragment(){
		FragmentManager fragmentManager = getSupportFragmentManager();
		MainContentFragment mainContentFragment = (MainContentFragment) fragmentManager.findFragmentByTag(MAIN_MENU_TAG);
		return mainContentFragment;
	}
	
	
	public LeftContentFragment getLeftContentFragment(){
		FragmentManager fragmentManager = getSupportFragmentManager();
		LeftContentFragment leftContentFragment = (LeftContentFragment) fragmentManager.findFragmentByTag(LEFT_MENU_TAG);
		return leftContentFragment;
	}
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
