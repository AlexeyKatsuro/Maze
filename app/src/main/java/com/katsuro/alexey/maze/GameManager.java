package com.katsuro.alexey.maze;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

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
    private Exit mExit;
    private Rect mRect = new Rect();
    private int screenSize;
    private int mazeSize = 5;

    public GameManager() {
        create(mazeSize);
    }

    private void create(int mazeSize){
        mMaze = new Maze(mazeSize);
        mPlayer = new Player(mMaze.getEndPoint(),mazeSize);
        mExit = new Exit(mMaze.getStartPoint(),mazeSize);

        mDrawables.clear();
        mDrawables.add(mMaze);
        mDrawables.add(mPlayer);
        mDrawables.add(mExit);
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
        int diffX, diffY;
        diffX = Math.round(e2.getX() - e1.getX());
        diffY = Math.round(e2.getY() - e1.getY());
        if (Math.abs(diffX) > Math.abs(diffY)) {
            diffX = diffX > 0 ? 1 : -1;
            diffY = 0;
        } else {
            diffX = 0;
            diffY = diffY > 0 ? 1 : -1;
        }
        int stepX = mPlayer.getX();
        int stepY = mPlayer.getY();

        while (!mMaze.isWall(stepX + diffX, stepY + diffY)) {
            stepX += diffX;
            stepY += diffY;
            if (diffX != 0) {
                if (!mMaze.isWall(stepX, stepY + 1)
                        || !mMaze.isWall(stepX, stepY - 1)) {
                    break;
                }
            }
            if (diffY != 0) {
                if (!mMaze.isWall(stepX + 1, stepY)
                        || !mMaze.isWall(stepX - 1, stepY)) {
                    break;
                }
            }
        }

        mPlayer.goTo(stepX, stepY);
        if (mExit.getPoint().equals(mPlayer.getPoint())) {
            create(mazeSize+=5);
        }

        mView.invalidate();
        return super.onFling(e1, e2, velocityX, velocityY);
    }

    public void setScreenSize(int width, int height){
        screenSize = Math.min(width,height);
        mRect.set(
                (width-screenSize)/2,
                (height-screenSize)/2,
                (width+screenSize)/2,
                (height+screenSize)/2
        );
    }
}
