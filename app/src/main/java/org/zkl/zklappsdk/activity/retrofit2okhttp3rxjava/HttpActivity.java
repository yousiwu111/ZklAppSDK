package org.zkl.zklappsdk.activity.retrofit2okhttp3rxjava;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.View;

import org.zkl.zklappsdk.R;
import org.zkl.zklappsdk.activity.BaseActivity;
import org.zkl.zklappsdk.databinding.ActivityHttpBinding;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by zkl on 2018/4/8.
 */
public class HttpActivity extends BaseActivity {

    private ActivityHttpBinding bind;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = bindView(R.layout.activity_http);
        initEvent();
    }

    private void initEvent() {
        bind.btnGet.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            //Get请求
            case R.id.btnGet:
                getUserInfo();
                break;
        }
    }
    private void getUserInfo(){
        NetWorks.getUserInfo("utf-8", "卫衣", new Observer<Bean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Bean bean) {
                Log.d("Debug","onNext:"+bean);
                bind.tvResult.setText(bean.getResult().size()+"");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("Debug","onError"+e.getMessage());
                bind.tvResult.setText(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
