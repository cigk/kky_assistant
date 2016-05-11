package com.kuaikuaiyu.assistant.ui.income.qrcode;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseActivity;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.ui.income.CommonModule;
import com.kuaikuaiyu.assistant.ui.widgets.CommonTitleBar;
import com.kuaikuaiyu.assistant.utils.QRCodeUtil;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by binlly
 * <p>
 * date: 2016/5/10 14:49
 * desc:
 */
public class QrcodeActivity extends BaseActivity implements QrcodeView {
    @Bind(R.id.top_bar)
    CommonTitleBar top_bar;
    @Bind(R.id.ll_qrcode_root)
    LinearLayout ll_qrcode_root;
    @Bind(R.id.iv_qrcode)
    ImageView iv_qrcode;
    @Bind(R.id.tv_save_qrcode)
    TextView tv_save_qrcode;

    @Inject
    QrcodePresenter mPresenter;

    @Override
    protected void initComponent() {
        DaggerQrcodeComponent.builder().commonModule(new CommonModule()).qrcodeModule(new
                QrcodeModule(this)).build().inject(this);
    }

    @Override
    protected int getRootView() {
        return R.layout.activity_qrcode;
    }

    @Override
    protected void setListener() {
        top_bar.onBackClick(v -> onBackPressed());
        tv_save_qrcode.setOnClickListener(v -> {
            mPresenter.saveImage(QrcodeActivity.this, mPresenter.createViewBitmap(ll_qrcode_root));
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        Bitmap bitmap = QRCodeUtil.createImage("http://www.jianshu.com/p/3141d4e46240", 100, 100,
                null);
        iv_qrcode.setImageBitmap(bitmap);
    }

    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }
}
