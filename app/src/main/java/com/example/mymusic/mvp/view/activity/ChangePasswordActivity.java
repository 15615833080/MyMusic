package com.example.mymusic.mvp.view.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mymusic.R;
import com.example.mymusic.base.BaseActivity;
import com.example.mymusic.mvp.presenter.InputPresenterImpl;
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
        inputPresenter.handleInput(inputOldPassword.getInputStr(), inputPassword.getInputStr(), inputPasswordConfirm.getInputStr());
    }
}
