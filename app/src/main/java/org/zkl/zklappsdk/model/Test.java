package org.zkl.zklappsdk.model;

import android.graphics.Color;

import com.razerdp.widget.animatedpieview.data.IPieInfo;

public class Test implements IPieInfo {
    @Override
    public double getValue() {
        //这个数值将会决定其所占有的饼图百分比
        return 0.5f;
    }

    @Override
    public int getColor() {
        //该段甜甜圈的颜色，请返回@colorInt，不要返回@colorRes
        return Color.GREEN;
    }

    @Override
    public String getDesc() {
        //描述文字，可不返回
        return "这是一个测试";
    }
}