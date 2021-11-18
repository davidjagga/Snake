package com.jaggadavid.snake;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Random;

public class DrawView extends View {
    int count = 0;
    int height, width = 0;
    Snake snake;
    Sprite apple;
    Random rand;

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        rand = new Random();
        snake = new Snake();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (snake.update(canvas, apple)){
            snake.draw(canvas);

            apple = new Sprite(width, height, rand);
            apple.draw(canvas);
            count++;
        } else {
            snake.draw(canvas);
            apple.draw(canvas);

        }
        System.out.println(apple.toString());
        invalidate();
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
