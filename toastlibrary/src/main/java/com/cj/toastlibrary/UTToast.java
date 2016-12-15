package com.cj.toastlibrary;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;

/**
 * Created by cj on 2016/12/13.
 * Function:
 * Desc:
 */

public class UTToast {

    private static UTCustomToast setToast(Context mContext,int iconResId,  String content,int imagLocation) {
        return UTCustomToast.makeToast(mContext, content)
                .setTextColor(Color.WHITE)
                .setTextSize(18)
                .setPadding(10, 10, 10, 10)
                .setTextImage(iconResId)
                .setTextImageSize(50, 50)
                .setTextImageLocation(imagLocation)
                .setImagePadding(10)
                .setBackgroundColor(Color.parseColor("#" + "000000"))
                .setBackgroundAlpha(178)
                .setBackgroundRadius(10)
                .setToastGravity(Gravity.BOTTOM, 0, 150);
    }

    public static void success(Context mContext,int location) {
        setToast(mContext,R.drawable.ic_success_small, "操作成功！",location).showMToast();
    }

    public static void fail(Context mContext,int location) {
        setToast(mContext,R.drawable.ic_failure_small, "操作失败！",location).showMToast();
    }

    public static void remind(Context mContext,int location) {
        setToast(mContext,R.drawable.ic_notice_small, "出现问题！",location).showMToast();
    }

    public static void warn(Context mContext,int location) {
        setToast(mContext,R.drawable.ic_normal_small, "状态异常！",location).showMToast();
    }

}
