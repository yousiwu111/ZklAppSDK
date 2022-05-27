package org.zkl.zklappsdk.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.core.content.ContextCompat;
import android.util.Log;

/**
 * 网络判断工具类
 * Created by zkl on 2018/4/9.
 */
public class NetUtil {

    /**
     * 判断是否有网络链接
     *
     * @param context
     * @return true：有网络连接，false：没有网络连接
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();
            if (networkInfo != null) {
                return networkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 简单判断当前网络类型
     *
     * @param context
     * @return disconnection：无网络，WIFI：无线网络，MONET：移动数据流量
     */
    public static String isWifiNetWorkConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo == null) {
            Log.d("Debug", "网络未连接");
            return "disconnection";
        } else if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            Log.d("Debug", "WIFI 连接:"+networkInfo.getSubtypeName());
            return "WIFI";
        } else {
            Log.d("Debug", "数据流量使用");
            return "MONET";
        }
    }
}
