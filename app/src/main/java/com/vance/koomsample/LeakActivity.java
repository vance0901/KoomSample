package com.vance.koomsample;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.vance.koomsample.test.A;
import com.kwai.koom.base.MonitorManager;
import com.kwai.koom.javaoom.monitor.OOMMonitor;
import com.kwai.koom.javaoom.monitor.utils.SizeUnit;

public class LeakActivity extends Activity {
    private byte[] buffer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak);
        Instance.INSTANCE.addActivity(this);
        buffer = new byte[360_000*1024];//这里OOM-演示让其崩溃，玩转koom
    }
}
