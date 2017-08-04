package com.example.gd001.myapplication6;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by gd001 on 17-8-4.
 */

public class Indicator extends View {
    private Paint mPaint;
    private Paint mPaintCur;
    private int itemCount;
    private int curPos;
    private final static int ITEM_RADIUS = 6;

    public Indicator(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);

        mPaintCur = new Paint();
        mPaintCur.setColor(Color.RED);
        mPaintCur.setAntiAlias(true);
        curPos = 0;
        itemCount = 3;
    }

    public void setItemCount(int count) {
        itemCount = count;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measureDimension(ITEM_RADIUS*2+ITEM_RADIUS*2*(2*(itemCount-1)), widthMeasureSpec);
        int height = measureDimension(ITEM_RADIUS*2, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    private int measureDimension(int defaultSize, int measureSpec){
        int result;

        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if(specMode == MeasureSpec.EXACTLY){
            result = specSize;
        }else{
            result = defaultSize;
            if(specMode == MeasureSpec.AT_MOST){
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int x = ITEM_RADIUS;
        int y = ITEM_RADIUS;
        int dis = ITEM_RADIUS * 4;
        int radius = ITEM_RADIUS;
        for(int i=0; i<itemCount; ++i) {
            if (i == curPos) {
                canvas.drawCircle(x+i*dis, y, radius, mPaintCur);
            } else {
                canvas.drawCircle(x+i*dis, y, radius, mPaint);
            }
        }
        super.onDraw(canvas);
    }

    /**
     * 当前位置
     * @param position 现在的位置
     */
    public void setCurPos(int position) {
        curPos = position;
        invalidate();
    }
}
