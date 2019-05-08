package com.zkl.mvp.mvp;

public class BasePresent<V extends BaseView> {

    /**
     * 绑定的View
     */
    private V mvpView;

    /**
     * 绑定View 一般在初始化使用
     *
     * @param mvpView
     */
    public void attachView(V mvpView) {
        this.mvpView = mvpView;
    }

    /**
     * 断开View，一般在onDestroy中使用
     */
    public void detachView() {
        this.mvpView = null;
    }

    /**
     * 是否与View建立连接
     * 每次调用present钱都需要检查是否与View建立连接
     *
     * @return
     */
    public boolean isViewAttached() {
        return mvpView != null;
    }

    /**
     * 获取连接的View
     *
     * @return
     */
    public V getMvpView() {
        return mvpView;
    }
}
