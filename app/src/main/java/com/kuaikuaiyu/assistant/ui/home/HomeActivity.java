package com.kuaikuaiyu.assistant.ui.home;

import android.os.Bundle;

import com.kuaikuaiyu.assistant.R;
import com.kuaikuaiyu.assistant.base.BaseActivity;
import com.kuaikuaiyu.assistant.base.BasePresenter;
import com.kuaikuaiyu.assistant.model.domain.HttpResult;
import com.kuaikuaiyu.assistant.model.domain.PageInfo;
import com.kuaikuaiyu.assistant.model.domain.StrollItem;
import com.kuaikuaiyu.assistant.model.service.EqxService;
import com.kuaikuaiyu.assistant.net.NetUtil;
import com.kuaikuaiyu.assistant.utils.UIUtil;
import com.kuaikuaiyu.assistant.utils.logger.Logger;
import com.umeng.update.UmengUpdateAgent;

import java.util.List;

import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/3
 * Desc:    主页面
 */
public class HomeActivity extends BaseActivity {


    @Override
    protected void initComponent() {
    }

    @Override
    protected int getRootView() {
        return R.layout.activity_home;
    }

    @Override
    protected void setListener() {
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, new
                HomeFragment()).commit();
        UIUtil.postDelayed(() -> UmengUpdateAgent.update(HomeActivity.this), 500);
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    /**
     * 测试Retrofit CallBack
     */
    @OnClick(R.id.btn_test)
    public void test() {
        EqxService service = NetUtil.createForTest(EqxService.class);
        Call call = service.getShowItems();
        call.enqueue(new Callback<HttpResult<Object, PageInfo, List<StrollItem>>>() {
            @Override
            public void onResponse(Call<HttpResult<Object, PageInfo, List<StrollItem>>> call, Response<HttpResult<Object, PageInfo, List<StrollItem>>> response) {
                Logger.d(response.body().code + "");
                Logger.d(response.body().msg);
                Logger.d(response.body().map.toString());
                Logger.d(response.body().list.toString());
            }

            @Override
            public void onFailure(Call<HttpResult<Object, PageInfo, List<StrollItem>>> call, Throwable t) {
                Logger.d("onFailure: %s", t.getMessage());
            }
        });
//        call.cancel();
    }
}
