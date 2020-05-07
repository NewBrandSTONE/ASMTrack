package com.lcop.smart.track.sdk.asm;

import android.app.Application;

import androidx.annotation.Keep;

@Keep
public class SensorsDataAPI {

    private static SensorsDataAPI INSTANCE;

    public static SensorsDataAPI init(Application app) {
        if (INSTANCE == null) {
            INSTANCE = new SensorsDataAPI();
        }
        return INSTANCE;
    }

}
