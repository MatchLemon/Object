package com.examp.gallerytest;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends ActionBarActivity implements
		OnItemSelectedListener, ViewFactory {

	Gallery gallery;
	int[] res = { R.drawable.item1, R.drawable.item2, R.drawable.item3,
			R.drawable.item4, R.drawable.item5, R.drawable.item6,
			R.drawable.item7, R.drawable.item8, R.drawable.item9,
			R.drawable.item10, R.drawable.item11, R.drawable.item12, };

	ImageAdapter adapter;
	ImageSwitcher imageSwitcher;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		gallery = (Gallery) findViewById(R.id.gallery);
		adapter = new ImageAdapter(this, res);
		gallery.setAdapter(adapter);
		imageSwitcher = (ImageSwitcher) findViewById(R.id.is);
		imageSwitcher.setFactory(this);
		imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
		imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_out));
		gallery.setOnItemSelectedListener(this);
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {

//		imageSwitcher.setBackgroundResource(res[arg2 % res.length]);
		imageSwitcher.setBackgroundResource(res[arg2]);
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public View makeView() {
		ImageView imageView = new ImageView(this);
		imageView.setScaleType(ScaleType.FIT_CENTER);
		return imageView;
	}

	
//	
//	mImageView = (ImageView) findViewById(R.id.mImageView);
//	 
//	File f = new File(fileName);
//	if (f.exists()) { /* 产生Bitmap对象，并放入mImageView中 */
//	    Bitmap bm = BitmapFactory.decodeFile(fileName);
//	    mImageView.setImageBitmap(bm);
//	    mTextView.setText(fileName);
//	}

	
}
