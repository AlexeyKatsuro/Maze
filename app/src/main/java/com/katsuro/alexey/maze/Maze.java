package com.katsuro.alexey.maze;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by alexey on 4/1/18.
 */

public class Maze implements Drawable {

    private Paint mPaint;

    public Maze(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.GREEN);
    }
    @Override
    public void draw(Canvas canvas) {

    }
}
