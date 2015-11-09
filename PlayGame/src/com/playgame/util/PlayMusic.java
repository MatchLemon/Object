package com.playgame.util;

import java.io.IOException;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;

/**
 * ���ֲ�����
 * 
 * @author zhang
 * 
 */
public class PlayMusic {

	// ��Ч����
	public static int MUSUC_ENTER = 0;
	public static int MUSUC_CANCEL = 1;
	public static int MUSUC_COIN = 2;
	// ��Ч��������
	public static String MUSIC_NAME[] = { "enter.mp3", "cancel.mp3", "coin.mp3" };
	public static MediaPlayer[] mediaPalyers = new MediaPlayer[MUSIC_NAME.length];
	// mediaPlay �������ֵĲ�����
	public static MediaPlayer mediaPlayer;

	public static void playTone(Context context, int indext) {
		if (mediaPalyers[indext] == null) {
			mediaPalyers[indext] = new MediaPlayer();
			AssetManager asset = context.getAssets();
			try {
				AssetFileDescriptor fd = asset.openFd(MUSIC_NAME[indext]);
				mediaPalyers[indext].setDataSource(fd.getFileDescriptor(),
						fd.getStartOffset(), fd.getLength());
				mediaPalyers[indext].prepare();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		mediaPalyers[indext].start();
	}

	public static void playMusic(Context context, String musicFileName) {
		AssetManager assetM = context.getAssets();
		if (mediaPlayer == null) {
			mediaPlayer = new MediaPlayer();
		}
		mediaPlayer.reset();
		try {
			AssetFileDescriptor fileDescriptor = assetM.openFd(musicFileName);
			mediaPlayer
					.setDataSource(fileDescriptor.getFileDescriptor(),
							fileDescriptor.getStartOffset(),
							fileDescriptor.getLength());

			mediaPlayer.prepare();
			mediaPlayer.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * ֹͣ�������ڲ��ŵ�����
	 */
	public static void stopMusic() {

		if (mediaPlayer != null) {
			mediaPlayer.stop();
		}

	}
}
