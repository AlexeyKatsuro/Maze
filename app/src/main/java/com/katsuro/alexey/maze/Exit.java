package com.katsuro.alexey.maze;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

/**
 * Created by alexey on 4/3/18.
 */

public class Exit implements Drawable {

    private static final String TAG = Exit.class.getSimpleName();
    private Paint mPaint;
    private Point mPoint;
    private int sizeMaze;


    public Exit(Point start, int sizeMaze){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.GREEN);
        mPoint = start;
        this.sizeMaze = sizeMaze;

    }

    @Override
    public void draw(Canvas canvas, Rect rect) {
        float cellSize = (float) (rect.right - rect.left) / sizeMaze;
        canvas.drawRect(
                rect.left + mPoint.x * cellSize,
                rect.top + mPoint.y * cellSize,
                rect.left + mPoint.x * cellSize + cellSize,
                rect.top + mPoint.y * cellSize + cellSize,
                mPaint);
    }

    public int getX() {
        return mPoint.x;
    }
    public int getY() {
        return mPoint.y;
    }

    public Point getPoint(){
        return mPoint;
    }

    public void goTo(int stepX, int stepY) {

        mPoint.x =stepX;
        mPoint.y =stepY;
    }
}
