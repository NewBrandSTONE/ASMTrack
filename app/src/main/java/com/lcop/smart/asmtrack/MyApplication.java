package com.lcop.smart.asmtrack;

import android.app.Application;

import com.lcop.smart.track.sdk.asm.SensorsDataAPI;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SensorsDataAPI.init(this);
    }
}
