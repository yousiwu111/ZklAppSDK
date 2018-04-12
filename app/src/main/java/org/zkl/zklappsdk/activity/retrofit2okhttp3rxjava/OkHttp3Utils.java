package org.zkl.zklappsdk.activity.retrofit2okhttp3rxjava;

import android.os.Environment;
import android.util.Log;

import org.zkl.zklappsdk.MyApplication;
import org.zkl.zklappsdk.util.NetUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zkl on 2018/4/8.
 */
public class OkHttp3Utils {

    private static OkHttpClient mOkHttpClient;
    //设置缓存目录
    private static File cacheDirectory = new File(MyApplication.getInstance().getApplicationContext().getCacheDir().getAbsolutePath(), "MyCache");
    private static Cache cache = new Cache(cacheDirectory, 10 * 1024 * 1024);

    public static OkHttpClient getOkHttpClient() {
        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient.Builder()
                    .retryOnConnectionFailure(true)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .cache(cache)
                    .addInterceptor(interceptor)
                    .build();
        }
        return mOkHttpClient;
    }

    private static Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            NetUtil.isWifiNetWorkConnected(MyApplication.getInstance());
            if (!NetUtil.isNetworkConnected(MyApplication.getInstance())){
                Log.d("Debug","没有网络连接，将去获取缓存");
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            Log.d("Debug",request.url().toString());
            Response response = chain.proceed(request);
            Log.d("Debug",response.body().toString());
            if (NetUtil.isNetworkConnected(MyApplication.getInstance())){
                return response.newBuilder().header("Cache-Control","public, max-age="+0)
                        .removeHeader("Pragma")
                        .build();
            }else {
                int maxStale = 60 * 60 * 24;
                return response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };
}
