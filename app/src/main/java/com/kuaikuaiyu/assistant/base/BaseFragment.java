package com.kuaikuaiyu.assistant.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.ui.widgets.LoadingPage;
import com.kuaikuaiyu.assistant.ui.widgets.MyProgressDialog;
import com.kuaikuaiyu.assistant.utils.UIUtil;

import butterknife.ButterKnife;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/4/30
 * Desc:    Fragment基类
 */
public abstract class BaseFragment extends Fragment implements BaseView {

    protected BaseActivity mActivity;
    private MyProgressDialog loadingDia;
    private LoadingPage mLoadingPage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initComponent();
        mActivity = (BaseActivity) getActivity();
        preLoad();
        if (null == mLoadingPage) {
            mLoadingPage = new LoadingPage(mActivity) {
                @Override
                protected View createLoadingView() {
                    return BaseFragment.this.getLoadingView();
                }

                @Override
                protected View createEmptyView() {
                    return BaseFragment.this.getEmptyView();
                }

                @Override
                protected View createErrorView() {
                    return BaseFragment.this.getErrorView();
                }

                @Override
                protected View createSucceedView() {
                    return BaseFragment.this.getContentView();
                }
            };
        }
        return mLoadingPage;
    }

    /**
     * 1. for dagger2 injection
     * 2. for other init action before setContentView
     */
    protected abstract void initComponent();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        restoreFragmentState(savedInstanceState);
        setListener();
        try {
            initData();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 恢复viewState
     *
     * @param savedInstanceState
     */
    protected void restoreFragmentState(Bundle savedInstanceState) {

    }

    /**
     * 加载布局文件
     *
     * @return
     */
    private View getContentView() {
        return UIUtil.inflate(getRootViewId());
    }


    @Override
    public void onDestroyView() {
        if (loadingDia != null)
            loadingDia.dismiss();
        loadingDia = null;
        BasePresenter presenter = getPresenter();
        if (null != presenter) presenter.clean();
        ButterKnife.unbind(this);
        super.onDestroyView();
    }

    /**
     * 获取Presenter
     */
    protected abstract BasePresenter getPresenter();

    /**
     * 显示加载进度对话框
     */
    @Override
    public void showLoading(String info) {
        try {
            if (loadingDia == null) {
                loadingDia = new MyProgressDialog(mActivity);
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
            if (mActivity.isFinishing()) return;
            if (loadingDia != null && loadingDia.isShowing()) loadingDia.hide();
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


    /**
     * 显示加载进度对话框
     */
    public void showLoading() {
        showLoading("加载中…");
    }

    /**
     * 预加载
     */
    protected void preLoad() {

    }

    /**
     * 获取布局id
     *
     * @return
     */
    protected abstract int getRootViewId();

    /**
     * 设置监听
     */
    protected abstract void setListener();

    /**
     * 设置数据
     */
    protected abstract void initData() throws Exception;

    /**
     * 加载失败时重新加载
     * 刷洗页面
     */
    protected abstract void refresh();

    /**
     * 创建加载中页面
     *
     * @return
     */
    protected View getLoadingView() {
        return UIUtil.inflate(R.layout.loading_page_loading);
    }

    /**
     * 内容为空的布局id
     * 自定义空布局是可以在fragment中重写次方法
     * <p>
     * 默认点击图片可以重新加载
     *
     * @return
     */
    protected View getEmptyView() {
        View view = UIUtil.inflate(R.layout.loading_page_empty);
        view.findViewById(R.id.iv_empty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseFragment.this.refresh();
            }
        });
        return view;
    }

    /**
     * 加载失败页面
     *
     * @return
     */
    protected View getErrorView() {
        View view = UIUtil.inflate(R.layout.loading_page_error);
        //点击重新加载
        view.findViewById(R.id.iv_reload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseFragment.this.refresh();
            }
        });
        return view;
    }


    /**
     * 跳转到其他Activity
     *
     * @param activity
     */
    protected void goActivity(Class<?> activity) {
        Intent intent = new Intent(mActivity, activity);
        mActivity.startActivity(intent);
    }
}
