package com.example.wquarter.ui.activity;

import android.widget.Toast;

import com.example.wquarter.entity.RegisterBean;
import com.example.wquarter.rxretrofit.http.RetrofitManager;
import com.example.wquarter.R;
import com.example.baseutils.base.BaseActivity;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {


    @Override
    protected void initView() {
        RegisterBean registerBean= new RegisterBean();
        RegisterBean init = init(registerBean);
        String s = new Gson().toJson(init);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),s);

        RetrofitManager.getInstance(this).createBaseApi().json("register",body);
        RetrofitManager.getInstance(this).setMyShowCallBack(new RetrofitManager.MyShowCallBack() {
            @Override
            public void onError(Throwable e) {
               //异常
            }

            @Override
            public void onSuccess(ResponseBody responseBody) {
                try {
                    System.out.println("================="+responseBody.string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private RegisterBean init(RegisterBean registerBean) {

        registerBean.h2y_app_id="";
        registerBean.token="";
        RegisterBean.PdBean pdBean = new RegisterBean.PdBean();
        pdBean.account="15055256925";
        pdBean.h2y_app_id="";
        pdBean.password="123456";
        pdBean.ref_one_id=0;
        pdBean.sms_code="ste";
        registerBean.pd=pdBean;

        return registerBean;

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }
}

