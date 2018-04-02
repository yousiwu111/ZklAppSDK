package org.zkl.zklappsdk.activity.AppBarLayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import org.zkl.zklappsdk.R;
import org.zkl.zklappsdk.activity.BaseActivity;
import org.zkl.zklappsdk.databinding.ActivityAppbarlayoutShowBinding;

/**
 * 案例
 * Created by zkl on 2018/3/8.
 */

public class AppBarLayoutShowActivity extends BaseActivity {

    private ActivityAppbarlayoutShowBinding bind;
    private int type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind =bindView(R.layout.activity_appbarlayout_show);
        type = getIntent().getIntExtra("type",-1);
        if (type==1){
            bind.mTbScroll.setVisibility(View.VISIBLE);
        }else if (type==2){
            bind.mTbEnterAlways.setVisibility(View.VISIBLE);
        }
    }
}
