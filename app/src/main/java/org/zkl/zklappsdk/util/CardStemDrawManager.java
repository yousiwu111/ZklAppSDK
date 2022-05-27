package org.zkl.zklappsdk.util;

import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.MotionEvent;

import org.zkl.zklappsdk.adapter.ZoomRecyclerViewAdapter;
import org.zkl.zklappsdk.view.ZoomRecyclerView;

/**
 * Created by ZhangKelu on 2022/1/15.
 * Description :
 */
public class CardStemDrawManager {

    private ZoomRecyclerView mZoomRecyclerView;

    public CardStemDrawManager(ZoomRecyclerView recyclerView) {
        this.mZoomRecyclerView = recyclerView;
        this.mZoomRecyclerView.setRecyclerPointListener(new ZoomRecyclerView.RecyclerPointListener() {
            @Override
            public void setPoint(MotionEvent e, float scale, float tranX, float tranY) {
                doMotionEvent(e, scale, tranX, tranY);
            }
        });
    }

    /**
     * 将父容器的事件x，y值传递给子view
     * 注意：现在的x，y是相对父容器
     * 子容器在各自的画板上绘制要转换成自己的相对 x和y
     */
    private void doMotionEvent(MotionEvent event, float scale, float tranX, float tranY) {
        float itemX = (event.getX() - tranX) / scale;
        float itemY = (event.getY() - tranY) / scale;

        if (mZoomRecyclerView == null) return;
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            downHandler(itemX, itemY);
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            moveHandler(itemX, itemY);
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            upHandler(itemX, itemY);
        }
    }

    private void upHandler(float x, float y) {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mZoomRecyclerView.getLayoutManager();
        int firstItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
        int lastItemPosition = linearLayoutManager.findLastVisibleItemPosition();
        if (firstItemPosition == lastItemPosition) {

            cardDrawStemOneItemUp(firstItemPosition, x, y);

        } else {
            for (int i = firstItemPosition; i <= lastItemPosition; i++) {
                cardDrawStemOneItemUp(i, x, y);
            }
        }
    }

    private void cardDrawStemOneItemUp(int index, float x, float y) {
        ZoomRecyclerViewAdapter.ViewHolder viewHolder = (ZoomRecyclerViewAdapter.ViewHolder) mZoomRecyclerView.findViewHolderForAdapterPosition(index);
        if (viewHolder != null) {
            // Y轴转成相对item的Y坐标
            float itemY = viewHolder.itemView.getY();
            float topY = y - itemY;
            viewHolder.bind.stem.up(x, topY);
        }
    }

    /**
     * 核心画笔实现方法
     * 一笔数据去绘制当前可见的所有view，这样写有三个原因
     * 1.答题卡大题，绘制不会过于浪费
     * 2.在跨页的时候可以无缝连接
     * 3.减少因区域产生的大量判断计算
     */
    private void moveHandler(float x, float y) {
        LinearLayoutManager manager = (LinearLayoutManager) mZoomRecyclerView.getLayoutManager();
        int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();
        int lastVisibleItemPosition = manager.findLastVisibleItemPosition();

        if (firstVisibleItemPosition == lastVisibleItemPosition) {
            cardDrawStemOneItemMove(firstVisibleItemPosition, x, y);
        } else {
            for (int i = firstVisibleItemPosition; i < lastVisibleItemPosition; i++) {
                cardDrawStemOneItemMove(i, x, y);
            }
        }
    }

    /**
     * 获取当当前可见的view
     * 将父容器坐标转换成该view 的相对坐标
     */
    private void cardDrawStemOneItemMove(int index, float x, float y) {
        ZoomRecyclerViewAdapter.ViewHolder viewHolder = (ZoomRecyclerViewAdapter.ViewHolder) mZoomRecyclerView.findViewHolderForAdapterPosition(index);
        if (viewHolder != null) {
//            if(StemStorageManager.getInstance().isPen()){
            viewHolder.bind.stem.penFun();
//            }else if(StemStorageManager.getInstance().isEraser()){
//                cardDrawViewHolder.mDrawView.eraserFun();
//            }
            // Y轴转成相对item的Y坐标
            float itemY = viewHolder.itemView.getY();
            float topY = y - itemY;
            viewHolder.bind.stem.move(x, topY);
        }
    }

    /**
     * 监听RecyclerView 的down事件
     * 判断 down点在哪个item区域
     */
    private void downHandler(float x, float y) {
        LinearLayoutManager manager = (LinearLayoutManager) mZoomRecyclerView.getLayoutManager();
        int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();
        int lastVisibleItemPosition = manager.findLastVisibleItemPosition();
        if (firstVisibleItemPosition == lastVisibleItemPosition) {
            cardDrawStemOneItemDown(firstVisibleItemPosition, x, y);
        } else {
            for (int i = firstVisibleItemPosition; i < lastVisibleItemPosition; i++) {
                cardDrawStemOneItemDown(i, x, y);
            }
        }
    }

    /**
     * 获取当当前可见的view
     * 将父容器坐标转换成该view 的相对坐标
     */
    private void cardDrawStemOneItemDown(int index, float x, float y) {
        ZoomRecyclerViewAdapter.ViewHolder viewHolder = (ZoomRecyclerViewAdapter.ViewHolder) mZoomRecyclerView.findViewHolderForAdapterPosition(index);
        if (viewHolder != null) {
            float itemY = viewHolder.itemView.getY();
            float topY = y - itemY;
            viewHolder.bind.stem.start(x, topY);
        }
    }
}
