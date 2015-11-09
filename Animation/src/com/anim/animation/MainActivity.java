package com.anim.animation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		init();
	}

	public void init() {

		findViewById(R.id.alpha).setOnClickListener(this);
		findViewById(R.id.translation).setOnClickListener(this);
		findViewById(R.id.scale).setOnClickListener(this);
		findViewById(R.id.rotate).setOnClickListener(this);
		findViewById(R.id.setall).setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
	Animation anim=null;
	ImageView imageView=(ImageView) findViewById(R.id.imageView);
		switch (arg0.getId()) {
	case R.id.alpha:
		anim=AnimationUtils.loadAnimation(this, R.anim.alpha_animation);
//		Toast.makeText(this, "µã»÷", Toast.LENGTH_SHORT).show();
		break;
	case R.id.translation:
		anim=AnimationUtils.loadAnimation(this, R.anim.translate_animation);
		break;
	case R.id.scale:
		anim=AnimationUtils.loadAnimation(this, R.anim.scale_animation);
		break;
	case R.id.rotate:
		anim=AnimationUtils.loadAnimation(this, R.anim.rotate_animation);
		break;
	case R.id.setall:
		anim=AnimationUtils.loadAnimation(this, R.anim.set_animation);
		break;


	}
		imageView.startAnimation(anim);
	}

}
