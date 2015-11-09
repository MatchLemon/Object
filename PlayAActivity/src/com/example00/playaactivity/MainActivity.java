package com.example00.playaactivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example00.playaactivity.SecondActivity.SendMsg;

public class MainActivity extends ActionBarActivity {

	Button seconButton;
	EditText editText;
	SendMsg msgX;
	TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		seconButton = (Button) findViewById(R.id.button1);
		editText = (EditText) findViewById(R.id.editor_id);
		textView = (TextView) findViewById(R.id.textView2);
		testTnterfaceSendMessage();
		seconButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String msg = editText.getText() + "";
				Intent intent = new Intent(MainActivity.this,
						SecondActivity.class);

				Bundle bundle = new Bundle();

				bundle.putString("msg", msg);
				intent.putExtras(bundle);
				startActivityForResult(intent, 0);
//				startActivity(intent);

			}
		});

	}

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		// TODO Auto-generated method stub
		textView.setText(arg2.getStringExtra("msg"));
		super.onActivityResult(arg0, arg1, arg2);
		
	}
	
	
	private void testTnterfaceSendMessage() {
		 msgX = new SendMsg () {
		
		 @Override
		 public void onSendMessage(String str) {
		 textView.setText(str);
		
		 }
		 };


	}

}
