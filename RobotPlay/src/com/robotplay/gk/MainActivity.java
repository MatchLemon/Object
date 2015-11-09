package com.robotplay.gk;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.robotplay.gk.bean.ChatMessage;
import com.robotplay.gk.bean.ChatMessage.Type;
import com.robotplay.gk.util.Util;

public class MainActivity extends ActionBarActivity {

	public ListView mListview;
	public chatMessageAdapt mAdape;
	private List<ChatMessage> mData;
	public EditText mInputMsg;
	public Button sendBtn;
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			ChatMessage cmsg = (ChatMessage) msg.obj;
			mData.add(cmsg);
			mAdape.notifyDataSetChanged();
			mListview.setSelection(View.FOCUS_DOWN);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fragment_main);
		
		
		initView();
		initData();// 初始化数据
		initListener();

	}

	private void initListener() {
		sendBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				final String msg = mInputMsg.getText() + "";
				mInputMsg.setText("");
				if(TextUtils.isEmpty(msg)){
					Toast.makeText(MainActivity.this, "消息不能为空", Toast.LENGTH_SHORT)
					.show();
				}else{
					
				
				ChatMessage chatMessage = new ChatMessage(msg, Type.OUTCOMIND,
						new Date());
				mData.add(chatMessage);
				mAdape.notifyDataSetChanged();
				mListview.setSelection(View.FOCUS_DOWN);
				new Thread() {

					@Override
					public void run() {
						ChatMessage chatMessage = Util.getChatMessage(msg);
						Message message = Message.obtain();
						message.obj = chatMessage;
						handler.sendMessage(message);
					}
				}.start();
			
				}
			}
		});
		
	}

	private void initData() {
		mData = new ArrayList<ChatMessage>();
		ChatMessage chatMessage = new ChatMessage("您好，我是过客", Type.INCOMING,
				new Date());
		mData.add(chatMessage);
//
//		ChatMessage chatMessage1 = new ChatMessage("您好", Type.OUTCOMIND,
//				new Date());
//		mData.add(chatMessage1);

		mAdape = new chatMessageAdapt(MainActivity.this, mData);
		mListview.setAdapter(mAdape);
	}

	private void initView() {
		mListview = (ListView) findViewById(R.id.id_listview);
		mInputMsg = (EditText) findViewById(R.id.id_edit);
		sendBtn = (Button) findViewById(R.id.id_to_msg_btu);

	}

}
