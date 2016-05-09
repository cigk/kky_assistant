package com.kuaikuaiyu.assistant.base;

import android.view.View;

import butterknife.ButterKnife;

public abstract class BaseHolder<T> {

    private View view;

    private T data;

    public BaseHolder() {
        view = initView();
        view.setTag(this);
        ButterKnife.bind(BaseHolder.this, view);
    }

    public void setData(T data) {
        this.data = data;
        refreshView();
    }

    /**
     * 添加数据
     */
    public abstract void refreshView();

    public T getData() {
        return data;
    }

    public View getRootView() {
        return view;
    }

    /**
     * 加载布局
     *
     * @return view
     */
    public abstract View initView();
}
