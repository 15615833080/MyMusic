package com.example.mymusic.mvp.view.activity.impl;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.example.mymusic.R;
import com.example.mymusic.base.BaseActivity;
import com.example.mymusic.base.BaseInternetActivity;
import com.example.mymusic.helps.UserHelp;
import com.example.mymusic.utils.UserUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeActivity extends BaseInternetActivity {

    @BindView(R.id.tv_user)
    TextView tvUser;
    @BindView(R.id.refactor_password)
    TextView refactorPassword;
    @BindView(R.id.person_check)
    TextView personCheck;
    @BindView(R.id.logout)
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        ButterKnife.bind(this);
        init();
    }
    private void init() {
        initNavBar(true, "个人中心", false);
        tvUser.setText("当前用户：" + UserHelp.getInstance().getPhone());
    }

    @OnClick({R.id.refactor_password, R.id.person_check,R.id.logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.refactor_password:
                ActivityUtils.startActivity(ChangePasswordActivity.class);
                break;
            case R.id.person_check:
                ActivityUtils.startActivity(PlayRecordActivity.class);
                break;
            case R.id.logout:
                UserUtils.logout(this);
                break;
        }
    }
}
