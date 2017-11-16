package com.example.wquarter.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.wquarter.R;
import com.example.wquarter.base.BaseActivity;

/**
 * Author:wangcaiwen
 * Time:2017/11/14.
 * Description:
 */

public class MainActivity extends BaseActivity {

    private TextView textView = null;

    @Override
    protected void initView() {
// textView.setText("1321231231");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
