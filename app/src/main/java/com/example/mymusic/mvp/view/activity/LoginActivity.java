package com.example.mymusic.mvp.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mymusic.R;
import com.example.mymusic.base.BaseActivity;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        initNavBar(false, "登录",false);
    }
}
