package org.zkl.zklappsdk.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.zkl.zklappsdk.R;
import org.zkl.zklappsdk.databinding.ActivityAdImageBinding;
import org.zkl.zklappsdk.view.AdImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * 滚动时，能看到所有内容的图片，图片会移动
 * Created by zkl on 2017/12/8.
 */

public class AdImageActivity extends BaseActivity {


    private ActivityAdImageBinding bind;
    private Context mContext;
    private BaseQuickAdapter<String> mAdapter;
    private List<String> mData;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind  =bindView(R.layout.activity_ad_image);
        initEvent();
    }

    private void initEvent() {
        mData = new ArrayList<>();
        for (int i=0;i<30;i++){
            mData.add("");
        }
        mAdapter = new BaseQuickAdapter<String>(R.layout.item_adimage,mData) {
            @Override
            protected void convert(BaseViewHolder holder, String s) {
                int position = holder.getAdapterPosition();
                if (position > 0 && position % 6 == 0) {
                    holder.setVisible(R.id.id_tv_title, false);
                    holder.setVisible(R.id.id_tv_desc, false);
                    holder.setVisible(R.id.id_iv_ad, true);
                } else {
                    holder.setVisible(R.id.id_tv_title, true);
                    holder.setVisible(R.id.id_tv_desc, true);
                    holder.setVisible(R.id.id_iv_ad, false);
                }
            }
        };
        mLinearLayoutManager = new LinearLayoutManager(mContext);
        bind.idRecyclerview.setLayoutManager(mLinearLayoutManager);
        bind.idRecyclerview.setAdapter(mAdapter);
        bind.idRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int fPos = mLinearLayoutManager.findFirstVisibleItemPosition();
                int lPos = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
                for (int i = fPos; i <= lPos; i++) {
                    View view = mLinearLayoutManager.findViewByPosition(i);
                    AdImageView adImageView = view.findViewById(R.id.id_iv_ad);
                    if (adImageView.getVisibility() == View.VISIBLE) {
                        adImageView.setDy(mLinearLayoutManager.getHeight() - view.getTop());
                        //aa
                    }
                }
            }
        });
    }
}
