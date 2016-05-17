package com.kuaikuaiyu.assistant.ui.widgets;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by binlly
 * <p>
 * date: 2016/5/12 14:17
 * desc: 配合ultra-ptr下拉刷新库使用的RecyclerView
 */
public class PtrRecyclerView extends RecyclerView implements
        MaterialPtrFramelayout.OnRefreshingChangedListener {
    private boolean refreshing = false;

    public PtrRecyclerView(Context context) {
        super(context);
    }

    public PtrRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PtrRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (refreshing) {
            requestDisallowInterceptTouchEvent(true);
        } else {
            requestDisallowInterceptTouchEvent(false);
        }
        return super.onTouchEvent(e);
    }

    @Override
    public void onRefreshing(boolean refreshing) {
        this.refreshing = refreshing;
    }
}
