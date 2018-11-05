package org.zkl.zklappsdk.activity.lottie;

import android.os.Bundle;
import android.support.annotation.Nullable;

import org.zkl.zklappsdk.R;
import org.zkl.zklappsdk.activity.BaseActivity;
import org.zkl.zklappsdk.databinding.ActivityLottieBinding;

/**
 * lottie 动画
 * Created by zkl on 2018/5/30.
 */

public class LottieActivity extends BaseActivity {

    private ActivityLottieBinding bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = bindView(R.layout.activity_lottie);
        initEvent();
    }

    private void initEvent() {
        setTitle("Lottie动画");
        bind.animationView.setAnimation("checkmark_animation.json");
        bind.animationView.loop(true);
    }
}
