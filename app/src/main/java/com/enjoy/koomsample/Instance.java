package com.enjoy.koomsample;

import android.app.Activity;

public class Instance {

    public static final Instance INSTANCE = new Instance();
    private Activity activity;

    public void addActivity(Activity activity) {
        this.activity = activity;
    }

}
