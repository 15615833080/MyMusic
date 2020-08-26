package com.example.mymusic.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.mymusic.helps.UserHelp;

public class SPUtils {
    /**
     *  1.当用户登录时，利用SharedPreferences,保存用户登录的标记（手机号）
     *
     */
    public static boolean saveUser(Context context, String phone){
        SharedPreferences sp = context.getSharedPreferences(Constant.SP_NAME_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(Constant.SP_KEY_PHONE, phone);
        boolean result = editor.commit();
        return result;
    }
    /**
     * 验证是否存在已经登录用户
     */
    public static boolean isLoginUser(Context context){
        boolean result = false;
        SharedPreferences sp = context.getSharedPreferences(Constant.SP_NAME_USER,Context.MODE_PRIVATE);
        String phone = sp.getString(Constant.SP_KEY_PHONE,"");
        if(!TextUtils.isEmpty(phone)){
            result = true;
            UserHelp.getInstance().setPhone(phone);
        }
        return result;
    }
    /**
     * 退出登录，删除SP中的标记
     */
    public static boolean logout(Context context){
        SharedPreferences sp = context.getSharedPreferences(Constant.SP_NAME_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(Constant.SP_KEY_PHONE);
        boolean result = editor.commit();
        return result;
    }
}
