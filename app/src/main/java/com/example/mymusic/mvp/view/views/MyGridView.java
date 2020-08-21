package com.example.mymusic.mvp.view.views;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

public class MyGridView extends AppCompatImageView {

    public MyGridView(@NonNull Context context) {
        super(context);
    }

    public MyGridView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGridView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
//        获取View宽度
//        int width = MeasureSpec.getSize(widthMeasureSpec);
//        获取View模式
//        match_parent、warp_content、具体dp
//        int mode = MeasureSpec.getMode(widthMeasureSpec);
    }
}
