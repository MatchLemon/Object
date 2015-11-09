package com.examp.gallerytest;

import android.R.integer;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class ImageAdapter extends BaseAdapter {

	Context context;
	int[] res;
	
	public ImageAdapter(Context context, int[] res) {
		
		this.context = context;
		this.res = res;
	}

	@Override
	public int getCount() {
		
		return Integer.MAX_VALUE;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return res[arg0];
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		
		ImageView imageView=new ImageView(context);
//		imageView.setBackgroundResource(res[arg0%res.length]);
		imageView.setBackgroundResource(res[arg0]);
		imageView.setLayoutParams(new Gallery.LayoutParams(200, 150));
		imageView.setScaleType(ScaleType.FIT_XY);
		return imageView;
	}

}
