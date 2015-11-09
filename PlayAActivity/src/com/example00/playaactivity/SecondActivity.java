package com.example00.playaactivity;

import java.io.Serializable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends Activity {

	Button button;
	EditText editText;
	TextView textView;
	SendMsg sendMsg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_activevity);
		button = (Button) findViewById(R.id.sbutton1);
		editText = (EditText) findViewById(R.id.seditor_id);
		textView = (TextView) findViewById(R.id.stextView2);
		Intent intent = this.getIntent();
		String str = intent.getStringExtra("msg");
		textView.setText(str);
//		sendMsg = (SendMsg) intent.getSerializableExtra("sendMsg");
		button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
//				sendMsg.onSendMessage(editText.getText() + "");
				Intent data=new Intent();
				data.putExtra("msg",editText.getText() + "" );
				setResult(0, data);
				finish();
			}
		});
	}

	interface SendMsg extends Serializable {
		void onSendMessage(String str);
	}
}
