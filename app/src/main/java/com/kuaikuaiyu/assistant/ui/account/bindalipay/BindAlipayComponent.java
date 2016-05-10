package com.kuaikuaiyu.assistant.ui.account.bindalipay;

import com.kuaikuaiyu.assistant.rx.PerFragment;

import dagger.Component;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/4/30
 * Desc:
 */
@PerFragment
@Component(modules = BindAlipayModule.class)
public interface BindAlipayComponent {
    BindAlipayFragment inject(BindAlipayFragment bindAlipayFragment);
}
