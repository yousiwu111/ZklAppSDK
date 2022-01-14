package org.zkl.zklappsdk.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import org.zkl.zklappsdk.R;
import org.zkl.zklappsdk.adapter.ZoomRecyclerViewAdapter;
import org.zkl.zklappsdk.databinding.ActivityZoomRecyclerviewBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangKelu on 2022/1/13.
 * Description :
 */
public class ZoomRecyclerViewActivity extends BaseActivity {

    public static void startActivity(Context context){
        Intent intent = new Intent(context,ZoomRecyclerViewActivity.class);
        context.startActivity(intent);
    }

    private ActivityZoomRecyclerviewBinding bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = bindView(R.layout.activity_zoom_recyclerview);
        init();
    }

    private void init() {
        ZoomRecyclerViewAdapter mAdapter = new ZoomRecyclerViewAdapter();
        bind.zoomRectclerView.setLayoutManager(new LinearLayoutManager(this));
        bind.zoomRectclerView.setAdapter(mAdapter);
        List<String> data = new ArrayList<>();
        data.add("1");
        data.add("2");
        data.add("3");
        data.add("4");
        data.add("5");
        data.add("6");
        data.add("7");
        data.add("8");
        data.add("9");
        data.add("10");
        mAdapter.setData(data);
    }
}
