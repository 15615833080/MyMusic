package com.example.mymusic.mvp.presenter;

import android.app.Activity;
import android.content.Context;

public interface InputPresenter {
    /**
     * 登录界面
     * @param phoneInput
     * @param passwordInput
     */
    void handleInputLogin(String phoneInput, String passwordInput);

    /**
     * 注册界面
     * @param phoneInput
     * @param passwordInput
     * @param confirmPassword
     */
    void handleInputRegister(String phoneInput, String passwordInput, String confirmPassword);

    /**
     * 修改密码界面
     * @param oldPassword
     * @param password
     * @param confirmPassword
     */
    void handlerInputChangePassword(String oldPassword, String password, String confirmPassword);
}
