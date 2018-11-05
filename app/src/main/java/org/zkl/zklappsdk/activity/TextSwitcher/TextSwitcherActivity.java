package org.zkl.zklappsdk.activity.TextSwitcher;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import org.zkl.zklappsdk.R;
import org.zkl.zklappsdk.activity.BaseActivity;
import org.zkl.zklappsdk.databinding.ActivityTextswitcherBinding;

/**
 * Created by zkl on 2018/4/12.
 */

public class TextSwitcherActivity extends BaseActivity {

    private ActivityTextswitcherBinding bind;
    private Context mContext;
    private int i = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        bind = bindView(R.layout.activity_textswitcher);
        initEvent();
    }

    private void initEvent() {
        bind.ts.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView tv = new TextView(mContext);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
                tv.setTextColor(getResources().getColor(R.color.text1));
                return tv;
            }
        });
        handler.sendEmptyMessage(0);
        bind.ts.setText(getResources().getStringArray(R.array.demo_name)[0]);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (i == getResources().getStringArray(R.array.demo_name).length) {
                i = 0;
            }
            bind.ts.setInAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_in_bottom));
            bind.ts.setOutAnimation(AnimationUtils.loadAnimation(mContext, R.anim.slide_out_up));
            bind.ts.setText(getResources().getStringArray(R.array.demo_name)[i]);
            i++;
            handler.sendEmptyMessageDelayed(0, 2000);
        }
    };
}
