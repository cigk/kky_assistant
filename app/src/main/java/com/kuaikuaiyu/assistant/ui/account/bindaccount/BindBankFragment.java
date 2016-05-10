package com.kuaikuaiyu.assistant.ui.account.bindaccount;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.app.AppConfig;
import com.kuaikuaiyu.assistant.base.BaseFragment;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.modle.domain.Account;
import com.kuaikuaiyu.assistant.modle.domain.VerifyData;
import com.kuaikuaiyu.assistant.ui.widgets.PlainEditText;
import com.kuaikuaiyu.assistant.utils.CityUtil;
import com.kuaikuaiyu.assistant.utils.ConfigUtil;
import com.kuaikuaiyu.assistant.utils.DialogUtil;
import com.kuaikuaiyu.assistant.utils.FormatUtil;
import com.kuaikuaiyu.assistant.utils.UIUtil;

import org.json.JSONObject;

import java.util.List;

import butterknife.Bind;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2015/12/8
 * Desc:    绑定银行卡页面   refactor from BainBankFragment
 */
public class BindBankFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.tv_info)
    TextView tv_info;
    @Bind(R.id.tv_bank_name)
    TextView tv_bank_name;
    @Bind(R.id.tv_province)
    TextView tv_province;
    @Bind(R.id.tv_city)
    TextView tv_city;
    @Bind(R.id.et_card_num)
    EditText et_card_num;
    @Bind(R.id.et_card_name)
    PlainEditText et_card_name;
    @Bind(R.id.ll_choose_bank)
    LinearLayout ll_choose_bank;
    @Bind(R.id.ll_choose_city)
    LinearLayout ll_choose_city;
    @Bind(R.id.btn_submit)
    Button btn_submit;

    private String cardNum;
    private String cardName;
    private String province;
    private String city;
    private String bankName;
    private VerifyData mVerifyData;

    @Override
    protected void initComponent() {

    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_bind_bank;
    }

    @Override
    protected void setListener() {
        ll_choose_bank.setOnClickListener(this);
        ll_choose_city.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
    }

    @Override
    protected void initData() throws Exception {
        if (mVerifyData != null)
            initView();
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
                                tv_bank_name.setText(banksList.get(i));
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
                    tv_province.setText(data.getStringExtra("province"));
                    tv_city.setText(data.getStringExtra("city"));
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
        switch (mVerifyData.status) {
            case AppConfig.VERIFYSTATUS_VERIFING:
                btn_submit.setVisibility(View.GONE);
                tv_info.setVisibility(View.VISIBLE);
                tv_info.setText(R.string.waitting_bankaccount_verify);
                ll_choose_bank.setEnabled(false);
                ll_choose_city.setEnabled(false);
                btn_submit.setEnabled(false);
                et_card_num.setEnabled(false);
                et_card_name.setEnabled(false);
                break;

            case AppConfig.VERIFYSTATUS_FAILED:
                tv_info.setVisibility(View.VISIBLE);
                tv_info.setText(R.string.err_bankaccount_verify);
                break;

            case AppConfig.VERIFYSTATUS_VERIFIED:
                tv_info.setVisibility(View.GONE);
                tv_info.setText(R.string.success_bankaccount_verify);
                mActivity.setResult(Activity.RESULT_OK);
                Account mAccount = ConfigUtil.getAccountInfo();
                mAccount.bank.name = mVerifyData.verify_data.bank.name;
                mAccount.bank.province = mVerifyData.verify_data.bank.province;
                mAccount.bank.city = mVerifyData.verify_data.bank.city;
                mAccount.bank.card_no = mVerifyData.verify_data.bank.card_no;
                mAccount.bank.owner = mVerifyData.verify_data.bank.owner;
                ConfigUtil.saveAccountInfo(mAccount);
                break;

            default:
                break;
        }
        tv_bank_name.setText(mVerifyData.verify_data.bank.name);
        tv_province.setText(mVerifyData.verify_data.bank.province);
        tv_city.setText(mVerifyData.verify_data.bank.city);
        et_card_num.setText(mVerifyData.verify_data.bank.card_no);
        et_card_name.setText(mVerifyData.verify_data.bank.owner);
    }

    /**
     * 提交银行账户信息
     */
    private void submitBankInfo() {
        bankName = tv_bank_name.getText().toString();
        province = tv_province.getText().toString();
        city = tv_city.getText().toString();
        cardNum = et_card_num.getText().toString();
        cardName = et_card_name.getText().toString();
        if (TextUtils.isEmpty(bankName)) {
            UIUtil.showToast(R.string.choose_bank);
            return;
        }
        if (TextUtils.isEmpty(province) || TextUtils.isEmpty(city)) {
            UIUtil.showToast(R.string.choose_province_and_city);
            return;
        }
        if (TextUtils.isEmpty(cardNum)) {
            UIUtil.showToast(R.string.enter_bank_number);
            return;
        }
        if (TextUtils.isEmpty(cardName)) {
            UIUtil.showToast(R.string.enter_bank_owner_name);
            return;
        }
        if (!FormatUtil.checkNameChinese(cardName)) {
            UIUtil.showToast(R.string.owner_name_be_chinese);
            return;
        }
//        new NetTask(mActivity) {
//            @Override
//            protected JSONObject onLoad() {
//                return AccountEngine.bindBank(bankName, cardNum, cardName, province, city);
//            }
//
//            @Override
//            protected void onSuccess(JSONObject jsonObj) throws Exception {
//                UIUtil.showToast(R.string.success_submit_bind);
//                mActivity.finish();
//            }
//        }.execute();
    }
}
