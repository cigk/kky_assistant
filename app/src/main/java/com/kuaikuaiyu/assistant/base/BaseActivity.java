package com.kuaikuaiyu.assistant.base;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.view.Window;

import com.kuaikuaiyu.assistant.sys.ActivityManager;
import com.kuaikuaiyu.assistant.ui.widgets.MyProgressDialog;
import com.kuaikuaiyu.assistant.utils.UIUtil;

import butterknife.ButterKnife;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/4/30
 * Desc:    Activity基类
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    private MyProgressDialog loadingDia;
    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
//        getWindow().setEnterTransition(new Fade());
//        getWindow().setReenterTransition(new Fade());
        //        getWindow().setEnterTransition(new Slide(Gravity.BOTTOM));
        //        getWindow().setReenterTransition(new Slide(Gravity.TOP));
        super.onCreate(savedInstanceState);
        initComponent();
        mContext = this;
        ActivityManager.addActivity(this);
        setContentView(getRootView());
        //ButterKnife注入
        ButterKnife.bind(this);
        setListener();
        //初始化数据和设置view内容
        initData(savedInstanceState);
    }

    /**
     * 1. for dagger2 injection
     * 2. for other init action before setContentView
     */
    protected abstract void initComponent();

    @Override
    protected void onDestroy() {
        if (loadingDia != null)
            loadingDia.dismiss();
        loadingDia = null;
        BasePresenter presenter = getPresenter();
        if (null != presenter)
            presenter.clean();
        ButterKnife.unbind(this);
        super.onDestroy();
        ActivityManager.removeActivity(this);
    }

    /**
     * setContentView前预初始化
     */
    //    protected abstract void init();

    /**
     * 获取布局id
     *
     * @return
     */
    protected abstract int getRootView();

    /**
     * 设置监听
     */
    protected abstract void setListener();

    /**
     * 初始化数据
     * 根据数据设置view内容
     */
    protected abstract void initData(Bundle savedInstanceState);

    /**
     * 获取Presenter
     */
    protected abstract BasePresenter getPresenter();

    /**
     * 显示加载进度对话框
     */
    public void showLoading() {
        showLoading("加载中…");
    }

    /**
     * 显示加载进度对话框
     */
    @Override
    public void showLoading(String info) {
        try {
            if (loadingDia == null) {
                loadingDia = new MyProgressDialog(this);
                loadingDia.setTextInfo(info);
                loadingDia.setCancelable(true);
                loadingDia.setCanceledOnTouchOutside(false);
            }
            loadingDia.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 隐藏加载进度对话框
     */

    @Override
    public void hideLoading() {
        try {
            if (this.isFinishing())
                return;
            if (loadingDia != null && loadingDia.isShowing())
                loadingDia.hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showError(String msg) {
        UIUtil.showToast(msg);
    }

    @Override
    public void showNetError() {
        UIUtil.showToast("网络链接异常，请稍后重试");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //        MobclickAgent.onPageStart(this.getClass().getName());
        //        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //        MobclickAgent.onPageEnd(this.getClass().getName());
        //        MobclickAgent.onPause(this);
    }

    /**
     * 跳转到其他Activity并且finish当前Activity
     *
     * @param act
     */
    protected void goActivityAndFinish(final Class<?> act) {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
        Intent intent = new Intent(this, act);
        ActivityCompat.startActivity(this, intent, options.toBundle());
        onBackPressed();
    }

    /**
     * 跳转到其他Activity但不finish当前的Activity
     *
     * @param act
     */
    protected void goActivity(final Class<?> act) {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
        Intent intent = new Intent(this, act);
        ActivityCompat.startActivity(this, intent, options.toBundle());
    }

    /**
     * 跳转到其他Activity但不finish当前的Activity
     *
     * @param intent
     */
    protected void goActivity(Intent intent) {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
        ActivityCompat.startActivity(this, intent, options.toBundle());
    }
}