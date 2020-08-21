package com.example.mymusic.mvp.presenter;

import android.app.Activity;
import android.content.Context;

public interface InputPresenter {
    void handleInput(String phoneInput, String passwordInput);
    void handleInput(String phoneInput, String passwordInput, String confirmPassword);

}
