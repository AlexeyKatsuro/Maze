package com.katsuro.alexey.maze;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by alexey on 4/1/18.
 */

public class MazeView extends View {

    GameManager mGameManager;

    public MazeView(Context context) {
        super(context);
    }

    public MazeView(Context context, GameManager gameManager){
        this(context);
        mGameManager = gameManager;
        mGameManager.setView(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mGameManager.draw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mGameManager.setScreenSize(w,h);
    }
}
