package com.kuaikuaiyu.assistant.ui.account.bindbank;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.app.AppConfig;
import com.kuaikuaiyu.assistant.base.BaseFragment;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.modle.domain.ShopInfo;
import com.kuaikuaiyu.assistant.sys.event.UpdateShopInfo;
import com.kuaikuaiyu.assistant.ui.widgets.PlainEditText;
import com.kuaikuaiyu.assistant.utils.CityUtil;
import com.kuaikuaiyu.assistant.utils.CommonUtil;
import com.kuaikuaiyu.assistant.utils.ConfigUtil;
import com.kuaikuaiyu.assistant.utils.DialogUtil;
import com.kuaikuaiyu.assistant.utils.FormatUtil;
import com.kuaikuaiyu.assistant.utils.UIUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2015/12/8
 * Desc:    绑定银行卡页面   refactor from BainBankFragment
 */
public class BindBankFragment extends BaseFragment implements BindBankView, View.OnClickListener {

    @Bind(R.id.tv_bank_name)
    TextView tvBankName;
    @Bind(R.id.et_card_num)
    EditText etCardNum;
    @Bind(R.id.et_card_name)
    PlainEditText etCardName;
    @Bind(R.id.ll_choose_bank)
    LinearLayout llChooseBank;
    @Bind(R.id.ll_choose_city)
    LinearLayout llChooseCity;
    @Bind(R.id.btn_submit)
    Button btnSubmit;

    @Inject
    BindBankPresenter mPresenter;

    private ShopInfo shopInfo;

    @Override
    protected void initComponent() {
        DaggerBindBankComponent.builder()
                .bindBankModule(new BindBankModule(this))
                .build().inject(this);
        shopInfo = ConfigUtil.getShopInfo();
    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_bind_bank;
    }

    @Override
    protected void setListener() {
        llChooseBank.setOnClickListener(this);
        llChooseCity.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        if (null != shopInfo.getBank())
            initView();
        mLoadingPage.setSucceed();
    }

    @Override
    protected void refresh() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_choose_bank:
                final List<String> banksList = CityUtil.getBankNameList();
                DialogUtil.getSelectDialog(mActivity, "选择银行",
                        banksList, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tvBankName.setText(banksList.get(i));
                            }
                        }).show();
                break;

            case R.id.ll_choose_city:
                Intent intent = new Intent(mActivity, ChooseCityActivity.class);
                startActivityForResult(intent, AppConfig.REQUEST_CODE_CHOOSE_CITY);
                break;

            case R.id.btn_submit:
                submitBankInfo();
                break;

            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case AppConfig.REQUEST_CODE_CHOOSE_CITY:
//                    tv_province.setText(data.getStringExtra("province"));
//                    tv_city.setText(data.getStringExtra("city"));
                    break;

                default:
                    break;
            }
        }
    }

    /**
     * 根据当前的审核状态设置View
     */
    private void initView() {
        tvBankName.setText(shopInfo.getBank().getBank_name());
        etCardNum.setText(shopInfo.getBank().getAccount());
        etCardName.setText(shopInfo.getBank().getReal_name());
    }

    /**
     * 提交银行账户信息
     */
    private void submitBankInfo() {
        String bankName = tvBankName.getText().toString();
        String cardNum = etCardNum.getText().toString();
        String cardName = etCardName.getText().toString();

        if (CommonUtil.checkEmpty(bankName, R.string.choose_bank)) return;
        if (CommonUtil.checkEmpty(cardNum, R.string.enter_bank_number)) return;
        if (CommonUtil.checkEmpty(cardName, R.string.enter_bank_owner_name)) return;

        if (!FormatUtil.checkNameChinese(cardName)) {
            UIUtil.showToast(R.string.owner_name_be_chinese);
            return;
        }
        mPresenter.bindBank(bankName, cardNum, cardName);

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
