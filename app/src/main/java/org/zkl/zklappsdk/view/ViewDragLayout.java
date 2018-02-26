package org.zkl.zklappsdk.view;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * ViewDragHelper的使用
 * Created by zkl on 2018/2/24.
 */

public class ViewDragLayout extends LinearLayout {

    private ViewDragHelper mDragger;

    private View mDragView;
    private View mAutoBackView;
    private View mEdgeTrackerView;

    private Point mAutoBackOriginPos = new Point();

    public ViewDragLayout(Context context) {
        this(context, null);
    }

    public ViewDragLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewDragLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mDragger = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return (mDragView == child || child == mAutoBackView);
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                return left;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                return top;
            }

            //手指释放的时候回调
            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                //mAutoBackView手指释放时可以自动回去
                if (releasedChild == mAutoBackView) {
                    mDragger.settleCapturedViewAt(mAutoBackOriginPos.x, mAutoBackOriginPos.y);
                    Log.d("debug","执行onViewReleased方法：mAutoBackOriginPos.x："+mAutoBackOriginPos.x+"，mAutoBackOriginPos.y："+mAutoBackOriginPos.y);
                    invalidate();
                }
            }

            //在边界拖动时回调
            @Override
            public void onEdgeDragStarted(int edgeFlags, int pointerId) {
                mDragger.captureChildView(mEdgeTrackerView, pointerId);
            }
        });
        mDragger.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mAutoBackOriginPos.x = mAutoBackView.getLeft();
        mAutoBackOriginPos.y = mAutoBackView.getTop();
        Log.d("debug","onLayout方法执行，mAutoBackView.getLeft()："+mAutoBackView.getLeft()+"，mAutoBackView.getTop()："+mAutoBackView.getTop());
    }

    @Override
    public void computeScroll() {
        if (mDragger.continueSettling(true)) {
            Log.d("debug","执行：computeScroll，mAutoBackView.getLeft()："+mAutoBackView.getLeft()+"，mAutoBackView.getTop()："+mAutoBackView.getTop());
            invalidate();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mDragView = getChildAt(0);
        mAutoBackView = getChildAt(1);
        mEdgeTrackerView = getChildAt(2);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mDragger.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragger.processTouchEvent(event);
        return true;
    }
}
