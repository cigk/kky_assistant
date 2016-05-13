package com.kuaikuaiyu.assistant.ui.account.bindalipay;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.app.AppConfig;
import com.kuaikuaiyu.assistant.base.BaseFragment;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.modle.domain.VerifyData;
import com.kuaikuaiyu.assistant.utils.FormatUtil;
import com.kuaikuaiyu.assistant.utils.UIUtil;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2015/12/8
 * Desc:    绑定支付宝页面 refactor from BinaAlipayActivity
 */
public class BindAlipayFragment extends BaseFragment implements BindAlipayView {

    @Bind(R.id.tv_info)
    TextView tv_info;
    @Bind(R.id.et_account)
    EditText et_account;
    @Bind(R.id.et_name)
    EditText et_name;
    @Bind(R.id.btn_submit)
    Button btn_submit;

    @Inject
    BindAlipayPresenter mPresenter;
    private VerifyData mVerifyData;

    @Override
    protected void initComponent() {

    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_bind_alipay;
    }

    @Override
    protected void setListener() {
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAlipay();
            }
        });
    }

    @Override
    protected void initData() throws Exception {
        mLoadingPage.setSucceed();
        if (null != mVerifyData)
            initView();
    }

    @Override
    protected void refresh() {

    }

    /**
     * 根据当前的审核状态设置View
     */
    private void initView() {
        switch (mVerifyData.status) {
            case AppConfig.VERIFYSTATUS_VERIFING:
                btn_submit.setVisibility(View.GONE);
                tv_info.setVisibility(View.VISIBLE);
                tv_info.setText(R.string.waitting_alipay_verify);
                btn_submit.setOnClickListener(null);
                et_account.setEnabled(false);
                et_name.setEnabled(false);
                break;

            case AppConfig.VERIFYSTATUS_FAILED:
                tv_info.setVisibility(View.VISIBLE);
                tv_info.setText(R.string.err_alipay_verify);
                break;

            case AppConfig.VERIFYSTATUS_VERIFIED:
                tv_info.setVisibility(View.GONE);
                tv_info.setText(R.string.success_alipay_verify);
                mActivity.setResult(Activity.RESULT_OK);
//                Account mAccount = ConfigUtil.getAccountInfo();
//                mAccount.alipay = mVerifyData.verify_data.alipay;
//                ConfigUtil.saveAccountInfo(mAccount);
                break;
        }
        et_account.setText(mVerifyData.verify_data.alipay);
        et_name.setText(mVerifyData.verify_data.alipay_name);
    }

    /**
     * 修改支付宝信息
     */
    private void updateAlipay() {
        final String name = et_name.getText().toString();
        final String account = et_account.getText().toString();

        if (TextUtils.isEmpty(name)) {
            UIUtil.showToast("请输入您的真实姓名");
            return;
        }
        if (TextUtils.isEmpty(account)) {
            UIUtil.showToast("请输入您的支付宝账户");
            return;
        }
        if (!FormatUtil.checkNameChinese(name)) {
            UIUtil.showToast(R.string.owner_name_be_chinese);
            return;
        }
        if (!FormatUtil.isMobile(account) && !FormatUtil.isEmail(account)) {
            UIUtil.showToast(R.string.enter_valid_account);
            return;
        }

        mPresenter.bindAlipay(name, account);
//        NetTask netTask = new NetTask(mActivity) {
//            @Override
//            protected JSONObject onLoad() {
//                return AccountEngine.bindAlipay(name, account);
//            }
//
//            @Override
//            protected void onSuccess(JSONObject jsonObj) throws Exception {
//                UIUtil.showToast(R.string.success_submit_bind);
//                mActivity.finish();
//            }
//        };
//        netTask.execute();
    }

    @Override
    public void bindSucceed() {

    }
}
