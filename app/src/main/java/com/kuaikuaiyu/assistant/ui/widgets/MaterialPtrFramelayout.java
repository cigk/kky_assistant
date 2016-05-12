package com.kuaikuaiyu.assistant.ui.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.utils.UIUtil;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.MaterialHeader;

/**
 * Created by binlly
 * <p>
 * date: 2016/5/12 14:34
 * desc: 封装了material刷新头的PtrFramelayout
 * 配合PtrRecycleView使用，刷新中失能对滑动的处理，把touch事件交给child处理，刷新完毕调用complete重新收回控制权
 */
public class MaterialPtrFramelayout extends PtrClassicFrameLayout {
    private Context mContext;
    private OnRefreshingChangedListener mChangedListener;

    public MaterialPtrFramelayout(Context context) {
        super(context);
        init(context);
    }

    public MaterialPtrFramelayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MaterialPtrFramelayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        setMaterialHeader(mContext);
    }

    /**
     * 结束刷新操作，同时恢复使能，可以接受新的操作
     */
    public void compelete() {
        refreshComplete();
        setEnabled(true);
        if (mChangedListener != null) {
            mChangedListener.onRefreshing(false);
        }
    }

    /**
     * 需要PtrMaterialHandler类型的handler,配合MaterialPtrFramelayout处理了滑动使能的关系
     *
     * @param handler
     */
    public void setMaterialPtrHandler(PtrMaterialHandler handler) {
        setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                //不接收滑动事件，交给child处理
                setEnabled(false);
                if (mChangedListener != null) {
                    mChangedListener.onRefreshing(true);
                }
                handler.onBegin(frame);
            }
        });
    }

    /**
     * 设置Material风格的下拉刷新头
     *
     * @param context
     */
    private void setMaterialHeader(Context context) {
        final MaterialHeader header = new MaterialHeader(context);
        int[] colors = getResources().getIntArray(R.array.progress_colors);
        header.setColorSchemeColors(colors);
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        header.setPadding(0, UIUtil.dp2px(30), 0, UIUtil.dp2px(10));
        header.setPtrFrameLayout(this);
        setHeaderView(header);
        addPtrUIHandler(header);
        //内容保持不动
        setPinContent(true);
    }

    public void setRefreshingListener(OnRefreshingChangedListener listener) {
        mChangedListener = listener;
    }

    public interface PtrMaterialHandler {
        void onBegin(PtrFrameLayout frame);
    }

    public interface OnRefreshingChangedListener {
        void onRefreshing(boolean refreshing);
    }
}
