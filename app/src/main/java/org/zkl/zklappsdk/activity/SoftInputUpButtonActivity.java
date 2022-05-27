package org.zkl.zklappsdk.activity;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;

import org.zkl.zklappsdk.R;
import org.zkl.zklappsdk.databinding.ActivitySoftInputBinding;

/**
 * 输入法弹出时，把按钮顶上去
 * Created by zkl on 2017/12/7.
 */

public class SoftInputUpButtonActivity extends BaseActivity {

    private ActivitySoftInputBinding bind;
    private Context mContext;
    private int[] sc;
    private int scrollHegit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        bind = bindView(R.layout.activity_soft_input);
        mContext = this;
        initEvent();
    }

    private void initEvent() {
        //根布局
        bind.mRlContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                bind.mRlContainer.getWindowVisibleDisplayFrame(r);
                if (sc == null) {
                    sc = new int[2];
                    bind.mLlLogin.getLocationOnScreen(sc);
                }
                //r.top 是状态栏高度
                int screenHeight = bind.mRlContainer.getRootView().getHeight();
                int softHeight = screenHeight - r.bottom;
                if (scrollHegit == 0&&softHeight>120)
                    scrollHegit = sc[1] +bind.mLlLogin.getHeight() -(screenHeight-softHeight);//可以加个5dp的距离这样，按钮不会挨着输入法
                if (softHeight > 120) {//当输入法高度大于100判定为输入法打开了  设置大点，有虚拟键的会超过100
                    if (bind.mRlContainer.getScrollY() != scrollHegit)
                        scrollToPos(0, scrollHegit);
                } else {//否则判断为输入法隐藏了
                    if (bind.mRlContainer.getScrollY() != 0)
                        scrollToPos(scrollHegit, 0);
                }
            }
        });
    }

    private void scrollToPos(int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.setDuration(250);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                bind.mRlContainer.scrollTo(0, (Integer) valueAnimator.getAnimatedValue());
            }
        });
        animator.start();
    }
}
