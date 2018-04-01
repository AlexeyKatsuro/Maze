package com.katsuro.alexey.maze;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

/**
 * Created by alexey on 4/1/18.
 */

public class Player implements Drawable {

    private static final String TAG = Player.class.getSimpleName();
    private Paint mPaint;
    private Point mPoint;
    private int width = 50;


    public Player(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mPoint = new Point(0,0);
    }

    @Override
    public void draw(Canvas canvas) {
    canvas.drawRect(mPoint.x,mPoint.y,mPoint.x +width,mPoint.y+width,mPaint);
    }

    public void move(int diffX, int diffY){
        mPoint.x +=diffX;
        mPoint.y +=diffY;
        Log.i(TAG,"Move:" + String.format("x: %d, y: %d",mPoint.x,mPoint.y));
    }
}
