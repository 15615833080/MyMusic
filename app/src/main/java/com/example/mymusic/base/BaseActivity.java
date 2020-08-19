package com.example.mymusic.base;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mymusic.R;


public class BaseActivity extends AppCompatActivity {

    private ImageView navBack, navMe;
    private TextView navTitle;

    /**
     * 初始化navBar
     * @param isShowBack
     * @param title
     * @param isShowMe
     */
    public void initNavBar(boolean isShowBack, String title, boolean isShowMe){
        navBack = findViewById(R.id.iv_back);
        navTitle = findViewById(R.id.tv_title);
        navMe = findViewById(R.id.iv_me);
        navBack.setVisibility(isShowBack ? View.VISIBLE : View.GONE);
        navMe.setVisibility(isShowMe ? View.VISIBLE : View.GONE);
        navTitle.setText(title);

        navBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}