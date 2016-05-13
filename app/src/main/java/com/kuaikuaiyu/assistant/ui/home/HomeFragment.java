package com.kuaikuaiyu.assistant.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.app.AppConfig;
import com.kuaikuaiyu.assistant.base.BaseFragment;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.sys.event.UpdateShopInfo;
import com.kuaikuaiyu.assistant.ui.account.BalanceActivity;
import com.kuaikuaiyu.assistant.ui.common.WebViewActivity;
import com.kuaikuaiyu.assistant.ui.income.IncomeActivity;
import com.kuaikuaiyu.assistant.ui.setting.SettingActivity;
import com.kuaikuaiyu.assistant.utils.CommonUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/13
 * Desc:
 */
public class HomeFragment extends BaseFragment implements HomeView, View.OnClickListener {

    @Bind(R.id.ll_cash_desk)
    LinearLayout llCashDesk;
    @Bind(R.id.ll_balance)
    LinearLayout llBalance;
    @Bind(R.id.ll_assistant)
    LinearLayout llAssistant;
    @Bind(R.id.ll_school)
    LinearLayout llSchool;
    @Bind(R.id.iv_setting)
    ImageView ivSetting;


    @Inject
    HomePresenter mPresenter;

    @Override
    protected void initComponent() {
        DaggerHomeComponent.builder().homeModule(new HomeModule(this)).build().inject(this);
    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void setListener() {
        llCashDesk.setOnClickListener(this);
        llBalance.setOnClickListener(this);
        llAssistant.setOnClickListener(this);
        llSchool.setOnClickListener(this);
        ivSetting.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mPresenter.updatePushId();
        mPresenter.getShopInfo(true);
    }

    @Override
    protected void refresh() {
        mPresenter.getShopInfo(true);
    }

    @Override
    public void onClick(View v) {
        if (CommonUtil.isFastDoubleClick())
            return;
        switch (v.getId()) {
            case R.id.ll_cash_desk:
                goActivity(IncomeActivity.class);
                break;

            case R.id.ll_balance:
                goActivity(BalanceActivity.class);
                break;

            case R.id.ll_assistant:
                WebViewActivity.start(mActivity, AppConfig.URL_ASSISTANT, "平台助手");
                break;

            case R.id.ll_school:
                WebViewActivity.start(mActivity, AppConfig.URL_SCHOOL, "商学院");
                break;

            case R.id.iv_setting:
                goActivity(SettingActivity.class);
                break;

            default:
                break;
        }
    }

    @Override
    public void loadSucceed() {
        mLoadingPage.setSucceed();
    }

    @Override
    public void loadFail() {
        mLoadingPage.setError();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    /**
     * 接受更新店铺信息的事件
     *
     * @param event
     */
    @Subscribe
    public void onEvent(UpdateShopInfo event) {
        mPresenter.getShopInfo(false);
    }
}
