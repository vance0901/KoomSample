package com.enjoy.koomsample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;

import com.enjoy.forksamples.NativeLib;
import com.kwai.koom.base.DefaultInitTask;
import com.kwai.koom.base.MonitorManager;
import com.kwai.koom.javaoom.monitor.OOMHprofUploader;
import com.kwai.koom.javaoom.monitor.OOMMonitor;
import com.kwai.koom.javaoom.monitor.OOMMonitorConfig;
import com.kwai.koom.javaoom.monitor.OOMReportUploader;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Lance";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.enjoy.koomsample.R.layout.activity_main);
        //初始化 MonitorManager
        OOMMonitorInitTask.INSTANCE.init(getApplication());
        // 启动 OOMMonitor
        OOMMonitor.INSTANCE.startLoop(true, false, 5_000L);
        test();

    }

    public void leak(View view) {
        startActivity(new Intent(this, LeakActivity.class));
    }


    private void test() {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                }
            }
        };
        t1.start();

        File file = new File(getExternalFilesDir(""), "samples/a.hprof");
        file.getParentFile().mkdirs();
        Log.i(TAG, "test: fork before");
        // suspendAll
        int pid = new NativeLib().fork();
        
        if (pid == 0) {
            Log.i(TAG, "父进程fork完成,子进程执行");
//            printThreads("子进程");
//            Log.i(TAG, "子进程: " + t1.getState());
            try {
                Debug.dumpHprofData(file.getAbsolutePath());
            } catch (IOException e) {
                Log.i(TAG, "子进程dump失败 ", e);
            }
            Log.i(TAG, "子进程dump完成");
        } else if (pid > 0) {
//            printThreads("父进程");
            // 主进程
            Log.i(TAG, "父进程fork完成,继续执行");
        } else {
            Log.i(TAG, "父进程fork失败,继续执行");
        }
    }
}