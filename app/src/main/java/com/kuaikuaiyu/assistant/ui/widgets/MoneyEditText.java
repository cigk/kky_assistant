package com.kuaikuaiyu.assistant.ui.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

import com.kuaikuaiyu.assistant.utils.MoneyFormatUtil;


/**
 * Created by kky on 15-8-5.
 */
public class MoneyEditText extends EditText implements TextWatcher {

    public MoneyEditText(Context context) {
        super(context);
        init();
    }

    public MoneyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MoneyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MoneyEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init(){
        this.addTextChangedListener(this);
        setSingleLine(true);
        setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
    }


    public int getMoney(){
        return MoneyFormatUtil.formatToSend(this.getText().toString());
    }

    public void setMoney(int money){
        setText(MoneyFormatUtil.format(money));
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        super.onTextChanged(s,start,before,count);
    }

    @Override
    public void afterTextChanged(Editable edt) {
        String temp = edt.toString();
        int posDot = temp.indexOf(".");
        if (posDot < 0) return;
        if (temp.length() - posDot - 1 > 2) {
            edt.delete(posDot + 3, edt.length());
        }
    }
}
