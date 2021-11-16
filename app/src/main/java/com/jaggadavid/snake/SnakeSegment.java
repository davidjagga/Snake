package com.jaggadavid.snake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SnakeSegment extends RectF {

    int color;
    float dx, dy;
    float radius;

    ArrayList<Tuple> turnPointList = new ArrayList<Tuple>();



    public SnakeSegment(float left, float top, float right, float bottom, float dx, float dy, int color) {
        super(left, top, right, bottom);
        this.dx = dx;
        this.dy = dy;
        this.color = color;
        radius=(top-bottom)/2;
    }

    public SnakeSegment(float cX, float cY){
        this(cX-50,cY-50, cX+50, cY+50, 5f, 5f, Color.BLUE);

    }

    public SnakeSegment() {
        this(10,10,110,110,5f,5f, Color.BLUE);
    }



    public void update(float newX, float newY) {
        offsetTo(newX,newY);
    }




    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas. drawCircle(centerX(), centerY(), width()/2,paint);
    }


}
