package com.example.wquarter.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;
import com.example.wquarter.R;
import com.example.wquarter.base.BaseApplication;


/**
 * Author:wangcaiwen
 * Time:2017/11/14.
 * Description:
 */

public class ToastUtils {

    private static Handler sMainThreadHandler;
    private static Toast mToast;
    private static Context context = BaseApplication.getContext();

    public static Handler getMainThreadHandler() {
        if (sMainThreadHandler == null) {
            synchronized (ToastUtils.class) {
                if (sMainThreadHandler == null) {
                    sMainThreadHandler = new Handler(Looper.getMainLooper());
                }
            }
        }
        return sMainThreadHandler;
    }


    public static void showMyToast(final String message) {
        if (mToast != null) {
            mToast.cancel();
        }
        getMainThreadHandler().post(new Runnable() {
            @Override
            public void run() {
                mToast = Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT);
                mToast.show();
            }
        });
    }

    public static void showToast(final String message) {
        TextView text = new TextView(context);
        text.setBackgroundResource(R.drawable.shape_rectangle_gray_corner_40);
        text.setTextColor(ResUtils.getColor(R.color.textColor_white));
        text.setText(message);
        text.setPadding(20, 10, 20, 10);
        mToast = new Toast(context);
        mToast.setGravity(Gravity.BOTTOM, 0, 200);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.setView(text);
        if (mToast != null) {
            mToast.cancel();
        }
        getMainThreadHandler().post(new Runnable() {
            @Override
            public void run() {
                mToast.show();
            }
        });
    }

}
