package com.example.mymusic.mvp.view.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.mymusic.R;
import com.example.mymusic.utils.LogUtils;

public class InputView extends FrameLayout {

    private static final String TAG = "InputView";
    private int inputIcon;
    private String inputHint;
    private boolean isPassword;
    private View mView;
    private ImageView mIvIcon;
    private EditText mIvText;

    public InputView(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public InputView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
   }

    public InputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public InputView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    /**
     * 初始化方法
     * @param context
     * @param atrs
     */
    public void init(Context context, AttributeSet atrs){
        if(atrs == null){
            return;
        }
        //获取自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(atrs, R.styleable.InputView);
        inputIcon = typedArray.getResourceId(R.styleable.InputView_input_icon, R.mipmap.logo);
        inputHint = typedArray.getString(R.styleable.InputView_input_hint);
        isPassword = typedArray.getBoolean(R.styleable.InputView_is_password, false);
        LogUtils.d(TAG, inputHint + isPassword);
        typedArray.recycle();

        //绑定layout布局
        mView = LayoutInflater.from(context).inflate(R.layout.input_view, this, false);
        mIvIcon = mView.findViewById(R.id.iv_icon);
        mIvText = mView.findViewById(R.id.et_input);

        //布局关联属性
        mIvIcon.setImageResource(inputIcon);
        mIvText.setHint(inputHint);
        mIvText.setInputType(isPassword ? InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD : InputType.TYPE_CLASS_PHONE);
        addView(mView);
    }

    /**
     * 获取输入的内容
     * @return
     */
    public String getInputStr(){
        return mIvText.getText().toString().trim();
    }

}
