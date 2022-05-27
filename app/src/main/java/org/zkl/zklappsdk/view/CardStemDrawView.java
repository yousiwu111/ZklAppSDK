package org.zkl.zklappsdk.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;


import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: yujinghua
 * @CreateDate: 2021/10/18 20:50
 */
public class CardStemDrawView extends View {

    private Paint mPaint;
    private Path mPath;
    private int color = Color.parseColor("#479FFF");
    ;
    private int penWidth = 2;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private int mViewWidth;
    private int mViewHeight;

    private int penMode = 1; //1:画笔；2：橡皮擦
    private float mX;
    private float mY;

    private boolean enable; //如果ImageView中的图片没有展示成功，则不能绘制

    private List<Float[]> mRequestPath;

    //当前的画板在第几个item里面
    private int position;

    //判断是否有一个点在画板范围内，如果有，则保存下来
    private boolean isInRect = false;
    private boolean isMove = false;

    public void setPosition(int position) {
        this.position = position;
    }

    public CardStemDrawView(Context context) {
        super(context);
        init();
    }

    public CardStemDrawView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public CardStemDrawView(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
        init();
    }

    /**
     * 获取画板的宽高
     * 注意点，如果该view 初始化设置为GONE，这个方法addOnPreDrawListener也会调用，并且
     * 获得的宽高为0；
     * 解决方法，如果不可见设置为invisible
     */
    private void init() {
        initPaint();
        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                getViewTreeObserver().removeOnPreDrawListener(this);
                mViewWidth = getWidth();
                mViewHeight = getHeight();
                //判断一下是否有绘制数据
                setOnePageData();
                return true;
            }
        });
    }

    private void initPaint() {
        if (mPaint == null) {
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setColor(color);
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setStrokeWidth(penWidth);
        }

        if (mPath == null) {
            mPath = new Path();
        }

        mRequestPath = new ArrayList<>();
    }

    public void start(float x, float y) {
        if (/*StemStorageManager.getInstance().isEraser()*/false) {
            penMode = 2;
        } else {
            penMode = 1;
        }
        createBitmap(mViewWidth, mViewHeight);
        if (penMode == 1) {
            penFun();
        } else if (penMode == 2) {
            eraserFun();
        }
        isMove = false;
        mPath.moveTo(x, y);
        Float[] actionPosition = {x, y};
        mRequestPath.add(actionPosition);
        mX = x;
        mY = y;
    }

    /**
     * 在绘制过程中判断，改笔数据是否全部不在改view的显示范围内
     * 如果不是，则不进行存储
     *
     * @param x
     * @param y
     */
    public void move(float x, float y) {
        if (penMode == 1) {
            mPath.quadTo(mX, mY, x, y);
        } else if (penMode == 2 && mCanvas != null) {
            mCanvas.drawLine(mX, mY, x, y, mPaint);
            // mCanvas.drawCircle(x, y, 12, mPaint);
        }
        isMove = true;
        Float[] actionPosition = {x, y};
        mRequestPath.add(actionPosition);
        invalidate();
        if (0 <= y && y <= mViewHeight) {
            isInRect = true;
        }
        mX = x;
        mY = y;
    }

    public void up(float x, float y) {
        if (penMode == 1) {
            mPath.quadTo(mX, mY, x, y);
        } else if (penMode == 2 && mCanvas != null) {
            mCanvas.drawLine(mX, mY, x, y, mPaint);
            mCanvas.drawCircle(x, y, 12, mPaint);
        }
        Float[] actionPosition = {x, y};
        mRequestPath.add(actionPosition);
        if (mCanvas != null)
            mCanvas.drawPath(mPath, mPaint);
        mPath.reset();
        isMove = false;
        invalidate();
        if (isInRect) {
            doRequest();
        }
        isInRect = false;
    }

    /**
     * 开始绘制时，或者有绘制数据的时候才去创建Bitmap
     * 防止用户未做任何操作，申请了无用的内存
     *
     * @param width
     * @param height
     */
    public void createBitmap(int width, int height) {
        //Log.e("DrawStem", "drawView width="+getWidth() + " ||height="+getHeight());
        if (height > 0 && width > 0 && mBitmap == null) {
            mViewHeight = height;
            mViewWidth = width;
            try {
                mBitmap = Bitmap.createBitmap(mViewWidth, mViewHeight, Bitmap.Config.ARGB_8888);
                mCanvas = new Canvas(mBitmap);
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //createBitmap();
        if (canvas != null && mBitmap != null) {
            canvas.drawBitmap(mBitmap, 0, 0, null);
            canvas.drawPath(mPath, mPaint);
            if (penMode == 2 && isMove) {
                //当在橡皮模式画圆
                Paint p = new Paint();
                //设置画笔的颜色
                p.setColor(Color.BLACK);
                p.setStrokeWidth(5);
                p.setStyle(Paint.Style.STROKE);
                p.setAntiAlias(true);
                p.setAlpha(50);
                //绘制一个小圆（作为小球）
                canvas.drawCircle(mX, mY, 18, p);
            }
        }
    }

    public void clear() {
        if (null != mCanvas) {
            Paint mPaint = new Paint();
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            mCanvas.drawPaint(mPaint);
            invalidate();
        }
    }

    public void penFun() {
        if (null != mPaint) {
            mPaint.reset();
        } else {
            mPaint = new Paint();
        }
        mPaint.setAntiAlias(true);
        mPaint.setColor(color);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(penWidth);
    }

    public void eraserFun() {
        if (null != mPaint) {
            mPaint.reset();
        } else {
            mPaint = new Paint();
        }

        mPaint.setColor(Color.TRANSPARENT);
        mPaint.setAntiAlias(false);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(40);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAlpha(0xFF);
        mPaint.setColor(Color.BLUE);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    }

    /**
     * 将数据存储在本地，为了减少数据存储，做了差值计算，后面的点都是第一个点的偏移量
     */
    private void doRequest() {
//        if (mRequestPath.size() <= 2) return;
//        Float[][] point = new Float[mRequestPath.size()][2];
//        float firstPointX = 0;
//        float firstPointY = 0;
//        for (int i = 0; i < point.length; i++) {
//            point[i] = mRequestPath.get(i);
//            if (i == 0) {
//                firstPointX = point[i][0];
//                firstPointY = point[i][1];
//
//            } else {
//                point[i][0] = point[i][0] - firstPointX;
//                point[i][1] = point[i][1] - firstPointY;
//            }
//        }
//        mRequestPath.clear();
//        StemDataEntry.StemPenDataEntry stemPenDataEntry = new StemDataEntry.StemPenDataEntry();
//        stemPenDataEntry.p = point;
//        stemPenDataEntry.r = 2 == penMode;
//
//        StemStorageManager.getInstance().saveOneDrawData(position, stemPenDataEntry);
    }

    /**
     * 界面初始化完成，绘制该页数据
     */
    private void setOnePageData() {
        clear();
//        List<StemDataEntry.StemPenDataEntry> entries = StemStorageManager.getInstance().findOnePageDrawData(position);
//        if (entries != null && entries.size() > 0) {
//            for (int i = 0; i < entries.size(); i++) {
//                StemDataEntry.StemPenDataEntry entry = entries.get(i);
//                drawByData(entry);
//            }
//        }
    }

//    private void drawByData(StemDataEntry.StemPenDataEntry entry) {
//        if (entry == null && entry.p == null) return;
//
//        Path path = getPath(entry.p, 0, 0);
//        if (entry.r) {
//            //橡皮模式
//            eraserFun();
//        } else {
//            penFun();
//        }
//        drawPath(path);
//    }

    public void drawPath(Path path) {
        createBitmap(mViewWidth, mViewHeight);
        if (mCanvas != null && mBitmap != null) {
            mCanvas.drawPath(path, mPaint);
            invalidate();
        }
    }

    private Path getPath(Float[][] drawPath, int xx, int yy) {
        Path path = new Path();
        if (drawPath != null && drawPath.length > 0) {
            float firstPointX = 0;
            float firstPointY = 0;
            for (int i = 0; i < drawPath.length; i++) {
                if (i == 0) {
                    firstPointX = drawPath[i][0] - xx;
                    firstPointY = drawPath[i][1] - yy;
                    path.moveTo(firstPointX, firstPointY);
                } else {
                    path.lineTo(drawPath[i][0] + firstPointX, drawPath[i][1] + firstPointY);
                }

            }

        }
        return path;
    }

}
