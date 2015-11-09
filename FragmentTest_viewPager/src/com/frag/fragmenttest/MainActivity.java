package com.frag.fragmenttest;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;


public class MainActivity extends FragmentActivity implements OnPageChangeListener{

	ViewPager pager;
	List<Fragment> listf;
	List<String > listT;
	FragmentAdapt adapter;
	PagerTabStrip tag;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		pager = (ViewPager) findViewById(R.id.pager);
		listf=new ArrayList<Fragment>();
		listT=new ArrayList<String>();
		init();
		adapter=new FragmentAdapt(getSupportFragmentManager(),listf,listT);
		pager.setAdapter(adapter);
		pager.setOnPageChangeListener(this);
		TelephonyManager manager=(TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		
	}
	private void init() {

		Fragment1 fragment1 = new Fragment1();
		Fragment2 fragment2 = new Fragment2();
		Fragment3 fragment3 = new Fragment3();
		Fragment4 fragment4 = new Fragment4();
		listf.add(fragment1);
		listf.add(fragment2);
		listf.add(fragment3);
//		listf.add(fragment4);
		listT.add("第一页");
		listT.add("第二页");
		listT.add("第三页");
//		listT.add("第四页");
		
	}
	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
	
		Toast.makeText(this,"第"+arg0, Toast.LENGTH_SHORT).show();;
	}

}
