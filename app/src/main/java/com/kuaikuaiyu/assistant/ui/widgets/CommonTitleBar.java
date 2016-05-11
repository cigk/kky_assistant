package com.kuaikuaiyu.assistant.ui.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseCustomView;

import butterknife.Bind;

/**
 * Created by binlly
 * <p>
 * date: 2016/5/9 15:35
 * desc:
 */
public class CommonTitleBar extends BaseCustomView {
    @Bind(R.id.ib_back)
    ImageButton ib_back;
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.tv_right)
    TextView tv_right;
    @Bind(R.id.iv_right)
    ImageView iv_right;

    private String titleText;
    private String rightText;
    private Drawable rightImage;

    public CommonTitleBar(Context context) {
        super(context);
    }

    public CommonTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CommonTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected int[] getStyleable() {
        return R.styleable.CommonTitleBar;
    }

    @Override
    protected void initAttributes(TypedArray a) {
        titleText = a.getString(R.styleable.CommonTitleBar_titleText);
        rightText = a.getString(R.styleable.CommonTitleBar_rightText);
        rightImage = a.getDrawable(R.styleable.CommonTitleBar_rightImage);
    }

    @Override
    protected int getLayout() {
        return R.layout.title_bar_common;
    }

    @Override
    protected void initData(Context context, View view) {
        tv_title.setText(titleText);
        tv_right.setText(rightText);
        iv_right.setImageDrawable(rightImage);
    }

    public void onBackClick(OnClickListener listener) {
        ib_back.setOnClickListener(listener);
    }

    public void onRightClick(OnClickListener listener) {
        tv_right.setOnClickListener(listener);
    }

    public void setTitle(String title) {
        tv_title.setText(title);
    }

    public void setRightText(String rightText) {
        tv_right.setText(rightText);
    }

    public void setRightImage(Drawable rightImage) {
        iv_right.setImageDrawable(rightImage);
    }

    public void setRightImage(int rightImageRes) {
        iv_right.setImageResource(rightImageRes);
    }

    /**
     * 设置右侧文字是否可见
     *
     * @param visibility View.VISIBLE/ View.GONE  (default GONE)
     */
    public void setRightTextVisibility(int visibility) {
        tv_right.setVisibility(visibility);
    }

    /**
     * 设置右侧图片是否可见
     *
     * @param visibility View.VISIBLE/ View.GONE (default GONE)
     */
    public void setRightImgVisibility(int visibility) {
        iv_right.setVisibility(visibility);
    }
}
