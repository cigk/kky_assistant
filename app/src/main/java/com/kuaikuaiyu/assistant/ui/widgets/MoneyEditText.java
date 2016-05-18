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
        while (!TextUtils.isEmpty(edt) && '0' == (edt.charAt(0)) && edt.length() >= 2 && '.' != (edt.charAt(1))) {
            edt.delete(0, 1);
        }
        String str = edt.toString();
        int posDot = str.indexOf(".");
        if (posDot < 0) {
            //整数位最长为6位
            if (edt.length() > 6) {
                edt.delete(edt.length() - 1, edt.length());
            }
            //没有小数点时不进行后续处理
            return;
        }

        //第一位是小数点是前面加0
        if (posDot == 0) {
            edt.insert(0, "0");
        }
        //只保留两位小数
        if (str.length() - posDot - 1 == 2 && edt.charAt(edt.length() - 1) == '0') {
            edt.delete(edt.length() - 1, edt.length());
        }

        //小数最后一位为0时可以重新输入
        if (str.length() - posDot - 1 > 2) {
            edt.delete(posDot + 3, edt.length());
        }
    }
}
