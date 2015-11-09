package com.playgame.model;

public class MusicBean {

	String musicName;
	String musicFileName;
	int namaSize;
	char musicChar[];
	public char[] getMusicChar() {
		return musicChar;
	}
	public void setMusicChar(char[] musicChar) {
		this.musicChar = musicChar;
	}
	public String getMusicName() {
		return musicName;
	}
	public void setMusicName(String musicName) {
		this.musicName = musicName;
		this.namaSize=musicName.length();
		musicChar=musicName.toCharArray();
	}
	public String getMusicFileName() {
		return musicFileName;
	}
	public void setMusicFileName(String musicFileName) {
		this.musicFileName = musicFileName;
	}
	public int getNamaSize() {
		return namaSize;
	}
	public void setNamaSize(int namaSize) {
		this.namaSize = namaSize;
	}

}
