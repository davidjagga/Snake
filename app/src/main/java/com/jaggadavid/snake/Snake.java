package com.jaggadavid.snake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;

import java.util.ArrayList;


public class Snake extends ArrayList<SnakeSegment> {





    public Snake() {
        this.add(new SnakeSegment());
        addSegement();
        addSegement();
        addSegement();
        addSegement();
        addSegement();



    }

    public void addSegement(){
        SnakeSegment last = this.get(size()-1);
        float x = last.centerX();
        float y = last.centerY();
        x -= (50);
        y -= (50);
        this.add(new SnakeSegment(x,y));
        System.out.println("Hello: " + x+" "+y);

    }

    public void update(Canvas canvas) {

        for(int i=size()-1; i>=0; i--){
            SnakeSegment current = this.get(i);
            if (i==0){
                //current.update(current.centerX()+current.dx*1, current.centerY()+current.dy*1);

                if (current.centerX()>=canvas.getWidth()){
                    current.dx=-current.dx;
                }
                current.offset(current.dx,current.dy);

            } else {
                SnakeSegment previous = this.get(i-1);
                current.offset(previous.dx, previous.dy);
            }


        }
    }

    public void draw(Canvas canvas) {
        for (SnakeSegment elem:this) {

            elem.draw(canvas);
        }
    }


}
