package com.kuaikuaiyu.assistant.ui.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseActivity;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.sys.ActivityManager;
import com.kuaikuaiyu.assistant.ui.home.login.LoginActivity;
import com.kuaikuaiyu.assistant.utils.CommonUtil;
import com.kuaikuaiyu.assistant.utils.ConfigUtil;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/5
 * Desc:    设置页面
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.ib_back)
    ImageButton ibBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_shop_icon)
    ImageView ivShopIcon;
    @Bind(R.id.tv_shop_name)
    TextView tvShopName;
    @Bind(R.id.tv_shop_mobile)
    TextView tvShopMobile;
    @Bind(R.id.ll_shop_info)
    LinearLayout llShopInfo;
    @Bind(R.id.rl_change_shop_name)
    RelativeLayout rlChangeShopName;
    @Bind(R.id.rl_change_pwd)
    RelativeLayout rlChangePwd;
    @Bind(R.id.rl_feedback)
    RelativeLayout rlFeedback;
    @Bind(R.id.rl_about)
    RelativeLayout rlAbout;

    @Override
    protected void initComponent() {

    }

    @Override
    protected int getRootView() {
        return R.layout.activity_setting;
    }

    @Override
    protected void setListener() {
        ibBack.setOnClickListener(this);
        llShopInfo.setOnClickListener(this);
        rlChangeShopName.setOnClickListener(this);
        rlChangePwd.setOnClickListener(this);
        rlFeedback.setOnClickListener(this);
        rlAbout.setOnClickListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        tvTitle.setText("设置");
        tvShopName.setText(ConfigUtil.getShopName());
        tvShopMobile.setText(ConfigUtil.getShopMobile());
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        if (CommonUtil.isFastDoubleClick()) return;
        switch (v.getId()) {
            case R.id.ll_shop_info:

                break;

            case R.id.rl_change_shop_name:

                break;

            case R.id.rl_change_pwd:

                break;

            case R.id.rl_feedback:

                break;

            case R.id.rl_about:

                break;

            case R.id.ib_back:
                finish();
                break;

            default:
                break;
        }
    }

    @OnClick(R.id.btn_logout)
    public void logout() {
        ConfigUtil.setAuthToken("");
        goActivity(LoginActivity.class);
        ActivityManager.finishOtherActivities(LoginActivity.class);
    }
}
