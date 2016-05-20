package com.kuaikuaiyu.assistant.ui.income.alipay;

import com.kuaikuaiyu.assistant.rx.PerActivity;
import com.kuaikuaiyu.assistant.ui.income.CommonModule;

import dagger.Component;

@PerActivity
@Component(modules = {CommonModule.class, AlipayModule.class})
public interface AlipayComponent {
    AlipayActivity inject(AlipayActivity alipayActivity);
}
