package com.example.mymusic.mvp.view.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

public class CustomViewPager extends ViewPager {
    private boolean mIsSlidingEnable = true;

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return this.mIsSlidingEnable && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return this.mIsSlidingEnable && super.onInterceptTouchEvent(ev);
    }

    public void setSlidingEnable(boolean isSlidingEnable){
        this.mIsSlidingEnable = isSlidingEnable;
    }

}
