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
import org.zkl.zklappsdk.activity.AppBarLayout.AppBarLayoutMenuActivity;
import org.zkl.zklappsdk.activity.TextSwitcher.TextSwitcherActivity;
import org.zkl.zklappsdk.activity.Toolbar.ToolbarActivity;
import org.zkl.zklappsdk.activity.retrofit2okhttp3rxjava.HttpActivity;
import org.zkl.zklappsdk.databinding.ActivityDemoListBinding;
import org.zkl.zklappsdk.util.MyOrientationEventListener;

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
    private MyOrientationEventListener listener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = bindView(R.layout.activity_demo_list);
        mContext = this;
        initEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        listener.enable();
    }

    @Override
    protected void onPause() {
        super.onPause();
        listener.disable();
    }

    private void initEvent() {
        listener = new MyOrientationEventListener(mContext);
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
                    //ViewDragHelper使用
                    case 5:
                        startActivity(new Intent(mContext, ViewDragHelperActivity.class));
                        break;
                    //RecyclerView不用适配器预览完整效果
                    case 6:
                        startActivity(new Intent(mContext, RecyclerViewPreviewActivity.class));
                        break;
                    //Toolbar的使用
                    case 7:
                        startActivity(new Intent(mContext, ToolbarActivity.class));
                        break;
                    //AppBarLayout的使用
                    case 8:
                        startActivity(new Intent(mContext, AppBarLayoutMenuActivity.class));
                        break;
                    //popupwindow
                    case 9:
                        startActivity(new Intent(mContext, PopupwindowActivity.class));
                        break;
                        //饼状图
                    case 10:
                        startActivity(new Intent(mContext, PieActivity.class));
                        break;
                        //retrofit2 rxjava okhttp3
                    case 11:
                        startActivity(new Intent(mContext, HttpActivity.class));
                        break;
                        //TextSwitcher实现文字上下翻滚
                    case 12:
                        startActivity(new Intent(mContext, TextSwitcherActivity.class));
                        break;
                }
            }
        });
        bind.mRvDemoList.setAdapter(mAdapter);
        bind.mRvDemoList.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter.setOnRecyclerViewItemLongClickListener(new BaseQuickAdapter.OnRecyclerViewItemLongClickListener() {
            @Override
            public boolean onItemLongClick(View view, int i) {
                return false;
            }
        });
    }
}
