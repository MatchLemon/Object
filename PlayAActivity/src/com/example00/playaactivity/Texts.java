package com.example00.playaactivity;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.TextView;

import com.example00.playaactivity.SecondActivity.SendMsg;

public class Texts implements SendMsg ,Parcelable{
	TextView view;
	public Texts() {
		// TODO Auto-generated constructor stub
	}
	public Texts(TextView view){
		this.view=view;
	}
	@Override
	public void onSendMessage(String str) {
		view.setText(str);

	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

}
