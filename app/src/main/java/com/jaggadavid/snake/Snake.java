package com.jaggadavid.snake;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;

import java.util.ArrayList;


public class Snake extends ArrayList<SnakeSegment> {

    SnakeSegment head;



    public Snake() {
        head = new SnakeSegment();
        this.add(head);
        addSegement();
        addSegement();
        addSegement();
        addSegement();
        addSegement();



    }

    public SnakeSegment addSegement(){
        SnakeSegment last = this.get(size()-1);
        float x = last.centerX();
        float y = last.centerY();
        x -= (50);
        y -= (50);
        SnakeSegment s = new SnakeSegment(x,y);
        this.add(s);
        System.out.println("Hello: " + x+" "+y);
        return s;

    }

    public boolean update(Canvas canvas, Sprite apple) {
        boolean touched = false;
        if (RectF.intersects(head, apple)){


            touched = true;
        }

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


        return touched;

    }

    public void draw(Canvas canvas) {
        for (SnakeSegment elem:this) {

            elem.draw(canvas);
        }
    }


    public void updateDxDy(float x, float y) {
        SnakeSegment head = this.get(0);
        int val = 10;


        float diffx = (x - head.centerX());
        float diffy = (y - head.centerY());

        // i want dx and dy to add up to 10

//        if (x<head.centerX())
//            head.dx*=-1;


        float scale = (float) (Math.sqrt(diffy*diffy+ diffx*diffx));
        head.dx=diffx/scale*10;
        head.dy=diffy/scale*10;

        head.turnPointList.add(new Tuple(head.centerX(), head.centerY(), head.dx, head.dy));
    }


}
