package com.example.cj.customtoast;

import android.app.Application;
import android.content.Context;

/**
 * Created by Marno on 2016/11/11/10:33
 * Function：
 * Desc：
 */
public class App extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getAppContext() {
        return mContext;
    }
}
