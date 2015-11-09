package com.playgame.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.playgame.interf.DialogAlterListener;
import com.playgame.model.MusicData;
import com.playgame.ui.Main_ui;
import com.playgame.ui.R;

public class Util {

	public static AlertDialog alertDialog;

	public static String SAVE_DATA_NAME = "musicdata.dat";

	public static View getView(Context context, int layoutId) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(layoutId, null);
		return view;
	}

	public static void showDialogAlter(final Context context, String message,
			final DialogAlterListener listener) {

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		View alertView = getView(context, R.layout.dialog_sure_cancel);

		ImageButton mSureButton = (ImageButton) alertView
				.findViewById(R.id.sure_button);
		ImageButton mCancelButton = (ImageButton) alertView
				.findViewById(R.id.cancel_button);
		// 设置信息
		TextView message1 = (TextView) alertView.findViewById(R.id.message_id);
		message1.setText(message);
		// 设置弹出框
		builder.setView(alertView);
		alertDialog = builder.create();
		alertDialog.show();
		// 确定按钮功能实现
		mSureButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (alertDialog != null) {

					listener.onclick();

					alertDialog.cancel();
					PlayMusic.playTone(context, PlayMusic.MUSUC_CANCEL);
				}

			}
		});
		// 取消按钮功能
		mCancelButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (alertDialog != null) {

					alertDialog.cancel();
					PlayMusic.playTone(context, PlayMusic.MUSUC_ENTER);
				}
			}
		});
	}

	/**
	 * 保存数据
	 * 
	 * @param context
	 * @param stageIndex
	 * @param coin
	 */
	public static void saveData(Context context, int stageIndex, int coin) {
		FileOutputStream fos = null;

		try {
			fos = context
					.openFileOutput(SAVE_DATA_NAME, context.MODE_PRIVATE);
			DataOutputStream dos = new DataOutputStream(fos);
			dos.writeInt(stageIndex);
			dos.writeInt(coin);
			loadData(context);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 读取数据
	 * 
	 * @param context
	 * @return
	 */
	public static int[] loadData(Context context) {
		FileInputStream fis = null;
		int data[]= { -1, Main_ui.currentGlodCount };
		try {
			fis = context.openFileInput(SAVE_DATA_NAME);
			DataInputStream dos = new DataInputStream(fis);
			data[0] = dos.readInt();
			data[1] = dos.readInt();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return data;

	}
}
