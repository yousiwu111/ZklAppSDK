package org.zkl.zklappsdk.view;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by ZhangKelu on 2022/1/13.
 * Description :
 */
public class ZoomRecyclerView extends RecyclerView {

    private static final int DEFAULT_SCALE_DURATION = 300;
    private static final float DEFAULT_SCALE_FACTOR = 1.f;
    private static final float DEFAULT_MAX_SCALE_FACTOR = 2.0f;
    private static final float DEFAULT_MIN_SCALE_FACTOR = 0.5f;
    private static final String PROPERTY_SCALE = "scale";
    private static final String PROPERTY_TRANX = "tranX";
    private static final String PROPERTY_TRANY = "tranY";
    private static final float INVALID_TOUCH_POSITION = -1;

    private GestureDetectorCompat mGestureDetector;
    private ScaleGestureDetector mScaleDetector;

    private float mScaleFactor;  // 缩放因子
    float mMaxScaleFactor;       // 最大缩放系数
    float mMinScaleFactor;       // 最小缩放系数
    float mDefaultScaleFactor;   // 默认缩放系数 双击缩小后的缩放系数 暂不支持小于1
    int mScaleDuration;         // 缩放时间 ms

    float mScaleCenterX;         // 缩放中心 X
    float mScaleCenterY;         // 缩放中心 Y
    float mMaxTranX;             // 当前缩放系数下最大的X偏移量
    float mMaxTranY;             // 当前缩放系数下最大的Y偏移量

    ValueAnimator mScaleAnimator; //缩放动画
    float mViewWidth;             // 宽度
    float mViewHeight;            // 高度
    float mTranX;                 // x偏移量
    float mTranY;                 // y偏移量

    boolean isScaling = false;    // 是否正在缩放


    public ZoomRecyclerView(Context context) {
        this(context, null);
    }

    public ZoomRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZoomRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mDefaultScaleFactor = DEFAULT_SCALE_FACTOR;
        mScaleFactor = DEFAULT_SCALE_FACTOR;
        mMinScaleFactor = DEFAULT_MIN_SCALE_FACTOR;
        mMaxScaleFactor = DEFAULT_MAX_SCALE_FACTOR;
        mScaleDuration = DEFAULT_SCALE_DURATION;
        mGestureDetector = new GestureDetectorCompat(getContext(), new GestureListener());
        mScaleDetector = new ScaleGestureDetector(getContext(), new ScaleListener());
    }

    private void zoom(float startFactor, float endFactor) {
        if (mScaleAnimator == null) {
            newZoomAnimation();
        }

        if (mScaleAnimator.isRunning()) return;

        mMaxTranX = mViewWidth - mViewWidth * endFactor;
        mMaxTranY = mViewHeight - mViewHeight * endFactor;

        float startTranX = mTranX;
        float startTranY = mTranY;
        float endTranX = mTranX - (endFactor - startFactor) * mScaleCenterX;
        float endTranY = mTranY - (endFactor - startFactor) * mScaleCenterY;
        // Log.e("aaa","mTranX"+mTranX+",mTranY:"+mTranY);
        PropertyValuesHolder scaleHolder = PropertyValuesHolder
                .ofFloat(PROPERTY_SCALE, startFactor, endFactor);
        PropertyValuesHolder tranXHolder = PropertyValuesHolder
                .ofFloat(PROPERTY_TRANX, startTranX, endTranX);
        PropertyValuesHolder tranYHolder = PropertyValuesHolder
                .ofFloat(PROPERTY_TRANY, startTranY, endTranY);
        mScaleAnimator.setValues(scaleHolder, tranXHolder, tranYHolder);
        mScaleAnimator.setDuration(mScaleDuration);
        mScaleAnimator.start();
    }

    private void newZoomAnimation() {
        mScaleAnimator = new ValueAnimator();
        mScaleAnimator.setInterpolator(new DecelerateInterpolator());
        mScaleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mScaleFactor = (float) animation.getAnimatedValue(PROPERTY_SCALE);
                setTranslationXY((float) animation.getAnimatedValue(PROPERTY_TRANX), (float) animation.getAnimatedValue(PROPERTY_TRANY));
                invalidate();
            }
        });
    }

    private void setTranslationXY(float tranX, float tranY) {
        mTranX = tranX;
        mTranY = tranY;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(mTranX, mTranY);
        canvas.scale(mScaleFactor, mScaleFactor);
        super.dispatchDraw(canvas);
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        //Log.e("aaa","mTranX"+mTranX+",mTranY:"+mTranY);
        boolean gesture = mGestureDetector.onTouchEvent(e);
        boolean scaleTouch = mScaleDetector.onTouchEvent(e);
        return super.onTouchEvent(e);
    }

    private class ScaleListener implements ScaleGestureDetector.OnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            final float mLastScaleFactor = mScaleFactor;
            Log.e("ZoomRecyclerView", "getScaleFactor:" + detector.getScaleFactor());
            mScaleFactor *= detector.getScaleFactor();
            mScaleFactor = Math.max(mMinScaleFactor, Math.min(mScaleFactor, mMaxScaleFactor));
            mMaxTranX = mViewWidth - (mViewWidth * mScaleFactor);
            mMaxTranY = mViewHeight - (mViewHeight * mScaleFactor);

            mScaleCenterX = detector.getFocusX();
            mScaleCenterY = detector.getFocusY();

            float offsetX = mScaleCenterX * (mLastScaleFactor - mScaleFactor);
            float offsetY = mScaleCenterY * (mLastScaleFactor - mScaleFactor);

            setTranslationXY(mTranX + offsetX, mTranY + offsetY);

            isScaling = true;
            invalidate();
            return true;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            return true;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            if (mScaleFactor <= mDefaultScaleFactor) {
                mScaleCenterX = -mTranX / (mScaleFactor - 1);
                mScaleCenterY = -mTranY / (mScaleFactor - 1);
                mScaleCenterX = Float.isNaN(mScaleCenterX) ? 0 : mScaleCenterX;
                mScaleCenterY = Float.isNaN(mScaleCenterY) ? 0 : mScaleCenterY;
                zoom(mScaleFactor, mDefaultScaleFactor);
            }
            isScaling = false;
        }
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            float startFactor = mScaleFactor;
            float endFactor;
            // 如果当前是没有放大，那么需要方大
            if (mScaleFactor == mDefaultScaleFactor) {
                mScaleCenterX = e.getX();
                mScaleCenterY = e.getY();
                endFactor = mMaxScaleFactor;
            } else {
                mScaleCenterX = mScaleFactor == 1 ? e.getX() : -mTranX / (mScaleFactor - 1);
                mScaleCenterY = mScaleFactor == 1 ? e.getY() : -mTranY / (mScaleFactor - 1);
                endFactor = mDefaultScaleFactor;
            }
            zoom(startFactor, endFactor);
            return super.onDoubleTap(e);
        }
    }

}
