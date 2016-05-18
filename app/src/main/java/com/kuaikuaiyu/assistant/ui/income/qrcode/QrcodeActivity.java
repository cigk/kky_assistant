package com.kuaikuaiyu.assistant.ui.income.qrcode;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseActivity;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.ui.income.CommonModule;
import com.kuaikuaiyu.assistant.ui.widgets.CommonTitleBar;
import com.kuaikuaiyu.assistant.utils.PermissionUtil;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by binlly
 * <p>
 * date: 2016/5/10 14:49
 * desc:
 */
public class QrcodeActivity extends BaseActivity implements QrcodeView {

    public static final String PAY_URL = "pay_url";
    public static final String PAY_TYEP = "pay_type";
    public static final String TYEP_ALIPAY = "alipay";
    public static final String TYPE_WECHAT = "wechat";

    @Bind(R.id.top_bar)
    CommonTitleBar topBar;
    @Bind(R.id.rl_qrcode_root)
    RelativeLayout rlQrcodeRoot;
    @Bind(R.id.iv_qrcode)
    ImageView ivQrcode;
    @Bind(R.id.tv_save_qrcode)
    TextView tvSaveQrcode;

    @Inject
    QrcodePresenter mPresenter;

    private String payType;
    private String payUrl;

    @Override
    protected void initComponent() {
        DaggerQrcodeComponent.builder().commonModule(new CommonModule()).qrcodeModule(new
                QrcodeModule(this)).build().inject(this);
        payType = getIntent().getStringExtra(PAY_TYEP);
        payUrl = getIntent().getStringExtra(PAY_URL);
    }

    @Override
    protected int getRootView() {
        return R.layout.activity_qrcode;
    }

    @Override
    protected void setListener() {
        topBar.onBackClick(v -> onBackPressed());
        tvSaveQrcode.setOnClickListener(v -> {
            if (PermissionUtil.checkAndRequestPermission(QrcodeActivity.this, PermissionUtil
                    .PERMISSIONS_STORAGE)) {
                mPresenter.saveView(QrcodeActivity.this, rlQrcodeRoot);
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        tvSaveQrcode.setVisibility(TYEP_ALIPAY.equals(payType) ? View.GONE : View.VISIBLE);
        ivQrcode.setImageBitmap(mPresenter.createQRCodeBitmap(payUrl));
    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (PermissionUtil.checkPermissionRequestResult(QrcodeActivity.this, grantResults,
                "需要存储权限才能保存照片", requestCode)) {
            mPresenter.saveView(QrcodeActivity.this, rlQrcodeRoot);
        }
    }
}
