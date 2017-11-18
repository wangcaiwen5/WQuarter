package com.example.baseutils.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.igexin.sdk.PushManager;

import com.umeng.analytics.MobclickAgent;

/**
 * Author:wangcaiwen
 * Time:2017/11/14.
 * Description:
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView{

    protected P mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(getLayoutId());
        super.onCreate(savedInstanceState);
        // com.getui.demo.DemoPushService 为第三方自定义推送服务
        PushManager.getInstance().initialize(this.getApplicationContext(), com.example.baseutils.service.PushService.class);
        // com.getui.demo.DemoIntentService 为第三方自定义的推送服务事件接收类
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), com.example.baseutils.service.IntentService.class);
        initView();


    }

    protected abstract void initView();


    protected abstract int getLayoutId();
    /**
     * 默认不关闭当前activity
     * @param clz
     */
    public void gotoActivity(Class<?> clz) {
        gotoActivity(clz, false, null);
    }

    /**
     * 设置是否关闭当前activity
     * @param clz
     * @param isCloseCurrentActivity
     */
    public void gotoActivity(Class<?> clz, boolean isCloseCurrentActivity) {
        gotoActivity(clz, isCloseCurrentActivity, null);
    }

    /**
     * 设置是否关闭当前activity传值
     * @param clz
     * @param isCloseCurrentActivity
     * @param bundle
     */
    public void gotoActivity(Class<?> clz, boolean isCloseCurrentActivity, Bundle bundle) {
        Intent intent = new Intent(this, clz);
        if (bundle != null)
            intent.putExtras(bundle);
        startActivity(intent);
        if (isCloseCurrentActivity) {
            finish();
        }


    }
    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFail() {

    }



    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter != null && mPresenter.isViewBind()){
            mPresenter.detachView();
            mPresenter=null;
        }
    }
}
