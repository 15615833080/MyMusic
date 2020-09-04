package com.example.mymusic.mvp.view.activity.impl;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.ToastUtils;
import com.example.mymusic.R;
import com.example.mymusic.base.BaseActivity;
import com.example.mymusic.base.BaseInternetActivity;
import com.example.mymusic.mvp.presenter.impl.InputPresenterImpl;
import com.example.mymusic.mvp.view.views.InputView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.register_phone)
    InputView registerPhone;
    @BindView(R.id.register_passwod)
    InputView registerPasswod;
    @BindView(R.id.register_passwod_again)
    InputView registerPasswodAgain;
    @BindView(R.id.register_register)
    Button registerRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initNavBar(true, "注册", false);
        inputPresenter = new InputPresenterImpl(this);
    }

    /**
     *1.输入合法性验证
     *      1.手机号合法
     *      2.密码和确认密码不为空且同
     *      3.输入手机号是否已经被注册
     * 2.存入Realm数据库（md5加密）
     */
    @OnClick({R.id.register_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_register:
                String mPhone = registerPhone.getInputStr();
                String mPassword = registerPasswod.getInputStr();
                String mPasswordCconfirm = registerPasswodAgain.getInputStr();
                inputPresenter.handleInputRegister(mPhone, mPassword, mPasswordCconfirm);
                ToastUtils.showShort("注册成功");
                break;
                default:break;
        }
    }
}
