package org.zkl.zklappsdk.util;

import android.content.Context;
import android.util.Log;
import android.view.OrientationEventListener;

/**
 * 用来获取屏幕旋转角度的监听，竖直方向为0，按顺时针旋转角度从0-360，手机平放返回-1
 * Created by zkl on 2018/2/26.
 */

public class MyOrientationEventListener extends OrientationEventListener {
    public MyOrientationEventListener(Context context) {
        super(context);
    }

    public MyOrientationEventListener(Context context, int rate) {
        super(context, rate);
    }

    @Override
    public void onOrientationChanged(int i) {
        Log.d("debug","旋转的角度："+i);
    }
}
