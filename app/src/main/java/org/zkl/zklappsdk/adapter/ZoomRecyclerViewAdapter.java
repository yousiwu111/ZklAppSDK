package org.zkl.zklappsdk.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.zkl.zklappsdk.R;
import org.zkl.zklappsdk.databinding.ItemZoomRecyclerviewBinding;

import java.util.List;

/**
 * Created by ZhangKelu on 2022/1/13.
 * Description :
 */
public class ZoomRecyclerViewAdapter extends RecyclerView.Adapter<ZoomRecyclerViewAdapter.ViewHolder> {

    private List<String> data;

    public void setData(List<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_zoom_recyclerview, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind.text.setText(data.get(position));
        Log.e("aaa","a:"+data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemZoomRecyclerviewBinding bind;

        public ViewHolder(View itemView) {
            super(itemView);
            bind = DataBindingUtil.bind(itemView);
        }
    }
}
