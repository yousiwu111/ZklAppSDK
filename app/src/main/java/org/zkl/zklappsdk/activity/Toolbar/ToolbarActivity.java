package org.zkl.zklappsdk.activity.Toolbar;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;

import org.zkl.zklappsdk.R;
import org.zkl.zklappsdk.activity.BaseActivity;
import org.zkl.zklappsdk.databinding.ActivityWhatToolbarBinding;

/**
 * Toolbar
 * 依赖包 compile 'com.android.support:appcompat-v7:23.1.1'
 * 设置主题 <item name="windowActionBar">false</item>  <item name="android:windowNoTitle">true</item>
 * 完整类名 android.support.v7.widget.Toolbar
 * Created by zkl on 2018/3/6.
 */

public class ToolbarActivity extends BaseActivity {

    private ActivityWhatToolbarBinding bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = bindView(R.layout.activity_what_toolbar);
        initEvent();
    }

    private void initEvent() {
        //一级菜单
        bind.mToolbar.inflateMenu(R.menu.toolbar_menu);
        bind.mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                //创建一个二级菜单
                PopupMenu popupMenu = new PopupMenu(mContext,bind.mToolbar);
                popupMenu.inflate(R.menu.toolbar_sub_menu);
                return false;
            }
        });
    }
}
