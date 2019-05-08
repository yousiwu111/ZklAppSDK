package com.zkl.mvp.mvp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.zkl.mvp.StatusBar.StatusBarUtil;

public class BaseActivity extends Activity implements BaseView,View.OnClickListener {

    protected Context mContext;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    /**
     * 页面绑定，主要是为了在setContentView之后设置状态栏
     *
     * @param layoutResID activity的布局文件
     * @param <T>         DataBinding
     * @return DataBinding
     */
    public <T extends android.databinding.ViewDataBinding> T bindView(int layoutResID) {
        T bind = DataBindingUtil.setContentView(this, layoutResID);
        //设置状态栏,必须要在setContentView之后设置
        setStatusBar();
        //加载失败的点击事件
        try {
            //findViewById(R.id.layout_load_failed).setOnClickListener(this);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return bind;
    }

    /**
     * 设置状态栏背景白色，字体黑色
     */
    private void setStatusBar() {
        StatusBarUtil.setStatusBarColor(this, Color.WHITE);
        StatusBarUtil.setStatusBarDarkTheme(this, true);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showContent() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void onClick(View v) {
//        if (v.getId() == R.id.ib_back) {
//            finish();
//        }
    }

    public void jumpToActivity(Class<? extends BaseActivity> aClass) {
        Intent intent = new Intent(this, aClass);
        startActivity(intent);
    }
}
