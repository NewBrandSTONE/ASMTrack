package com.lcop.smart.track.sdk.asm;

import android.util.Log;
import android.view.View;

import androidx.annotation.Keep;

/**
 * 需要ASM帮我们插入的埋点代码
 *
 * @author 01380154
 * @version 2020/4/23
 */
public class SensorsDataAutoTrackHelper {

    private static final String TAG = SensorsDataAutoTrackHelper.class.getName();

    @Keep
    public static void trackViewOnClick(View view) {
        try {
            Log.d(TAG, "trackViewOnClick: CanonicalName" + view.getClass().getCanonicalName());
        } catch (Exception e) {
            Log.e(TAG, "trackViewOnClick: ", e);
        }
    }

}
