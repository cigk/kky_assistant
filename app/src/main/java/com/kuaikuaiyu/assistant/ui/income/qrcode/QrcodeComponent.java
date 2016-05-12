package com.kuaikuaiyu.assistant.ui.income.qrcode;

import com.kuaikuaiyu.assistant.rx.PerActivity;
import com.kuaikuaiyu.assistant.ui.income.CommonModule;

import dagger.Component;

/**
 * Created by binlly
 * <p>
 * date: 2016/5/10 16:53
 * desc:
 */
@PerActivity
@Component(modules = {CommonModule.class, QrcodeModule.class})
public interface QrcodeComponent {
    QrcodeActivity inject(QrcodeActivity qrcodeActivity);
}
