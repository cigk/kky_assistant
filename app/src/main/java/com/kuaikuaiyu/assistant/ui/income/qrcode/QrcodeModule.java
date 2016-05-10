package com.kuaikuaiyu.assistant.ui.income.qrcode;

import com.kuaikuaiyu.assistant.rx.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by binlly
 * <p>
 * date: 2016/5/10 16:50
 * desc:
 */
@Module
public class QrcodeModule {
    private QrcodeView mView;

    public QrcodeModule(QrcodeView qrcodeView) {
        this.mView = qrcodeView;
    }

    @PerActivity
    @Provides
    QrcodeView getQrcodeView() {
        return mView;
    }
}
