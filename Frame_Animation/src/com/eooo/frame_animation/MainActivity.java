package com.eooo.frame_animation;

import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	AnimationDrawable drawable;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);

		init();
	}

	private void init() {

		ImageView imageView = (ImageView) findViewById(R.id.image);
		imageView.setBackgroundResource(R.drawable.frame_list);
		drawable = (AnimationDrawable) imageView.getBackground();
		findViewById(R.id.start).setOnClickListener(this);
		findViewById(R.id.stop).setOnClickListener(this);
		drawable.start();
		drawable.stop();
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.start:
			drawable.start();
			break;
		case R.id.stop:
			drawable.stop();
			break;

		}

	}

}
