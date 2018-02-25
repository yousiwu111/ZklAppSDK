package org.zkl.zklappsdk.activity;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Activity's base activity
 * Created by zkl on 2017/12/1.
 */

public class BaseActivity extends Activity implements View.OnClickListener{

    public Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext =this;
    }

    /**
     * 页面绑定，主要是为了在setContentView之后设置状态栏
     */
    public <T extends android.databinding.ViewDataBinding> T bindView(int layoutResID) {
        T bind = DataBindingUtil.setContentView(this, layoutResID);
        //设置状态栏,必须要在setContentView之后设置
//        setStatusBar();
        return bind;
    }

    @Override
    public void onClick(View view) {

    }
}
