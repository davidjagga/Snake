package com.jaggadavid.snake;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.Random;

public class DrawView extends View {
    int count = 0;
    int height, width = 0;
    Snake snake;
    Sprite apple;
    Random rand;
    ArrayList<Bomb> bomblist = new ArrayList<>();

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        rand = new Random();
        snake = new Snake();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int updateVal = snake.update(canvas, apple, bomblist);
        if (updateVal==1){
            snake.draw(canvas);

            apple = new Sprite(width, height, rand);
            apple.draw(canvas);
            count++;
            Bomb newBomb = new Bomb(width, height, rand);
            while (RectF.intersects(newBomb, snake.freeSpace)){
                newBomb = new Bomb(width, height, rand);
            }

            bomblist.add(newBomb);
            for (Bomb bomb: bomblist){
                bomb.draw(canvas);
            }
        } else {
            snake.draw(canvas);
            apple.draw(canvas);
            for (Bomb bomb: bomblist){
                bomb.draw(canvas);
            }

        }


        invalidate();ConstraintLayout parent = (ConstraintLayout) this.getParent();
        TextView scoreView = parent.findViewById(R.id.score);
        scoreView.setText("Score: "+ count);
        if (updateVal==2) {
            snake = new Snake();
            count=0;
            bomblist = new ArrayList<Bomb>();
            invalidate();
        } else {
            invalidate();
        }

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        height = getHeight();
        width = getWidth();
        apple = new Sprite(getWidth(), getHeight(), rand);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            snake.updateDxDy(event.getX(), event.getY());
        }

        return super.onTouchEvent(event);

    }
}
