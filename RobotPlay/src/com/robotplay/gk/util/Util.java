package com.robotplay.gk.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

import com.google.gson.Gson;
import com.robotplay.gk.bean.ChatMessage;
import com.robotplay.gk.bean.ChatMessage.Type;
import com.robotplay.gk.bean.Result;

public class Util {

	public static String API_URL = "http://www.tuling123.com/openapi/api";
	public static String API_KEY = "93ea84e72852a1c13b1ef8829e3bb56f";

	/**
	 * 发送消息 ，接受消息
	 * 
	 * @param msg
	 * @return
	 */
	public static ChatMessage getChatMessage(String msg) {
		ChatMessage chatMessage = new ChatMessage();
//		JSONObject ob = JSONObject.fromObject(doGet(msg));
	Result result = null;
//		try {
//			result = (Result) JSONObject.toBean(ob, Result.class);
//			chatMessage.setMsg(result.getText());
//		} catch (Exception e) {
//			chatMessage.setMsg("服务异常");
//
//		}
		Gson gson=new Gson();
		result=gson.fromJson(doGet(msg),  Result.class);
		chatMessage.setMsg(result.getText());
		chatMessage.setType(Type.INCOMING);
		chatMessage.setDate(new Date());
		return chatMessage;
	}

	public static String doGet(String msg) {
		InputStream is = null;
		ByteArrayOutputStream bis = null;
		try {
			URL urll = new URL(getURL(msg));
			try {
				HttpURLConnection conn = (HttpURLConnection) urll
						.openConnection();
				conn.setReadTimeout(5 * 1000);
				conn.setConnectTimeout(5 * 1000);
				conn.connect();
				is = conn.getInputStream();
				byte[] buf = new byte[128];
				int len = -1;

				bis = new ByteArrayOutputStream();
				while ((len = is.read(buf)) != -1) {
					bis.write(buf, 0, len);
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {

				try {
					if (is != null) {
						is.close();
					}
					if (bis != null) {
						bis.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}

			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String result = new String(bis.toByteArray());
		return result;
	}

	public static String getURL(String msg) {
		String url = "";
		try {
			url = API_URL + "?key=" + API_KEY + "&info="
					+ URLEncoder.encode(msg, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;

	}
}
