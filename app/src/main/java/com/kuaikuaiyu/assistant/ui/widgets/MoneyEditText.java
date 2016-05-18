package com.kuaikuaiyu.assistant.ui.widgets;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

import com.kuaikuaiyu.assistant.utils.MoneyUtil;


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


    private void init() {
        this.addTextChangedListener(this);
        setSingleLine(true);
        setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
    }


    public int getMoney() {
        return MoneyUtil.buck2Cent(this.getText().toString());
    }

    public void setMoney(int money) {
        setText(MoneyUtil.format(money));
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        super.onTextChanged(s, start, before, count);
    }

    @Override
    public void afterTextChanged(Editable edt) {
        //删除字符串前面的0
        while (!TextUtils.isEmpty(edt) && '0' == (edt.charAt(0))) {
            edt.delete(0, 1);
        }
        String temp = edt.toString().trim();
        int posDot = temp.indexOf(".");
        if (posDot < 0) {
            if (temp.length() > 6) {
                edt.delete(edt.length() - 1, edt.length());
            }
            return;//没有小数点时不处理
        }

        if (temp.length() - posDot - 1 == 2 && edt.charAt(edt.length() - 1) == '0') {
            edt.delete(edt.length() - 1, edt.length());
        }

        if (temp.length() - posDot - 1 > 2) {
            edt.delete(posDot + 3, edt.length());
        }
    }
}
