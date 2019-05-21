package com.zkl.mvp.activity;

import com.zkl.mvp.mvp.BasePresent;

public class SecondPresent extends BasePresent<SecondView> {

    public void getData(){
        if (true){
            getMvpView().showDate("data");
        }else{
            getMvpView().showError();
        }
    }
}
