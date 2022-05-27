package org.zkl.zklappsdk.view;

import android.graphics.Rect;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import org.zkl.zklappsdk.util.DpUtil;

/**
 * Created by zkl on 2018/10/31.
 */

public class GalleryItemDecoration extends RecyclerView.ItemDecoration {
    int mPageMargin = 10; //自定义默认item边距
    int mLeftPageVisibleWidth = 125; //第一张图片的左边距

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int positon = parent.getChildAdapterPosition(view); //获得当前item的position
        int itemCount = parent.getAdapter().getItemCount(); //获得item的数量
        int leftMargin = (positon == 0) ? DpUtil.dp2px(mLeftPageVisibleWidth, view.getContext()) : DpUtil.dp2px(mPageMargin, view.getContext()); //如果position为0，设置leftMargin为计算后边距，其他为默认边距
        int rightMargin = (positon == (itemCount - 1)) ? DpUtil.dp2px(mLeftPageVisibleWidth, view.getContext()) : DpUtil.dp2px(mPageMargin, view.getContext()); //同上，设置最后一张图片
        RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) view.getLayoutParams();
        lp.setMargins(leftMargin, 30, rightMargin, 60); //30和60分别是item到上下的margin
        view.setLayoutParams(lp);//设置参数
        super.getItemOffsets(outRect, view, parent, state);
    }
}
