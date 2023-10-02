package com.enjoy.koomsample;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.kwai.koom.base.InitTask;
import com.kwai.koom.base.MonitorManager;
import com.kwai.koom.javaoom.monitor.OOMHprofUploader;
import com.kwai.koom.javaoom.monitor.OOMMonitorConfig;
import com.kwai.koom.javaoom.monitor.OOMReportUploader;

import java.io.File;

public class OOMMonitorInitTask implements InitTask {

    private static final String TAG = "OOMMonitorInitTask";
    public static OOMMonitorInitTask INSTANCE = new OOMMonitorInitTask();

    @Override
    public void init(@NonNull Application application) {
        OOMMonitorConfig config = new OOMMonitorConfig.Builder()
                //内存快照回调
                .setHprofUploader(new OOMHprofUploader() {
                    /**
                     *
                     * @param file 内存快照文件
                     * @param hprofType 快照类型：ORIGIN（原始） 、STRIPPED（裁剪）
                     */
                    @Override
                    public void upload(@NonNull File file, @NonNull HprofType hprofType) {
                        // 上传file至服务端
                    }
                })
                .setReportUploader(new OOMReportUploader() {
                    /**
                     *
                     * @param file 泄露报告文件
                     * @param content 泄露报告内容
                     */
                    @Override
                    public void upload(@NonNull File file, @NonNull String content) {
                        // 上传file至服务端
                        Log.i(TAG, "upload: " + content);
                    }
                }).build();
        //初始化 OOMMonitor
        MonitorManager.addMonitorConfig(config);
    }
}
