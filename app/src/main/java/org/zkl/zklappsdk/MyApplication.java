package org.zkl.zklappsdk;

import android.app.Application;

/**
 * Created by zkl on 2018/4/8.
 */
public class MyApplication extends Application {

    public static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.instance = this;
    }
}
