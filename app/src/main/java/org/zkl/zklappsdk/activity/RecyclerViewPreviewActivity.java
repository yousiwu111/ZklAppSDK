package org.zkl.zklappsdk.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import org.zkl.zklappsdk.R;
import org.zkl.zklappsdk.databinding.ActivityRecyclerviewPreviewBinding;

/**
 * RecyclerView不用适配器预览完整效果
 * Created by zkl on 2018/2/25.
 */

public class RecyclerViewPreviewActivity extends BaseActivity {

    private ActivityRecyclerviewPreviewBinding bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = bindView(R.layout.activity_recyclerview_preview);
    }
}
