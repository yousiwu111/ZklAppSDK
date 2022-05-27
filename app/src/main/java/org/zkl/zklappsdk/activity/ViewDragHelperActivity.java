package org.zkl.zklappsdk.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import org.zkl.zklappsdk.R;
import org.zkl.zklappsdk.databinding.ActivityViewdragBinding;

/**
 * ViewDragHelper View拖拽工具类
 * Created by zkl on 2018/2/24.
 */

public class ViewDragHelperActivity extends BaseActivity {

    private ActivityViewdragBinding bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = bindView(R.layout.activity_viewdrag);
    }
}
