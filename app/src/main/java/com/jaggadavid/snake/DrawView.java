package com.jaggadavid.snake;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DrawView extends View {
    int count = 0;

    Snake snake;

    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        snake = new Snake();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        snake.update(canvas);
        snake.draw(canvas);
        count++;
        invalidate();
    }
}
