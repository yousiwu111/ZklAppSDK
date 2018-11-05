package org.zkl.zklappsdk.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;

import org.zkl.zklappsdk.R;
import org.zkl.zklappsdk.adapter.MovieAdapter;
import org.zkl.zklappsdk.databinding.ActivityMovieRecyclerviewBinding;
import org.zkl.zklappsdk.view.GalleryItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView实现猫眼电影选择效果
 * Created by zkl on 2018/10/31.
 */

public class MovieRecyclerViewActivity extends BaseActivity {

    private ActivityMovieRecyclerviewBinding bind;
    private MovieAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = bindView(R.layout.activity_movie_recyclerview);
        init();
    }

    private void init() {
        setTitle("电影");
        mAdapter = new MovieAdapter(mContext);
        bind.mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        bind.mRecyclerView.setAdapter(mAdapter);
        List<Integer> list = new ArrayList<>();
        list.add(R.mipmap.movie_1);
        list.add(R.mipmap.movie_1);
        list.add(R.mipmap.movie_1);
        list.add(R.mipmap.movie_1);
        list.add(R.mipmap.movie_1);
        mAdapter.setList(list);
        LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(bind.mRecyclerView);
        bind.mRecyclerView.addItemDecoration(new GalleryItemDecoration());
        bind.mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }
}
