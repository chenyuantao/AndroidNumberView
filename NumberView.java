package com.tao.view;

import java.text.DecimalFormat;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class NumberView extends TextView {

	private float mStartNumber;
	private float mEndNumber;
	private long mDuration;
	private DecimalFormat mDecimalFormat;
	private boolean isRunning = false;

	public NumberView(Context context) {
		super(context);
	}

	public NumberView(Context context, AttributeSet attr) {
		super(context, attr);
	}

	public NumberView(Context context, AttributeSet attr, int defStyle) {
		super(context, attr, defStyle);
	}

	public void start(float startNumber, float endNumber, long duration) {
		if(!isRunning){
			isRunning=true;
			mStartNumber = startNumber;
			mEndNumber = endNumber;
			mDuration = duration;
			mDecimalFormat = new DecimalFormat(".00");
			runAnimator();
		}		
	}

	public void start(float startNumber, float endNumber, long duration,
			String pattern) {
		if(!isRunning){
			isRunning=true;
			mStartNumber = startNumber;
			mEndNumber = endNumber;
			mDuration = duration;
			mDecimalFormat = new DecimalFormat(pattern);
			runAnimator();
		}		
	}

	private void runAnimator() {
		ValueAnimator valueAnimator = ValueAnimator.ofFloat(mStartNumber,
				mEndNumber);
		valueAnimator.setDuration(mDuration);
		valueAnimator.addUpdateListener(new AnimatorUpdateListener() {

			@Override
			public void onAnimationUpdate(ValueAnimator va) {
				Float f = Float.parseFloat(va.getAnimatedValue().toString());
				if(f.floatValue()==mEndNumber){
					isRunning=false;
				}
				setText(mDecimalFormat.format(f.floatValue()));
			}
		});
		valueAnimator.start();
	}
}
