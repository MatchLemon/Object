package com.arcmenu2.test_arcmenu.view;

import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

public class ArcMenu extends ViewGroup implements OnClickListener {

	private static final int POS_LEF_TOP = 0;
	private static final int POS_LEF_BOTTOM = 1;
	private static final int POS_RIGHT_TOP = 2;
	private static final int POS_RIGHT_BOTTOM = 3;
	Position mPosition = Position.RIGHT_BOTTOM;
	Status mCurrentStatus = Status.CLOSE;
	int mRadius;
	private onClickButton buttonListener;

	public void setButtonListener(onClickButton buttonListener) {
		this.buttonListener = buttonListener;
	}

	View mcButton;

	public enum Status {

		OPEN, CLOSE
	}

	public enum Position {

		LEFT_TOP, LEFT_BOTTOM, RIGHR_TOP, RIGHT_BOTTOM
	}

	public ArcMenu(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mRadius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				100, getResources().getDisplayMetrics());
		// 获取自定义属性
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
				com.arcmenu2.test_arcmenu.R.styleable.ArcMenu, defStyle, 0);

		int pos = a.getInt(
				com.arcmenu2.test_arcmenu.R.styleable.ArcMenu_position,
				POS_RIGHT_BOTTOM);
		switch (pos) {
		case POS_LEF_TOP:
			mPosition = Position.LEFT_TOP;
			break;
		case POS_LEF_BOTTOM:
			mPosition = Position.LEFT_BOTTOM;
			break;
		case POS_RIGHT_TOP:
			mPosition = Position.RIGHR_TOP;
			break;
		case POS_RIGHT_BOTTOM:
			mPosition = Position.RIGHT_BOTTOM;
			break;

		}
		mRadius = (int) a.getDimension(
				com.arcmenu2.test_arcmenu.R.styleable.ArcMenu_radius,
				TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100,
						getResources().getDisplayMetrics()));

		a.recycle();

	}

	public ArcMenu(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
	}

	public ArcMenu(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}

	// 菜单动画
	private void menuIteamAnim(int pos, View v) {
		int count = getChildCount();
		for (int i = 0; i < count - 1; i++) {
			View childView = getChildAt(i+1);
			if (pos == i) {
				childView.startAnimation(scaleBigAnim(300));
				
			} else {
				

				childView.startAnimation(scaleSmallAnim(300));
			}
			childView.setClickable(false);
			childView.setFocusable(false);

		}
		changeState();
	}

	private Animation scaleSmallAnim(int duration) {
		AnimationSet animSet = new AnimationSet(true);
		ScaleAnimation scale = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		AlphaAnimation alpha=new AlphaAnimation(1.0f, 0.0f);
		
		animSet.addAnimation(scale);
		animSet.addAnimation(alpha);
		animSet.setDuration(duration);
		animSet.setFillAfter(true);
		return animSet;
	}

	private Animation scaleBigAnim(int duration) {
		AnimationSet animSet = new AnimationSet(true);
		ScaleAnimation scale = new ScaleAnimation(1.0f, 4.0f, 1.0f, 4.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		
		AlphaAnimation alpha=new AlphaAnimation(1.0f, 0.0f);
		animSet.addAnimation(scale);
		animSet.addAnimation(alpha);
		animSet.setDuration(duration);
		animSet.setFillAfter(true);
		return animSet;
	}

	@Override
	protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {
		if (arg0) {
			layoutCButton();

			int count = getChildCount();
			for (int i = 0; i < count - 1; i++) {

				final View child = getChildAt(i + 1);
				final int pos = i ;
				child.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						buttonListener.onClick(arg0, pos);
						menuIteamAnim(pos, child);
					}

				});
				child.setVisibility(View.GONE);
				int cWidth = child.getMeasuredWidth();
				int cHeight = child.getMeasuredHeight();
				int cl = (int) (mRadius * Math.sin(Math.PI / 2 / (count - 2)
						* i));
				int ct = (int) (mRadius * Math.cos(Math.PI / 2 / (count - 2)
						* i));
				// 按钮在底部
				if (mPosition == Position.LEFT_BOTTOM
						|| mPosition == Position.RIGHT_BOTTOM) {

					ct = getMeasuredHeight() - cHeight - ct;

				}
				// dang位子在右边
				if (mPosition == Position.RIGHR_TOP
						|| mPosition == Position.RIGHT_BOTTOM) {

					cl = getMeasuredWidth() - cWidth - cl;

				}
				child.layout(cl, ct, cl + cWidth, ct + cHeight);
			}

		}

	}

	private void layoutCButton() {
		mcButton = getChildAt(0);
		mcButton.setOnClickListener(this);
		int l = 0, t = 0;
		int width = mcButton.getMeasuredWidth();
		int height = mcButton.getMeasuredHeight();
		switch (mPosition) {
		case LEFT_TOP:
			l = 0;
			t = 0;
			break;
		case LEFT_BOTTOM:
			l = 0;
			t = getMeasuredHeight() - height;
			break;
		case RIGHR_TOP:
			l = getMeasuredWidth() - width;
			t = 0;
			break;
		case RIGHT_BOTTOM:
			t = getMeasuredHeight() - height;
			l = getMeasuredWidth() - width;
			break;

		}
		mcButton.layout(l, t, l + width, t + height);

	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int count = getChildCount();
		for (int i = 0; i < count; i++) {
			measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);

		}

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	public void onClick(View arg0) {
		// 主按钮旋转
		rotateCButton(arg0, 0f, 360f, 300);
		// 改变菜单状态
		toggleMenu(300);

	}

	private void toggleMenu(int duration) {
		int count = getChildCount();
		for (int i = 0; i < count - 1; i++) {
			final View childView = getChildAt(i + 1);
			childView.setVisibility(View.VISIBLE);
			AnimationSet animSet = new AnimationSet(true);
			Animation animTran = null;
			int cl = (int) (mRadius * Math.sin(Math.PI / 2 / (count - 2) * i));
			int ct = (int) (mRadius * Math.cos(Math.PI / 2 / (count - 2) * i));
			int xState = 1;
			int yState = 1;
			if (mPosition == Position.LEFT_TOP
					|| mPosition == Position.LEFT_BOTTOM) {
				xState = -1;

			}
			if (mPosition == Position.LEFT_TOP
					|| mPosition == Position.RIGHR_TOP) {
				yState = -1;
			}
			if (mCurrentStatus == Status.CLOSE) {

				animTran = new TranslateAnimation(cl * xState, 0, ct * yState,
						0);
				childView.setClickable(true);
				childView.setFocusable(true);

			} else {

				animTran = new TranslateAnimation(0, cl * xState, 0, ct
						* yState);
				childView.setClickable(false);
				childView.setFocusable(false);
			}
			animTran.setDuration(duration);
			animTran.setFillAfter(true);
			animTran.setAnimationListener(new AnimationListener() {

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
					if (mCurrentStatus == Status.CLOSE) {
						childView.setVisibility(View.GONE);
					}

				}
			});

			Animation rotateAnim = new RotateAnimation(0, 720,
					Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
			rotateAnim.setDuration(duration);
			rotateAnim.setFillAfter(true);
			animSet.addAnimation(rotateAnim);
			animSet.addAnimation(animTran);
			animSet.setStartOffset((i * 100) / count);
			childView.startAnimation(animSet);

		}
		changeState();

	}

	private void changeState() {
		mCurrentStatus = (mCurrentStatus == Status.OPEN) ? Status.CLOSE
				: Status.OPEN;

	}

	private void rotateCButton(View v, float f, float g, int i) {
		RotateAnimation anim = new RotateAnimation(f, g,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		anim.setDuration(i);
		anim.setFillAfter(true);
		v.setAnimation(anim);

	}

	public interface onClickButton {
		public void onClick(View view, int postion);
	}

}
