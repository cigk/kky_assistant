package com.kuaikuaiyu.assistant.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.igexin.sdk.PushManager;
import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.app.AppConfig;
import com.kuaikuaiyu.assistant.base.BaseActivity;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.modle.service.PassService;
import com.kuaikuaiyu.assistant.net.NetUtil;
import com.kuaikuaiyu.assistant.net.ReqParams;
import com.kuaikuaiyu.assistant.rx.IoTransformer;
import com.kuaikuaiyu.assistant.rx.RxSubscriber;
import com.kuaikuaiyu.assistant.ui.account.BalanceActivity;
import com.kuaikuaiyu.assistant.ui.common.WebViewActivity;
import com.kuaikuaiyu.assistant.ui.income.IncomeActivity;
import com.kuaikuaiyu.assistant.ui.setting.SettingActivity;
import com.kuaikuaiyu.assistant.utils.CommonUtil;
import com.kuaikuaiyu.assistant.utils.ConfigUtil;
import com.kuaikuaiyu.assistant.utils.UIUtil;
import com.umeng.update.UmengUpdateAgent;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/3
 * Desc:    主页面
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener {

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

    @Override
    protected void initComponent() {
        updatePushId();
    }

    @Override
    protected int getRootView() {
        return R.layout.activity_home;
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
    protected void initData(Bundle savedInstanceState) {
        UIUtil.postDelayed(() -> UmengUpdateAgent.update(HomeActivity.this), 500);
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
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
                WebViewActivity.start(this, AppConfig.URL_ASSISTANT, "平台助手");
                break;

            case R.id.ll_school:
                WebViewActivity.start(this, AppConfig.URL_SCHOOL, "商学院");
                break;

            case R.id.iv_setting:
                goActivity(SettingActivity.class);
                break;

            default:
                break;
        }
    }

    /**
     * 更新个推id
     */
    private void updatePushId() {
        String did = PushManager.getInstance().getClientid(UIUtil.getContext());
        PassService service = NetUtil.createForPass(PassService.class);
        ReqParams params = new ReqParams(ReqParams.POST, AppConfig.URL_UPDATE_PUSH_ID);
        params.addParam("duuid", ConfigUtil.getUuid());
        params.addParam("device_push_id", did);
        service.updatePushId(params.getQueryMap(), params.getFieldMap()).compose(new
                IoTransformer()).subscribe(new RxSubscriber(null) {
            @Override
            public void onNext(Object o) {

            }
        });
    }

    @OnClick(R.id.btn_test)
    public void test() {
        PassService service = NetUtil.createForTest(PassService.class);
        Map<String, String> query = new HashMap<>();
        query.put("state", "closed");
        service.testRedirect(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1() {
                    @Override
                    public void call(Object o) {

                    }
                });
    }
}
