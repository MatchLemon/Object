package com.arcmenu2.test_arcmenu;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.arcmenu2.test_arcmenu.view.ArcMenu;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fragment_main);
	ArcMenu	 mview=(ArcMenu) findViewById(R.id.id_ArcMenu); 
		mview.setButtonListener(new ArcMenu.onClickButton() {
			
			@Override
			public void onClick(View view, int postion) {
			Toast.makeText(MainActivity.this, view.getTag()+"", Toast.LENGTH_SHORT).show();
				
			}
		});
	}

}
