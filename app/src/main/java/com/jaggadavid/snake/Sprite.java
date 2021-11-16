package com.jaggadavid.snake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.Random;

public class Sprite extends RectF {
    private int color;

    public Sprite(float left, float top, float right, float bottom, int color) {
        super(left, top, right, bottom);
        this.color = color;
    }



    public Sprite(float left, float top, float right, float bottom) {
        this(left, top, right, bottom,Color.RED);

    }

    public Sprite(float centerx, float centery, float radius){
        this(centerx-radius, centery-radius, centerx+radius, centery+radius, Color.RED);
    }

    public Sprite(int boundsx, int boundsy, Random rand) {


        this(rand.nextInt(boundsx-80)+40, rand.nextInt(boundsy-80)+40, 40f);
    }




    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawCircle(centerX(), centerY(), width()/2, paint);
    }
    public String toString(){
        return ""+left +"," +top;
    }
}
