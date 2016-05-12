package com.kuaikuaiyu.assistant.ui.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kky on 15-8-5.
 */
public class PlainEditText extends EditText implements TextWatcher {

    public interface OnErrorDataInput {
        void onErrorData();
    }

    private OnErrorDataInput mOnErrorDataInput;

    //非法字符
    private String regEx = "[\\ud83c\\udc00-\\ud83c\\udfff]|[\\ud83d\\udc00-\\ud83d\\udfff]|[\\u2600-\\u27ff]";
    private Pattern pattern;

    public PlainEditText(Context context) {
        super(context);
        init();
    }


    public PlainEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PlainEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PlainEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        addTextChangedListener(this);
        pattern = Pattern.compile(regEx);
    }

    public void setOnErrorDataInput(OnErrorDataInput l) {
        mOnErrorDataInput = l;
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        super.onTextChanged(s,start,before,count);
    }


    @Override
    public void afterTextChanged(Editable s) {
        Matcher m=pattern.matcher(s.toString());
        if (m.find()){ //存在非法字符
            if (mOnErrorDataInput != null) mOnErrorDataInput.onErrorData();
            s.replace(0,s.length(),m.replaceAll("").trim());
        }
    }

}
