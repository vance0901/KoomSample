package com.vance.koomsample;

import android.app.Application;


import com.kwai.koom.base.CommonConfig;
import com.kwai.koom.base.DefaultInitTask;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化 MonitorManager
        DefaultInitTask.INSTANCE.init(this);

    }
}
