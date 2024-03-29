package org.zkl.zklappsdk.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import androidx.appcompat.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by zkl on 2017/12/8.
 */

public class AdImageView extends AppCompatImageView {

    private int mDx;
    private int mMinDx;

    public AdImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setDy(int dx) {
        if (getDrawable() == null) {
            return;
        }
        mDx = dx - mMinDx;
        if (mDx <= 0) {
            mDx = 0;
        }
        if (mDx > getDrawable().getBounds().height() - mMinDx) {
            mDx = getDrawable().getBounds().height() - mMinDx;
        }
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mMinDx = h;
    }

    public int getDx() {
        return mDx;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        int w = getWidth();
        int h = (int) (getWidth() * 1.0f / drawable.getIntrinsicWidth() * drawable.getIntrinsicHeight());
        drawable.setBounds(0, 0, w, h);
        canvas.save();
        canvas.translate(0, -getDx());
        super.onDraw(canvas);
        canvas.restore();
    }
}
