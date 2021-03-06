package com.example.cj.customtoast;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;

import com.cj.toastlibrary.UTCustomToast;

/**
 * Created by 程杰 on 2016/10/31.
 * Function:
 * Desc:系统单例土司工具类
 */

public class ToastUtil {

    private static Context mContext = App.getAppContext();

    private static UTCustomToast setToast(int iconResId, String bgColor, String content) {
        return UTCustomToast.makeToast(mContext, content)
                .setTextColor(Color.WHITE)
                .setTextSize(22)
                .setPadding(32, 24, 32, 24)
                .setTextImage(iconResId)
                .setTextImageSize(80, 80)
                .setTextImageLocation(UTCustomToast.LEFT)
                .setImagePadding(30)
                .setBackgroundColor(Color.parseColor("#" + bgColor))
                .setBackgroundRadius(10)
                .setToastGravity(Gravity.BOTTOM, 0, 100);
    }

    public static void success(String content) {
        setToast(R.drawable.success, "cc4caf50", content).showMToast();
    }

    public static void fail(String content) {
        setToast(R.drawable.fail, "ccF44336", content).showMToast();
    }

    public static void remind(String content) {
        setToast(R.drawable.remind, "ccFF5722", content).showMToast();
    }

    public static void warn(String content) {
        setToast(R.drawable.warn, "ccFFC107", content).showMToast();
    }

    public static void common(String content) {
        setToast(R.drawable.common, "cc000000", content).showMToast();
    }
}
