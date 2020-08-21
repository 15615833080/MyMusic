package com.example.mymusic.mvp.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.blankj.utilcode.util.ActivityUtils;
import com.example.mymusic.mvp.view.activity.LoginActivity;
import com.example.mymusic.mvp.view.activity.MainActivity;
import com.example.mymusic.utils.UserUtils;

public class InputPresenterImpl implements InputPresenter {

    private Context mContext;

    public InputPresenterImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void handleInput(String phoneInput, String passwordInput) {
        if(!UserUtils.validateLogin(mContext, phoneInput, passwordInput)){
            return;
        }else {
            Intent intent = new Intent(mContext, MainActivity.class);
            mContext.startActivity(intent);
        }
    }

    @Override
    public void handleInput(String phoneInput, String passwordInput, String confirmPassword) {
        boolean result = UserUtils.registerUser(mContext, phoneInput, passwordInput, confirmPassword);
        if(!result){
            return;
        }
        ((Activity)mContext).onBackPressed();
    }
}
