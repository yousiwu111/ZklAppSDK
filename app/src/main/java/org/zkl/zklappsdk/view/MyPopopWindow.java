package org.zkl.zklappsdk.view;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;

/**
 * Created by zkl on 2018/3/27.
 */

public class MyPopopWindow extends PopupWindow {
    public MyPopopWindow(Context context) {
        super(context);
    }

    public MyPopopWindow(View contentView, int width, int height) {
        super(contentView, width, height);
    }

    public MyPopopWindow(View contentView, int width, int height, boolean focusable) {
        super(contentView, width, height, focusable);
    }

    @Override
    public void showAsDropDown(View anchor) {
        try {//在版本24及以上会出现显示问题
            if (Build.VERSION.SDK_INT >= 24) {
                int[] location = new int[2];
                anchor.getLocationOnScreen(location);
                int x = location[0];
                int y = location[1] + anchor.getHeight();
                this.showAtLocation(anchor, Gravity.NO_GRAVITY, x, y);
            } else {
                this.showAtLocation(anchor, Gravity.NO_GRAVITY, 0, 0);
            }
            super.showAsDropDown(anchor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
