package com.robotplay.gk.test;

import com.robotplay.gk.util.Util;

import android.test.AndroidTestCase;
import android.util.Log;

public class TestDoGet extends AndroidTestCase{
	
	public void httpUtil(){
	String msg=	Util.doGet("���ҽ���Ц��");
		Log.e("XXX", msg);
		String msg1=	Util.doGet("���ҽ���Ц��");
		Log.e("XXX", msg1);
		String msg2=	Util.doGet("���ҽ���Ц��");
		Log.e("XXX", msg2);
		String msg3=	Util.doGet("���ҽ���Ц��");
		Log.e("XXX", msg3);
		String msg4=	Util.doGet("���ҽ�������");
		Log.e("XXX", msg4);
	}

}
