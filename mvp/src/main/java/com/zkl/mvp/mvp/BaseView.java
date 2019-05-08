package com.zkl.mvp.mvp;

public interface BaseView {
    /**
     * 显示正在加载
     */
    void showLoading();

    /**
     * 显示界面内容
     */
    void showContent();

    /**
     * 显示请求错误提示
     */
    void showError();
}
