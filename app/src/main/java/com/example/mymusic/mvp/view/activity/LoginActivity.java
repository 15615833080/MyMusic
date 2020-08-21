package com.example.mymusic.mvp.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.ActivityUtils;
import com.example.mymusic.R;
import com.example.mymusic.base.BaseActivity;
import com.example.mymusic.mvp.presenter.InputPresenter;
import com.example.mymusic.mvp.presenter.InputPresenterImpl;
import com.example.mymusic.mvp.view.views.InputView;
import com.example.mymusic.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    private static final String TAG = "LoginActivity";
    @BindView(R.id.login_phone)
    InputView loginPhone;
    @BindView(R.id.login_passwod)
    InputView loginPasswod;
    @BindView(R.id.login_login)
    Button loginLogin;
    @BindView(R.id.login_register)
    Button loginRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LogUtils.d(TAG, "DD");
        ButterKnife.bind(this);
        init();
    }

    /**
     * 初始化View
     */
    private void init() {
        initNavBar(false, "登录", false);
        inputPresenter = new InputPresenterImpl(this);
    }

    @OnClick({R.id.login_login, R.id.login_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_login:
                inputPresenter.handleInput(loginPhone.getInputStr(), loginPasswod.getInputStr());
                break;
            case R.id.login_register:
                ActivityUtils.startActivity(RegisterActivity.class);
                break;
        }
    }
}
