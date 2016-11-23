package com.example.zhihudiary.fragment;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Movie;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhihudiary.R;
import com.example.zhihudiary.pages.BasePage;
import com.example.zhihudiary.pages.BigCompanyPage;
import com.example.zhihudiary.pages.CartoonPage;
import com.example.zhihudiary.pages.DailyPsychologyPage;
import com.example.zhihudiary.pages.DesignPage;
import com.example.zhihudiary.pages.FinancePage;
import com.example.zhihudiary.pages.GamePage;
import com.example.zhihudiary.pages.HomeBasePage;
import com.example.zhihudiary.pages.InternetPage;
import com.example.zhihudiary.pages.MoviePage;
import com.example.zhihudiary.pages.MusicPage;
import com.example.zhihudiary.pages.NoBoringPage;
import com.example.zhihudiary.pages.SportsPage;
import com.example.zhihudiary.pages.UserRecommendPage;
import com.example.zhihudiary.view.MyViewPager;

public class MainContentFragment extends BaseFragment {
	private List<BasePage> allPages = new ArrayList<BasePage>();
	private MyViewPager	mViewPager;
	private String[]		themeDailyNumber	= { "13", "12", "3", "11", "4",
			"5", "6", "10", "2", "7", "9", "8"	};
	private int				selectedPosition = 0;

	@Override
	protected View initView() {
		View root = View.inflate(mainActivity, R.layout.main_fragment_base, null);
		mViewPager = (MyViewPager) root.findViewById(R.id.vp_base_fragment);
		
		return root;
	}
	
	
	@Override
	protected void initData() {
		super.initData();
		HomeBasePage homeBasePage = new HomeBasePage(mainActivity);
		DailyPsychologyPage dailyPsychologyPage = new DailyPsychologyPage(mainActivity);
		UserRecommendPage userRecommendPage = new UserRecommendPage(mainActivity);
		MoviePage moviePage = new MoviePage(mainActivity);
		NoBoringPage noBoringPage = new NoBoringPage(mainActivity);
		DesignPage designPage = new DesignPage(mainActivity);
		BigCompanyPage bigCompanyPage = new BigCompanyPage(mainActivity);
		FinancePage financePage = new FinancePage(mainActivity);
		InternetPage internetPage = new InternetPage(mainActivity);
		GamePage gamePage = new GamePage(mainActivity);
		MusicPage musicPage = new MusicPage(mainActivity);
		CartoonPage cartoonPage = new CartoonPage(mainActivity);
		SportsPage sportsPage = new SportsPage(mainActivity);
		
		allPages.add(homeBasePage);
		allPages.add(dailyPsychologyPage);
		allPages.add(userRecommendPage);
		allPages.add(moviePage);
		allPages.add(noBoringPage);
		allPages.add(designPage);
		allPages.add(bigCompanyPage);
		allPages.add(financePage);
		allPages.add(internetPage);
		allPages.add(gamePage);
		allPages.add(musicPage);
		allPages.add(cartoonPage);
		allPages.add(sportsPage);
		
		
		MyAdapter myAdapter = new MyAdapter();
		mViewPager.setAdapter(myAdapter);
		
		
	}
	
	public void switchPage(int position){
		this.selectedPosition = position;
		mViewPager.setCurrentItem(position);
		
	}
	
	private class MyAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			// TODO 自动生成的方法存根
			return allPages.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO 自动生成的方法存根
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO 自动生成的方法存根
			container.removeView((View) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			BasePage selectedPage = allPages.get(position);
			container.addView(selectedPage.getRoot());
			if (selectedPosition != 0) {
				selectedPage.initData(themeDailyNumber[selectedPosition -1]);
			}else{
				selectedPage.initData("");
			}
			
			return selectedPage.getRoot();
		}
		
	}

}
