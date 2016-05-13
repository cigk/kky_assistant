package com.kuaikuaiyu.assistant.ui.account.bindalipay;

import android.widget.Button;
import android.widget.EditText;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseFragment;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.modle.domain.ShopInfo;
import com.kuaikuaiyu.assistant.sys.event.UpdateShopInfo;
import com.kuaikuaiyu.assistant.utils.CommonUtil;
import com.kuaikuaiyu.assistant.utils.ConfigUtil;
import com.kuaikuaiyu.assistant.utils.FormatUtil;
import com.kuaikuaiyu.assistant.utils.UIUtil;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2015/12/8
 * Desc:    绑定支付宝页面 refactor from BinaAlipayActivity
 */
public class BindAlipayFragment extends BaseFragment implements BindAlipayView {

    @Bind(R.id.et_account)
    EditText etAccount;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.btn_submit)
    Button btnSubmit;

    @Inject
    BindAlipayPresenter mPresenter;

    private ShopInfo shopInfo;

    @Override
    protected void initComponent() {
        DaggerBindAlipayComponent.builder()
                .bindAlipayModule(new BindAlipayModule(this)).build().inject(this);
        shopInfo = ConfigUtil.getShopInfo();
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
        btnSubmit.setOnClickListener(v -> updateAlipay());
    }

    @Override
    protected void initData() {
        if (null != shopInfo.getAlipay())
            initView();
        mLoadingPage.setSucceed();
    }

    @Override
    protected void refresh() {

    }

    /**
     * 根据当前的审核状态设置View
     */
    private void initView() {
        etAccount.setText(shopInfo.getAlipay().getAccount());
        etName.setText(shopInfo.getAlipay().getReal_name());
    }

    /**
     * 修改支付宝信息
     */
    private void updateAlipay() {
        final String name = etName.getText().toString();
        final String account = etAccount.getText().toString();

        if (CommonUtil.checkEmpty(name, "请输入您的真实姓名")) return;
        if (CommonUtil.checkEmpty(account, "请输入您的支付宝账户")) return;
        if (!FormatUtil.checkNameChinese(name)) {
            UIUtil.showToast(R.string.owner_name_be_chinese);
            return;
        }
        if (!FormatUtil.isMobile(account) && !FormatUtil.isEmail(account)) {
            UIUtil.showToast(R.string.enter_valid_account);
            return;
        }

        mPresenter.bindAlipay(name, account);
    }

    @Override
    public void bindSucceed() {
        UIUtil.showToast("绑定成功啦,可以去提现喽…");
        UIUtil.postDelayed(() -> mActivity.onBackPressed(), 500);
        EventBus.getDefault().post(new UpdateShopInfo());
    }

    @Override
    public void bindFail() {
        UIUtil.showToast("绑定失败，请重新试下吧…");
    }
}
