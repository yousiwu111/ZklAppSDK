package org.zkl.zklappsdk.activity.AppBarLayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import org.zkl.zklappsdk.R;
import org.zkl.zklappsdk.activity.BaseActivity;
import org.zkl.zklappsdk.databinding.ActivityAppbarlayoutMenuBinding;

/**
 * AppBarLayout 菜单
 * Created by zkl on 2018/3/8.
 */

public class AppBarLayoutMenuActivity extends BaseActivity {

    private ActivityAppbarlayoutMenuBinding bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = bindView(R.layout.activity_appbarlayout_menu);
        initEvent();
    }

    private void initEvent() {
        setTitle("AppBarLayout");
        bind.mTvScroll.setOnClickListener(this);
        bind.mTvEnterAlways.setOnClickListener(this);
        bind.mTvEnterAlwayscollaps.setOnClickListener(this);
        bind.mTvEnterExitUntilCollapsed.setOnClickListener(this);
        bind.mTvCollapsingToolbarLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {//6214835891406751
        super.onClick(view);
        Intent intent = null;
        switch (view.getId()) {
            //scroll 时
            case R.id.mTvScroll:
                intent = new Intent(mContext, AppBarLayoutShowActivity.class);
                intent.putExtra("type", 1);
                startActivity(intent);
                break;
            //enterAlways
            case R.id.mTvEnterAlways:
                intent = new Intent(mContext, AppBarLayoutShowActivity.class);
                intent.putExtra("type", 2);
                startActivity(intent);
                break;
            //enterAlways
            case R.id.mTvEnterAlwayscollaps:
                intent = new Intent(mContext, AppBarLayoutCollapsActivity.class);
                intent.putExtra("type", 1);
                startActivity(intent);
                break;
                //exitUntilCollapsed
            case R.id.mTvEnterExitUntilCollapsed:
                intent = new Intent(mContext, AppBarLayoutCollapsActivity.class);
                intent.putExtra("type", 2);
                startActivity(intent);
                break;
            case R.id.mTvCollapsingToolbarLayout:
                intent = new Intent(mContext, CollapsingToolbarLayoutActivity.class);
                startActivity(intent);
                break;
        }

    }
}
