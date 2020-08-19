package com.example.mymusic.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class MyParcel implements Parcelable {

    private static final String TAG = "MyParcel";
    private int data;

    public MyParcel(int data) {
        this.data = data;
        Log.d(TAG, "MyParcel: ");
    }

    protected MyParcel(Parcel in) {
        data = in.readInt();
        loadParcel.parcel(in);
    }

    public static final Creator<MyParcel> CREATOR = new Creator<MyParcel>() {
        @Override
        public MyParcel createFromParcel(Parcel in) {
            Log.d(TAG, "createFromParcel: 反序列化");
            return new MyParcel(in);
        }

        @Override
        public MyParcel[] newArray(int size) {
            return new MyParcel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        Log.d(TAG, "writeToParcel: 序列化开始");
        parcel.writeInt(data);
    }


}
