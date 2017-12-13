package org.zkl.zklappsdk.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.zkl.zklappsdk.R;
import org.zkl.zklappsdk.databinding.ActivityDemoListBinding;

import java.util.Arrays;
import java.util.List;

/**
 * 案例列表入口
 * Created by zkl on 2017/12/1.
 */

public class DemoListActivity extends BaseActivity {

    private ActivityDemoListBinding bind;
    private Context mContext;
    private BaseQuickAdapter<String> mAdapter;
    private List<String> mData;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = bindView(R.layout.activity_demo_list);
        mContext = this;
        initEvent();
    }

    private void initEvent() {
        mToolbar = findViewById(R.id.mToolbar);
        //后退
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mData = Arrays.asList(getResources().getStringArray(R.array.demo_name));
        mAdapter = new BaseQuickAdapter<String>(R.layout.item_demo_list, mData) {
            @Override
            protected void convert(BaseViewHolder baseViewHolder, String s) {
                baseViewHolder.setText(R.id.mTxtDemoName, s);
            }
        };
        mAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                switch (i) {
                    case 0:
                        break;
                    //databing 数据绑定
                    case 1:
                        startActivity(new Intent(mContext, DataBindingActivity.class));
                        break;
                    //布局随输入法出现而上移
                    case 2:
                        startActivity(new Intent(mContext, SoftInputUpButtonActivity.class));
                        break;
                    //广告图移动
                    case 3:
                        startActivity(new Intent(mContext, AdImageActivity.class));
                        break;
                    //标签切换
                    case 4:
                        startActivity(new Intent(mContext, TagButtonActivity.class));
                        break;
                }
            }
        });
        bind.mRvDemoList.setAdapter(mAdapter);
        bind.mRvDemoList.setLayoutManager(new LinearLayoutManager(mContext));
    }
}
