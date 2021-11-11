package com.jaggadavid.snake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Sprite extends RectF {
    private int dx,dy, color;
    public Sprite(float left, float top, float right, float bottom, int dx, int dy, int color) {
        super(left, top, right, bottom);
        this.dx = dx;
        this.dy = dy;
        this.color = color;
    }

    public Sprite() {
        this(0,0,100,100,1,1, Color.BLUE);
    }

    public Sprite(float left, float top, float right, float bottom) {
        this(left, top, right, bottom, 1,1, Color.BLUE);
    }

    public void update(){
        offset(dx, dy);
    }

    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawCircle(centerX(), centerY(), width()/2, paint);
    }
}
