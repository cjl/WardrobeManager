package com.eb.wardrobemanager.ui;

import java.util.Timer;
import java.util.TimerTask;

import com.eb.wardrobemanager.R;
import com.eb.wardrobemanager.slidingmenu.SlidingMenu;
import com.eb.wardrobemanager.ui.base.BaseSlidingFragmentActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.Toast;

public class MainAct extends BaseSlidingFragmentActivity implements
		OnClickListener, AnimationListener {

	private final String LIST_TEXT = "text";
	private final String LIST_IMAGEVIEW = "img";

	private SlidingMenu sm;
	/**连续按两次返回键就退出*/
	private int keyBackClickCount=0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		initSlidingMenu();
		setContentView(R.layout.act_main);
	}

	
	@Override
	protected void onResume() {
		super.onResume();
		keyBackClickCount=0;
	}
	

	@Override
	public void onAnimationEnd(Animation arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationRepeat(Animation arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationStart(Animation arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
//		if (keyCode == KeyEvent.KEYCODE_BACK) {
//			switch (keyBackClickCount++) {
//				case 0:
//					Toast.makeText(this,
//							getResources().getString(R.string.press_again_exit),
//							Toast.LENGTH_SHORT).show();
//					Timer timer = new Timer();
//					timer.schedule(new TimerTask() {
//						@Override
//						public void run() {
//							keyBackClickCount=0;
//						}
//					}, 3000);
//					break;
//				case 1:
//					break;
//				default:
//					break;
//			}
//			return true;
//		} else 
			if (keyCode == KeyEvent.KEYCODE_MENU) {

			if (sm.isMenuShowing()) {
				toggle();
			} else {
				showMenu();
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	private boolean mIsAnim = false;
	private float lastX = 0;
	private float lastY = 0;
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		super.dispatchTouchEvent(event);
		if (mIsAnim ) {
			return false;
		}
		final int action = event.getAction();

		float x = event.getX();
		float y = event.getY();

		switch (action) {
		case MotionEvent.ACTION_DOWN:
			lastY = y;
			lastX = x;
			return false;
		case MotionEvent.ACTION_MOVE:
			float dY = Math.abs(y - lastY);
			float dX = Math.abs(x - lastX);
			boolean down = y > lastY ? true : false;
			lastY = y;
			lastX = x;
            
			if (dX < 8 && dY > 8  && !down) {
				Animation anim = AnimationUtils.loadAnimation(
						MainAct.this, R.anim.push_top_in);
				anim.setAnimationListener(MainAct.this);
			} else if (dX < 8 && dY > 8  && down) {
				Animation anim = AnimationUtils.loadAnimation(
						MainAct.this, R.anim.push_top_out);
				anim.setAnimationListener(MainAct.this);
			} else {
				return false;
			}
			mIsAnim = true;
			break;
		default:
			return false;
		}
		return false;
	}  
	
	/**
	 * 初始化SlidingMenu
	 */
	private void initSlidingMenu() {
		setBehindContentView(R.layout.behind_slidingmenu);
		// customize the SlidingMenu
		sm = getSlidingMenu();
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		// sm.setFadeDegree(0.35f);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		sm.setShadowDrawable(R.drawable.slidingmenu_shadow);
		sm.setShadowWidth(20);
		// sm.setBehindScrollScale(0.333f);
	}

}
