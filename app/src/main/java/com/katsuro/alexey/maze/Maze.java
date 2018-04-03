package com.katsuro.alexey.maze;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * Created by alexey on 4/1/18.
 */

public class Maze implements Drawable {

    private Paint mWallPaint;
    private final boolean[][] mArray;
    private final int size;

    private final Point mEndPoint = new Point(1,1);
    private  Point mStartPoint;
    private int bestScore =0;


    public Maze(int size){
        mWallPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mWallPaint.setColor(Color.BLUE);

        this.size = size;
        mArray = new boolean[size][size];
        generateMaze();
    }

    private void generateMaze(){
        for(int i=0; i<size;i++){
            for (int j = 0; j<size;j++){
                mArray[i][j] = i % 2 != 0 && j % 2 !=0
                && i < size-1 && j < size -1;
            }
        }

        Random random = new Random();
        Stack<Point> stack = new Stack<>();
        stack.push(mEndPoint);

        while (stack.size()>0){
            Point current = stack.peek();
            List<Point> unusedNeighbors = new LinkedList<>();
            //left
            if(current.x>2){
                if(!isUsedCell(current.x-2,current.y)){
                    unusedNeighbors.add(new Point(current.x-2,current.y));
                }
            }

            //top
            if(current.y>2){
                if(!isUsedCell(current.x,current.y-2)){
                    unusedNeighbors.add(new Point(current.x,current.y-2));
                }
            }

            //bottom
            if(current.y<size-2){
                if(!isUsedCell(current.x,current.y+2)){
                    unusedNeighbors.add(new Point(current.x,current.y+2));
                }
            }

            //right
            if(current.x<size-2){
                if(!isUsedCell(current.x+2,current.y)){
                    unusedNeighbors.add(new Point(current.x+2,current.y));
                }
            }

            if(unusedNeighbors.size() > 0){
                int rnd = random.nextInt(unusedNeighbors.size());
                Point direction = unusedNeighbors.get(rnd);
                int diffX = (direction.x - current.x)/2;
                int diffY = (direction.y - current.y)/2;
                mArray[current.y+diffY][current.x+diffX] = true;
                stack.push(direction);
            } else {
                if(bestScore<stack.size()){
                    bestScore = stack.size();
                    mStartPoint = current;
                }
                stack.pop();
            }
        }
    }

    private boolean isUsedCell(int x, int y){
        if(x<0 || y<0 || x>size || y>size){
            return true;
        }
        return mArray[y-1][x]
        || mArray[y][x-1]
        || mArray[y+1][x]
        || mArray[y][x+1];
    }

    @Override
    public void draw(Canvas canvas, Rect rect) {
        float cellSize= (float) (rect.right-rect.left)/size;

        for(int i = 0; i<size;++i){
            for (int j = 0; j<size;++j){
                if(!mArray[i][j]){
                    float left = j*cellSize+rect.left;
                    float top = i*cellSize+rect.top;
                    canvas.drawRect(left,top,left+cellSize,top+cellSize, mWallPaint);
                }
            }
        }
    }
}
