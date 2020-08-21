package com.example.mymusic.helps;

/**
 * 1.用户登录
 *      1.当用户登录时，利用SharedPreferences,保存用户登录的标记（手机号）
 *      2.利用全局单例类UserHelp保存用户登录信息
 *          1.用户登录之后
 *          2.用户重新打开应用程序，检查SharedPreferences是否存在登录用户标记
 *          ，如果存在，为UserHelp赋值，进入主页。不存在进入登录界面
 * 2.用户退出
 *      1.删除掉SharedPreferences保存的用户标记，退出到登录界面
 *
 *
 */
public class UserHelp {

    private static UserHelp instance;
    private String phone;
    private UserHelp(){}

    public static UserHelp getInstance() {
        if (instance == null) {
            synchronized (UserHelp.class) {
                if (instance == null) {
                    instance = new UserHelp();
                }
            }
        }
        return instance;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
