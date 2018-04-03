package com.katsuro.alexey.maze;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexey on 4/1/18.
 */

public class GameManager extends GestureDetector.SimpleOnGestureListener{

    private static final String TAG = GameManager.class.getSimpleName();
    private List<Drawable>  mDrawables = new ArrayList<>();
    private View mView;
    private Player mPlayer;
    private Maze mMaze;
    private Rect mRect = new Rect();
    private int size;

    public GameManager() {
        mPlayer = new Player();
        mMaze = new Maze(35);

        mDrawables.add(mMaze);
        mDrawables.add(mPlayer);
    }

    public void draw(Canvas canvas){
        for (Drawable drawable : mDrawables){
            drawable.draw(canvas,mRect);
        }
    }

    public void setView(View view) {
        mView = view;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        int diffX = 0, diffY = 0;
        diffX = Math.round(-e1.getX()+ e2.getX());
        diffY = Math.round(-e1.getY()+ e2.getY());
        mPlayer.move(diffX,diffY);
        mView.invalidate();
        return super.onFling(e1, e2, velocityX, velocityY);
    }

    public void setScreenSize(int width, int height){
        size = Math.min(width,height);
        mRect.set(
                (width-size)/2,
                (height-size)/2,
                (width+size)/2,
                (height+size)/2
        );
    }
}
