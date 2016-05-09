package com.kuaikuaiyu.assistant.base;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import butterknife.ButterKnife;

/**
 * Created by yy on 2015/12/2.
 * 一般自定义组合view的基类
 */
public abstract class BaseCustomView extends RelativeLayout {
    /**
     * 自定义view属性命名空间
     */
    private static final String NAMESPACE = "http://schemas.android.com/apk/res-auto";

    public BaseCustomView(Context context) {
        super(context);
        initView(context);
    }

    public BaseCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, getStyleable());
        initAttributes(a);
        initView(context);
        a.recycle();
    }

    public BaseCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    /**
     * 初始化布局
     */
    private void initView(Context context) {
        View view = View.inflate(getContext(), getLayout(), this);
        ButterKnife.bind(this, view);
        initData(context, view);
    }

    protected abstract int[] getStyleable();

    protected abstract void initAttributes(TypedArray a);

    protected abstract int getLayout();

    protected abstract void initData(Context context, View view);
}
