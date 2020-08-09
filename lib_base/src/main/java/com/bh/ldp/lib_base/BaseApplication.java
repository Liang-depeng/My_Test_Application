package com.bh.ldp.lib_base;

import android.content.Context;

import androidx.multidex.MultiDexApplication;

/**
 * created by Da Peng at 2019/6/26
 */
public abstract class BaseApplication extends MultiDexApplication {

    protected static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getAppContent(){
        return context;
    }

}
