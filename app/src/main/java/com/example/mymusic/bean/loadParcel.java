package com.example.mymusic.bean;

import android.os.Parcel;
import android.util.Log;

import com.example.mymusic.utils.LogUtils;

public class loadParcel {

    public loadParcel(Parcel in) {
        int mm = in.readInt();
        LogUtils.d("loadParcel", "mm:" + mm);
    }

    public static void parcel(Parcel in){

    }
}
