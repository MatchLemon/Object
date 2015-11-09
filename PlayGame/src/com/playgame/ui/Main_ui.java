package com.playgame.ui;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout.LayoutParams;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.playgame.interf.DialogAlterListener;
import com.playgame.interf.MyGuidButtonListener;
import com.playgame.model.MusicBean;
import com.playgame.model.MusicData;
import com.playgame.model.MyGridButton;
import com.playgame.model.MyGridView;
import com.playgame.util.PlayMusic;
import com.playgame.util.Util;

/**
 * @author zhang
 * 
 */

public class Main_ui extends Activity implements OnClickListener,
		MyGuidButtonListener {

	Animation mPanAnim;
	LinearInterpolator mPamlin;

	Animation mBarInAnim;
	LinearInterpolator mBarIntlin;

	Animation mBarOutAnim;
	LinearInterpolator mPamOutlin;

	ImageView mPanView;
	ImageView BarView;
	ImageButton playButton;

	MyGridView myGridView;
	ArrayList<MyGridButton> listButton;

	LinearLayout selectContainer;// 选择框布局
	// 当前关的索引
	private int mCurrentStageIndex = -1;

	MusicBean bean;// 当前播放音乐信息
	ArrayList<MyGridButton> selectBu;// 选择按钮 答案按钮。

	// 音乐界面关数
	TextView mCurrentStageView;
	// 当前关的索引
	TextView mCurrentStagePassView;
	// 当前歌曲名字
	TextView mCurrentSongNamePassView;
	// 过关界面
	LinearLayout passView;
	// 过关 界面下一关按钮
	ImageButton nextStagePassView;
	// 过关界面分享按钮
	ImageButton shareCountToWeiXinPassView;
	ImageButton mShareButton;// 分享按钮
	ImageButton mDeleteButton;// 删除按钮
	ImageButton mTipButton;// 提示按钮
	public static int currentGlodCount = 1000;// 当前金币数量
	TextView mCurrentGlodView;// 金币显示控件
	ArrayList<MyGridButton> currentList;
	public final static int ANSWER_WRONG = 1;
	public final static int ANSWER_RIGHT = 2;
	public final static int ANSWER_GONON = 3;
	// 退出应用
	ImageButton exitButton;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		int[] data = Util.loadData(Main_ui.this);
		mCurrentStageIndex = data[0];
		currentGlodCount = data[1];
		init();
		initSelectWord();// 初始化备选文字
		passViewButtonEvevt();// 过关界面按钮功能

	}

	@Override
	protected void onPause() {
		mPanView.clearAnimation();
		Util.saveData(Main_ui.this, mCurrentStageIndex - 1, currentGlodCount);
		PlayMusic.stopMusic();
		super.onPause();
	}

	private void init() {
		mPanView = (ImageView) findViewById(R.id.imageView2);
		BarView = (ImageView) findViewById(R.id.imageView4);
		playButton = (ImageButton) findViewById(R.id.play);
		mCurrentGlodView = (TextView) findViewById(R.id.glod_count);
		nextStagePassView = (ImageButton) findViewById(R.id.next_problem);
		shareCountToWeiXinPassView = (ImageButton) findViewById(R.id.share_weixin);
		selectContainer = (LinearLayout) findViewById(R.id.word_select_container);
		mShareButton = (ImageButton) findViewById(R.id.share_button);
		mTipButton = (ImageButton) findViewById(R.id.tip_button);
		mDeleteButton = (ImageButton) findViewById(R.id.delete_button);
		exitButton = (ImageButton) findViewById(R.id.imageButton1);
		mPanAnim = AnimationUtils.loadAnimation(this, R.anim.rotete_anim);
		mPamlin = new LinearInterpolator();
		mPanAnim.setInterpolator(mPamlin);
		mPanAnim.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation arg0) {
				BarView.startAnimation(mBarOutAnim);
			}
		});

		mBarInAnim = AnimationUtils.loadAnimation(this, R.anim.rotate_in);
		mBarIntlin = new LinearInterpolator();
		mBarInAnim.setInterpolator(mBarIntlin);
		mBarInAnim.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation arg0) {
				mPanView.startAnimation(mPanAnim);

			}
		});
		mBarOutAnim = AnimationUtils.loadAnimation(this, R.anim.rotate_out);
		mPamOutlin = new LinearInterpolator();
		mBarOutAnim.setInterpolator(mPamOutlin);
		mBarOutAnim.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation arg0) {
				playButton.setVisibility(View.VISIBLE);

			}
		});
		playButton.setOnClickListener(this);

		// 删除按钮功能实现
		mDeleteButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Util.showDialogAlter(Main_ui.this, "就不告诉你", butDelete);

			}
		});
		// 分享按钮功能实现
		mShareButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

			}
		});
		// 提示按钮功能
		mTipButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Util.showDialogAlter(Main_ui.this, "扣30金币，但是没有提示！", butDelete);
				manageGlod(-30);

			}
		});
		exitButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				exit();
			}
		});
	}

	/**
	 * 
	 * @param view
	 * @param count
	 */
	public void manageGlod(int count) {
		if ((currentGlodCount + count) > 0) {
			currentGlodCount += count;
			mCurrentGlodView.setText(currentGlodCount + "");
		} else {

			Toast.makeText(this, "金币不足请充值", 200).show();
		}

	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.play:
			// Toast.makeText(this, "点击", Toast.LENGTH_SHORT).show();
			handlBarin();
			break;

		}

	}

	public void handlBarin() {
		BarView.startAnimation(mBarInAnim);
		playButton.setVisibility(View.INVISIBLE);
		PlayMusic.playMusic(Main_ui.this, bean.getMusicFileName());

	}

	/**
	 * 初始化选择 框数据
	 * 
	 * @return
	 */
	public ArrayList<MyGridButton> initAllWord(String[] word) {
		currentList = new ArrayList<MyGridButton>();

		// 打乱答案
		for (int i = 0; i < bean.getNamaSize(); i++) {
			String str;
			Random random = new Random();
			int ran = random.nextInt(24);
			str = word[ran];
			word[ran] = word[i];
			word[i] = str;
		}

		for (int i = 0; i < 24; i++) {
			MyGridButton button = new MyGridButton();
			button.mWordString = word[i];
			currentList.add(button);
		}
		return currentList;

	}

	public String[] prepareWord(int current) {
		String str[] = MusicData.MUSIC_DATA[current];
		String words[] = new String[MyGridView.COUNTS_WORDS];
		bean = new MusicBean();
		bean.setMusicFileName(str[0]);
		bean.setMusicName(str[1]);
		for (int i = 0; i < bean.getNamaSize(); i++) {
			words[i] = bean.getMusicChar()[i] + "";
		}
		for (int i = bean.getNamaSize(); i < MyGridView.COUNTS_WORDS; i++) {
			words[i] = getRandomWord() + "";
		}

		return words;

	}

	/**
	 * *********************************************
	 * 
	 * 选着模块的创建 文字框数据的初始化
	 */
	public void initSelectWord() {
		// currentGlodCount=Integer.parseInt(mCurrentGlodView.getText()+"");
		mCurrentGlodView.setText(currentGlodCount + "");
		String word[] = prepareWord(++mCurrentStageIndex);
		selectBu = selectButton();

		selectContainer.removeAllViews();
		mCurrentStageView = (TextView) findViewById(R.id.guan_count);
		mCurrentStageView.setText((mCurrentStageIndex + 1) + "");
		LayoutParams params = new LayoutParams(140, 140);
		for (int i = 0; i < selectBu.size(); i++) {
			selectContainer.addView(selectBu.get(i).mViewButton, params);

		}

		listButton = initAllWord(word);
		myGridView = (MyGridView) findViewById(R.id.sel_con);
		myGridView.addMyGuidButtonListener(this);
		myGridView.updateData(listButton);

	}

	/**
	 * 初始化待选框
	 * 
	 */
	public ArrayList<MyGridButton> selectButton() {
		ArrayList<MyGridButton> arrayList = new ArrayList<MyGridButton>();
		for (int i = 0; i < bean.getNamaSize(); i++) {
			final MyGridButton button = new MyGridButton();
			View v = (View) Util.getView(Main_ui.this, R.layout.select_button);
			button.mViewButton = (Button) v.findViewById(R.id.item_but);

			button.mViewButton.setTextColor(Color.WHITE);
			button.mViewButton.setText("");
			button.mViewButton.setBackgroundResource(R.drawable.game_wordblank);
			button.mViewButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					selectButtonFunction(button);
					changeTextColor(true);
				}
			});
			arrayList.add(button);
		}

		return arrayList;

	}

	/**
	 * 点击已选按钮事件
	 * 
	 * @param myGridButton
	 */
	public void selectButtonFunction(MyGridButton myGridButton) {
		myGridButton.mWordString = "";
		myGridButton.mViewButton.setText("");

		setButtonVisibility(listButton.get(myGridButton.mIndex), View.VISIBLE);

	}

	/**
	 * 通过观察者模式实现按钮的点击事件
	 */
	@Override
	public void onMyGuidButtonListener(MyGridButton myGridButton) {

		// Toast.makeText(this, myGridButton.mIndex + "", 100).show();
		buttonFunction(myGridButton);// 按钮动态隐藏
		selectedState();// 检查答案的正确性，以及错误的处理状态
	}

	/**
	 * 答案处的状态
	 */
	public void selectedState() {
		switch (answerState()) {
		case ANSWER_RIGHT: // 正确答案
			handlePassEvent();

			break;
		case ANSWER_WRONG:// 错误的时候
			answerWrong();
			break;
		}
	}

	/**
	 * 处理错误的答案
	 */
	private void answerWrong() {

		TimerTask task = new TimerTask() {
			boolean clolrState = false;
			int spartTime = 0;

			public void run() {
				runOnUiThread(new Runnable() {
					public void run() {
						if (++spartTime > 7) {
							return;
						}

						changeTextColor(clolrState);

						clolrState = !clolrState;
					}
				});

			}

		};
		Timer timer = new Timer();
		timer.schedule(task, 1, 150);

	}

	public void changeTextColor(boolean clolrState) {
		for (int j = 0; j < selectBu.size(); j++) {
			selectBu.get(j).mViewButton.setTextColor((clolrState ? Color.WHITE
					: Color.RED));

		}

	}

	/**
	 * 检查答案是否正确
	 * 
	 * @return
	 */
	public int answerState() {
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < selectBu.size(); i++) {

			if (("").equals(selectBu.get(i).mViewButton.getText())) {

				return ANSWER_GONON;
			}

			str.append(selectBu.get(i).mViewButton.getText());
		}
		if (str.toString().equals(bean.getMusicName())) {
			return ANSWER_RIGHT;
		} else {
			return ANSWER_WRONG;
		}
	}

	/**
	 * 按钮功能实现，当按下是按钮隐藏
	 * 
	 * @param myGridButton
	 */
	public void buttonFunction(MyGridButton myGridButton) {

		for (int i = 0; i < selectBu.size(); i++) {
			if (selectBu.get(i).mWordString.length() == 0) {
				selectBu.get(i).mViewButton.setText(myGridButton.mWordString);
				selectBu.get(i).mWordString = myGridButton.mWordString;
				selectBu.get(i).mIsVisiable = true;
				selectBu.get(i).mIndex = myGridButton.mIndex;

				setButtonVisibility(myGridButton, View.INVISIBLE);
				break;
			}

		}

	}

	/**
	 * 设置按钮是否可见
	 * 
	 * @param myGridButton
	 * @param stage
	 */
	public void setButtonVisibility(MyGridButton myGridButton, int visibility) {
		myGridButton.mViewButton.setVisibility(visibility);
		myGridButton.mIsVisiable = (visibility == View.VISIBLE) ? true : false;

	}

	/**
	 * 得到随机汉字
	 * 
	 * @return
	 */
	public char getRandomWord() {
		String str = "";
		int hightPos;
		int lowPos;
		Random ramdom = new Random();
		hightPos = 176 + Math.abs(ramdom.nextInt(36));
		lowPos = 161 + Math.abs(ramdom.nextInt(93));
		byte wordchar[] = new byte[2];
		wordchar[0] = (byte) hightPos;
		wordchar[1] = (byte) lowPos;
		try {
			str = new String(wordchar, "GBK");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str.charAt(0);

	}

	/**
	 * 处理过关事件
	 */
	public void handlePassEvent() {
		mPanView.clearAnimation();
		PlayMusic.stopMusic();
		passView = (LinearLayout) findViewById(R.id.pass_view);
		passView.setVisibility(View.VISIBLE);
		PlayMusic.playTone(Main_ui.this, PlayMusic.MUSUC_COIN);
		mCurrentSongNamePassView = (TextView) findViewById(R.id.current_answer);
		if (mCurrentSongNamePassView != null) {

			mCurrentSongNamePassView.setText(bean.getMusicName());
		}
		mCurrentStagePassView = (TextView) findViewById(R.id.pass_count);
		if (mCurrentStagePassView != null) {

			mCurrentStagePassView.setText((mCurrentStageIndex + 1) + "");
		}

	}

	/**
	 * 过关界面 按钮功能实现及注册
	 */
	public void passViewButtonEvevt() {
		// 下一关功能实现
		nextStagePassView.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				if(cheekPassStage()){
					
				//通关***************
					/*
					 * ***********
					 * **************
					 * 
					 * 
					 * */
			
				}
			
				initSelectWord();
				passView.setVisibility(view.INVISIBLE);
			}
		});

		// 分享功能实现
		shareCountToWeiXinPassView.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {

			}
		});
	
	}
	public boolean cheekPassStage(){
		
		if((mCurrentStageIndex+1)==MusicData.MUSIC_DATA.length){
			
			mCurrentStageIndex=-1;
			return true;
		}else{
			return false;
		}
	
		
		
	}

	// 删除提示
	DialogAlterListener butDelete = new DialogAlterListener() {

		@Override
		public void onclick() {

		}
	};
	// 提示按钮
	DialogAlterListener butTip = new DialogAlterListener() {

		@Override
		public void onclick() {
			// TODO Auto-generated method stub

		}
	};
	//
	DialogAlterListener lackCoin = new DialogAlterListener() {

		@Override
		public void onclick() {
			

		}
	};

	public void exit() {
//		System.exit(0);
		finish();
	}

}
