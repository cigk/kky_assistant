package com.kuaikuaiyu.assistant.ui.widgets;

import android.app.Activity;
import android.content.Context;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.kuaikuaiyu.assistant.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/17
 * Desc:
 */
public class KeyboardView extends FrameLayout {

    private Vibrator vibrator;
    private EditText mEditText;

    public KeyboardView(Context context) {
        super(context);
        init();
    }

    public KeyboardView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public KeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_keyboard, this);
        ButterKnife.bind(this, view);
        vibrator = (Vibrator) getContext().getSystemService(Activity.VIBRATOR_SERVICE);
    }

    @OnClick(R.id.btn0)
    public void clickBtn0() {
        keyPressed(KeyEvent.KEYCODE_0);
    }

    @OnClick(R.id.btn1)
    public void clickBtn1() {
        keyPressed(KeyEvent.KEYCODE_1);
    }

    @OnClick(R.id.btn2)
    public void clickBtn2() {
        keyPressed(KeyEvent.KEYCODE_2);
    }

    @OnClick(R.id.btn3)
    public void clickBtn3() {
        keyPressed(KeyEvent.KEYCODE_3);
    }

    @OnClick(R.id.btn4)
    public void clickBtn4() {
        keyPressed(KeyEvent.KEYCODE_4);
    }

    @OnClick(R.id.btn5)
    public void clickBtn5() {
        keyPressed(KeyEvent.KEYCODE_5);
    }

    @OnClick(R.id.btn6)
    public void clickBtn6() {
        keyPressed(KeyEvent.KEYCODE_6);
    }

    @OnClick(R.id.btn7)
    public void clickBtn7() {
        keyPressed(KeyEvent.KEYCODE_7);
    }

    @OnClick(R.id.btn8)
    public void clickBtn8() {
        keyPressed(KeyEvent.KEYCODE_8);
    }

    @OnClick(R.id.btn9)
    public void clickBtn9() {
        keyPressed(KeyEvent.KEYCODE_9);
    }

    @OnClick(R.id.btnDot)
    public void clickBtnDot() {
//        keyPressed(KeyEvent.KEYCODE_PERIOD);
        keyPressed(KeyEvent.KEYCODE_NUMPAD_DOT);
    }

    @OnClick(R.id.btnDel)
    public void clickBtnDel() {
        keyPressed(KeyEvent.KEYCODE_DEL);
    }

    @OnLongClick(R.id.btnDel)
    public boolean longClickBtnDel() {
        mEditText.setText("");
        return true;
    }


    /**
     * 触发按键事件和震动
     *
     * @param keyCode
     */
    private void keyPressed(int keyCode) {
        KeyEvent event = new KeyEvent(KeyEvent.ACTION_DOWN, keyCode);
        if (null == mEditText) {
            throw new NullPointerException("mView can not be null");
        }
        mEditText.dispatchKeyEvent(event);
        vibrator.vibrate(50);

//        mDigits.onKeyDown(keyCode, event);
//        final int length = mDigits.length();
//        if (length == mDigits.getSelectionStart() && length == mDigits.getSelectionEnd()) {
//            mDigits.setCursorVisible(false);
//        }
    }

    /**
     * 设置TextView
     *
     * @param textView
     */
    public void setView(EditText textView) {
        this.mEditText = textView;
    }
}
