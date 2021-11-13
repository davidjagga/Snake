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
                boolean turned = false;
                if (current.centerX()- current.radius>=canvas.getWidth()){
                    current.dx=-current.dx;
                    turned = true;
                }
                if (current.centerX()+ current.radius<=0){
                    current.dx=-current.dx;
                    turned = true;
                }
                if (current.centerY()- current.radius>=canvas.getHeight()){
                    current.dy*=-1;
                    turned = true;
                }
                if (current.centerY()+ current.radius<=0){
                    current.dy*=-1;
                    turned = true;
                }
                if (turned)
                    current.turnPointList.add(new Tuple(current.centerX(), current.centerY(), current.dx, current.dy));
                current.offset(current.dx,current.dy);

            } else {
                SnakeSegment previous = this.get(i-1);
                ArrayList<Tuple> turnList = previous.turnPointList;
                if (turnList.size()>0) {
                    Tuple tuple = turnList.get(0);
                    if (current.centerX() == tuple.get(0) && current.centerY() == tuple.get(1)) {
                        current.dx = tuple.get(2);
                        current.dy = tuple.get(3);
                        turnList.remove(tuple);
                        current.turnPointList.add(new Tuple(current.centerX(), current.centerY(), current.dx, current.dy));
                    }
                }



                current.offset(current.dx, current.dy);


            }


        }
    }

    public void draw(Canvas canvas) {
        for (SnakeSegment elem:this) {

            elem.draw(canvas);
        }
    }


}
