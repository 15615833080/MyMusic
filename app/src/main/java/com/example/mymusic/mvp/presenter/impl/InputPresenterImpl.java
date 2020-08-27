package com.example.mymusic.mvp.presenter.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.mymusic.mvp.presenter.InputPresenter;
import com.example.mymusic.mvp.view.activity.impl.MainActivity;
import com.example.mymusic.utils.LogUtils;
import com.example.mymusic.utils.UserUtils;

public class InputPresenterImpl implements InputPresenter {

    private static final String TAG = "InputPresenterImpl";
    private Context mContext;

    public InputPresenterImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void handleInputLogin(String phoneInput, String passwordInput) {
        if (!UserUtils.validateLogin(mContext, phoneInput, passwordInput)) {
            return;
        } else {
            Intent intent = new Intent(mContext, MainActivity.class);
            mContext.startActivity(intent);
        }
    }

    @Override
    public void handleInputRegister(String phoneInput, String passwordInput, String confirmPassword) {
        boolean result = UserUtils.registerUser(mContext, phoneInput, passwordInput, confirmPassword);
        if (!result) {
            return;
        }
        ((Activity) mContext).onBackPressed();
    }

    @Override
    public void handlerInputChangePassword(String oldPassword, String password, String confirmPassword) {
        boolean result = UserUtils.checkUpdatePassword(mContext,oldPassword,password,confirmPassword);
        LogUtils.d(TAG, "result" + result);
        if(!result){
            return;
        }
        UserUtils.logout(mContext);
    }
}
