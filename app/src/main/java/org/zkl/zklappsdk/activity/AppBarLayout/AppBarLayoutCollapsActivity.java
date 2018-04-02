package org.zkl.zklappsdk.activity.AppBarLayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import org.zkl.zklappsdk.R;
import org.zkl.zklappsdk.activity.BaseActivity;
import org.zkl.zklappsdk.databinding.ActivityAppbarlayoutCollapsBinding;

/**
 * app:layout_scrollFlags="scroll|enerAlways|enterAlwaysCollapsed"
 * Created by zkl on 2018/3/8.
 */

public class AppBarLayoutCollapsActivity extends BaseActivity {

    private ActivityAppbarlayoutCollapsBinding bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = bindView(R.layout.activity_appbarlayout_collaps);
        int type = getIntent().getIntExtra("type",-1);
        if (type==1){
            bind.mLlCollapsed.setVisibility(View.VISIBLE);
        }else if (type==2){
            bind.mLlExit.setVisibility(View.VISIBLE);
        }

    }
}
