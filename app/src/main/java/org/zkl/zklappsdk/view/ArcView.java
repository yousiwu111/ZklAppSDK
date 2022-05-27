package org.zkl.zklappsdk.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ZhangKelu on 2022/5/18.
 * Description :
 */
public class ArcView extends View {

    private Paint mPaint;
    private Path mPath;

    public ArcView(Context context) {
        this(context,null);
    }

    public ArcView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);

        mPath = new Path();
        RectF baseRectF = new RectF(600,600,800,800);
        mPath.addArc(baseRectF,-30,60);
        baseRectF.inset(-100,-100);
        mPath.arcTo(baseRectF,30,-60);


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath,mPaint);
        Matrix m = new Matrix();
        m.postRotate(60,700,700);
        Path path1 = new Path(mPath);
        path1.transform(m);

        m.reset();
        m.postRotate(-60,700,700);
        Path path2 = new Path(mPath);
        path2.transform(m);
        canvas.drawPath(path1,mPaint);
        canvas.drawPath(path2,mPaint);
    }
}
