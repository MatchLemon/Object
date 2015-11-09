package com.frag.fragmenttest;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentAdapt extends FragmentPagerAdapter {

	List<Fragment> listf;
	List<String> listT;
	public FragmentAdapt(FragmentManager fm, List<Fragment> listf,List<String> listT) {
		super(fm);
		this.listf=listf;
		this.listT=listT;
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return listf.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listf.size();
	}
	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		return listT.get(position);
	}

}
