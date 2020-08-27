package com.example.mymusic.mvp.view.activity.impl;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mymusic.R;
import com.example.mymusic.base.BaseActivity;
import com.example.mymusic.mvp.presenter.impl.InputPresenterImpl;
import com.example.mymusic.mvp.view.views.InputView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePasswordActivity extends BaseActivity {

    @BindView(R.id.input_old_password)
    InputView inputOldPassword;
    @BindView(R.id.input_password)
    InputView inputPassword;
    @BindView(R.id.input_password_confirm)
    InputView inputPasswordConfirm;
    @BindView(R.id.confirm_go)
    Button confirmGo;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_me)
    ImageView ivMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initNavBar(true, "修改密码", false);
        inputPresenter = new InputPresenterImpl(this);
    }

    @OnClick(R.id.confirm_go)
    public void onViewClicked() {
        /**
         * 1.验证当前输入的密码
         *      1.验证旧密码是否为空
         *      2.验证新密码是否为空且是否一致
         *      3.验证旧密码是否和当前登录用户的密码匹配
         *          1、Realm 获取到当前登录的用户模型
         *          2、根据用户模型中保存的密码匹配用户原密码
         * 2.使用realm数据库进行修改
         */
        inputPresenter.handlerInputChangePassword(inputOldPassword.getInputStr(), inputPassword.getInputStr(), inputPasswordConfirm.getInputStr());
    }
}
