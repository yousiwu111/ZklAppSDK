package org.zkl.zklappsdk.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.razerdp.widget.animatedpieview.AnimatedPieView;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;

import org.zkl.zklappsdk.R;
import org.zkl.zklappsdk.databinding.ActivityPieBinding;
import org.zkl.zklappsdk.model.Test;

/**
 * https://github.com/razerdp/AnimatedPieView
 * Created by zkl on 2018/4/2.
 */
public class PieActivity extends BaseActivity {

    private ActivityPieBinding bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = bindView(R.layout.activity_pie);
        initEvent();
    }

    private void initEvent() {
        AnimatedPieViewConfig config = new AnimatedPieViewConfig();
        config.animOnTouch(true)// 点击事件是否播放浮现动画/回退动画（默认true）
        .drawText(true);
        config.startAngle(-90)// 起始角度偏移
                .addData(new Test())//数据（实现IPieInfo接口的bean）
                .addData(new SimplePieInfo(1.0f, Color.RED, "这是第二段"))
                .duration(2000);// 持续时间
// 以下两句可以直接用 mAnimatedPieView.start(config); 解决，功能一致
        bind.animatedPieView.applyConfig(config);
        bind.animatedPieView.start();
    }
}
