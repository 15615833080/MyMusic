package com.example.mymusic.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.mymusic.R;
import com.example.mymusic.helps.RealmHelp;
import com.example.mymusic.helps.UserHelp;
import com.example.mymusic.mvp.model.UserModel;
import com.example.mymusic.mvp.view.activity.impl.LoginActivity;

import java.util.List;

public class UserUtils {
    private static final String TAG = "UserUtils";

    /**
     * 验证登录用户
     *
     * @param context
     * @param phone
     * @param password
     * @return
     */
    public static boolean validateLogin(Context context, String phone, String password) {
        if (!RegexUtils.isMobileExact(phone)) {
            ToastUtils.showShort("无效手机号");
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtils.showShort("请输入密码");
            return false;
        }
        /**
         * 1.用户当前手机号是否注册
         * 2.用户手机号密码是否匹配
         */
        if (!UserUtils.userExistFromPhone(phone)) {
            ToastUtils.showShort("当前手机号未注册");
            return false;
        }
        RealmHelp realmHelp = new RealmHelp();
        boolean result = realmHelp.validateUser(phone, EncryptUtils.encryptMD5ToString(password));
        if (!result) {
            ToastUtils.showShort("账号与密码不一致，重新输入");
            return false;
        }

        //保存用户登录标记
        boolean isSave = SPUtils.saveUser(context, phone);
        if (!isSave) {
            ToastUtils.showShort("系统错误，请稍后重试");
            return false;
        }

        //保存用户标记，在全局单例类中
        UserHelp.getInstance().setPhone(phone);
        //保存音乐源
        realmHelp.savaMusicModel(context);
        realmHelp.close();
        return true;
    }

    /**
     * 退出登录
     *
     * @param context
     */
    public static void logout(Context context) {
        //删除SP中的标记
        boolean result = SPUtils.logout(context);
        if (!result) {
            ToastUtils.showShort("系统错误，请稍后重试");
            return;
        }
        RealmHelp realmHelp = new RealmHelp();
        realmHelp.removeMusicModel(context);
        realmHelp.close();
        Intent intent = new Intent(context, LoginActivity.class);
        //        添加intent标志符，清理task栈，并且新生成一个task栈
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        //定义动画
        ((Activity) context).overridePendingTransition(R.anim.open_enter, R.anim.open_exit);

    }
    /**
     * 对输入的密码进行验证
     *
     * @param context
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     * @return
     */
    public static boolean checkUpdatePassword(Context context, String oldPassword, String newPassword, String confirmPassword) {
        if (TextUtils.isEmpty(oldPassword)) {
            ToastUtils.showShort("请输入原密码");
            return false;
        }
        if (TextUtils.isEmpty(newPassword) || !TextUtils.equals(newPassword, confirmPassword)) {
            ToastUtils.showShort("请确认密码");
            return false;
        }
        RealmHelp realmHelp = new RealmHelp();
        UserModel model = realmHelp.getUser();
        if (!model.getPassword().equals(EncryptUtils.encryptMD5ToString(newPassword))) {
            ToastUtils.showShort("与原密码不一致，请检查");
            return false;
        }
        boolean result = realmHelp.updataPassword(EncryptUtils.encryptMD5ToString(newPassword));
        if(!result){
            ToastUtils.showShort("系统错误，请重试");
        }
        ToastUtils.showShort("修改密码成功");
        realmHelp.close();
        return result;

    }

    /**
     * 注册用户
     *
     * @param context
     * @param phone
     * @param password
     * @param passwordConfirm
     */
    public static boolean registerUser(Context context, String phone, String password, String passwordConfirm) {
        if (!RegexUtils.isMobileExact(phone)) {
            ToastUtils.showShort("无效手机号");
            return false;
        }

        if (TextUtils.isEmpty(password) || !password.equals(passwordConfirm)) {
            ToastUtils.showShort("请确认密码");
            return false;
        }
        //用户输入手机号是否被注册
        if (UserUtils.userExistFromPhone(phone)) {
            ToastUtils.showShort("当前手机号已存在");
            return false;
        }
        /**
         * 1.先去获取到已被注册的所有用户
         * 2.根据用户输入的手机号匹配查询的所有用户
         */
        UserModel userModel = new UserModel();
        userModel.setPhone(phone);
        userModel.setPassword(EncryptUtils.encryptMD5ToString(password));
        saveUser(userModel);
        return true;
    }

    /**
     * 根据手机号判断用户是否存在
     */
    public static boolean userExistFromPhone(String phone) {
        boolean result = false;
        RealmHelp realmHelp = new RealmHelp();
        List<UserModel> allUser = realmHelp.getAllUser();
        for (UserModel userModel : allUser) {
            if (userModel.getPhone().equals(phone)) {
//                当前手机号已经存在于数据库中了
                result = true;
                break;
            }
        }
        realmHelp.close();
        return result;
    }

    /**
     * 验证是否存在已登录用户
     */
    public static boolean validateUserLogin(Context context) {
        return SPUtils.isLoginUser(context);
    }



    /**
     * 保存用户到数据库
     *
     * @param userModel
     */
    public static void saveUser(UserModel userModel) {
        RealmHelp realmHelp = new RealmHelp();
        realmHelp.saveUser(userModel);
        realmHelp.close();
    }

}
