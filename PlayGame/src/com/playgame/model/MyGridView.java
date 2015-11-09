package com.playgame.model;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import com.playgame.interf.MyGuidButtonListener;
import com.playgame.ui.R;
import com.playgame.util.Util;

public class MyGridView extends GridView {
	public final static int COUNTS_WORDS = 24;
	
	private ArrayList<MyGridButton> mArrayList = new ArrayList<MyGridButton>();

	private MyGridAdapter mAdapter;
	
	private Context mContext;
	Animation animation;//选择按钮动画
	MyGuidButtonListener buttonListener;
	public MyGridView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		
		mContext = context;
		
		mAdapter = new MyGridAdapter();
		this.setAdapter(mAdapter);
	}
	
	public void updateData(ArrayList<MyGridButton> list) {
		mArrayList = list;
	
		// 重新设置数据源
		setAdapter(mAdapter);
	}

	class MyGridAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return mArrayList.size();
		}

		@Override
		public Object getItem(int pos) {
			return mArrayList.get(pos);
		}

		@Override
		public long getItemId(int pos) {
			return pos;
		}

		@Override
		public View getView(int pos, View v, ViewGroup p) {
			final MyGridButton holder;
		
			
			if (v == null) {
				
				v = Util.getView(mContext, R.layout.select_button);
			animation=AnimationUtils.loadAnimation(mContext, R.anim.scale_button);
				holder = mArrayList.get(pos);
				
				holder.mIndex = pos;
				holder.mViewButton = (Button)v.findViewById(R.id.item_but);
				animation.setStartOffset(pos*100);
				holder.mViewButton.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
				
						buttonListener.onMyGuidButtonListener(holder);
						
					}
				});
				
				v.setTag(holder);
			} else {
				holder = (MyGridButton)v.getTag();
			}
			
			holder.mViewButton.setText(holder.mWordString);
		v.startAnimation(animation);
			return v;
		}
		
		
		
	}
	public void addMyGuidButtonListener(MyGuidButtonListener buttonListener){
		
		this.buttonListener=buttonListener;
		
	}
	

	
}
