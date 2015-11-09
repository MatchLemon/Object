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

	LinearLayout selectContainer;// ѡ��򲼾�
	// ��ǰ�ص�����
	private int mCurrentStageIndex = -1;

	MusicBean bean;// ��ǰ����������Ϣ
	ArrayList<MyGridButton> selectBu;// ѡ��ť �𰸰�ť��

	// ���ֽ������
	TextView mCurrentStageView;
	// ��ǰ�ص�����
	TextView mCurrentStagePassView;
	// ��ǰ��������
	TextView mCurrentSongNamePassView;
	// ���ؽ���
	LinearLayout passView;
	// ���� ������һ�ذ�ť
	ImageButton nextStagePassView;
	// ���ؽ������ť
	ImageButton shareCountToWeiXinPassView;
	ImageButton mShareButton;// ����ť
	ImageButton mDeleteButton;// ɾ����ť
	ImageButton mTipButton;// ��ʾ��ť
	public static int currentGlodCount = 1000;// ��ǰ�������
	TextView mCurrentGlodView;// �����ʾ�ؼ�
	ArrayList<MyGridButton> currentList;
	public final static int ANSWER_WRONG = 1;
	public final static int ANSWER_RIGHT = 2;
	public final static int ANSWER_GONON = 3;
	// �˳�Ӧ��
	ImageButton exitButton;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		int[] data = Util.loadData(Main_ui.this);
		mCurrentStageIndex = data[0];
		currentGlodCount = data[1];
		init();
		initSelectWord();// ��ʼ����ѡ����
		passViewButtonEvevt();// ���ؽ��水ť����

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

		// ɾ����ť����ʵ��
		mDeleteButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Util.showDialogAlter(Main_ui.this, "�Ͳ�������", butDelete);

			}
		});
		// ����ť����ʵ��
		mShareButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

			}
		});
		// ��ʾ��ť����
		mTipButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Util.showDialogAlter(Main_ui.this, "��30��ң�����û����ʾ��", butDelete);
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

			Toast.makeText(this, "��Ҳ������ֵ", 200).show();
		}

	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.play:
			// Toast.makeText(this, "���", Toast.LENGTH_SHORT).show();
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
	 * ��ʼ��ѡ�� ������
	 * 
	 * @return
	 */
	public ArrayList<MyGridButton> initAllWord(String[] word) {
		currentList = new ArrayList<MyGridButton>();

		// ���Ҵ�
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
	 * ѡ��ģ��Ĵ��� ���ֿ����ݵĳ�ʼ��
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
	 * ��ʼ����ѡ��
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
	 * �����ѡ��ť�¼�
	 * 
	 * @param myGridButton
	 */
	public void selectButtonFunction(MyGridButton myGridButton) {
		myGridButton.mWordString = "";
		myGridButton.mViewButton.setText("");

		setButtonVisibility(listButton.get(myGridButton.mIndex), View.VISIBLE);

	}

	/**
	 * ͨ���۲���ģʽʵ�ְ�ť�ĵ���¼�
	 */
	@Override
	public void onMyGuidButtonListener(MyGridButton myGridButton) {

		// Toast.makeText(this, myGridButton.mIndex + "", 100).show();
		buttonFunction(myGridButton);// ��ť��̬����
		selectedState();// ���𰸵���ȷ�ԣ��Լ�����Ĵ���״̬
	}

	/**
	 * �𰸴���״̬
	 */
	public void selectedState() {
		switch (answerState()) {
		case ANSWER_RIGHT: // ��ȷ��
			handlePassEvent();

			break;
		case ANSWER_WRONG:// �����ʱ��
			answerWrong();
			break;
		}
	}

	/**
	 * �������Ĵ�
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
	 * �����Ƿ���ȷ
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
	 * ��ť����ʵ�֣��������ǰ�ť����
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
	 * ���ð�ť�Ƿ�ɼ�
	 * 
	 * @param myGridButton
	 * @param stage
	 */
	public void setButtonVisibility(MyGridButton myGridButton, int visibility) {
		myGridButton.mViewButton.setVisibility(visibility);
		myGridButton.mIsVisiable = (visibility == View.VISIBLE) ? true : false;

	}

	/**
	 * �õ��������
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
	 * ��������¼�
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
	 * ���ؽ��� ��ť����ʵ�ּ�ע��
	 */
	public void passViewButtonEvevt() {
		// ��һ�ع���ʵ��
		nextStagePassView.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				if(cheekPassStage()){
					
				//ͨ��***************
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

		// ������ʵ��
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

	// ɾ����ʾ
	DialogAlterListener butDelete = new DialogAlterListener() {

		@Override
		public void onclick() {

		}
	};
	// ��ʾ��ť
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
