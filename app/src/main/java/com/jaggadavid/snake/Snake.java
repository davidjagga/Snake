package com.jaggadavid.snake;

import android.graphics.Canvas;
import android.graphics.RectF;

import java.util.ArrayList;


public class Snake extends ArrayList<SnakeSegment> {

    SnakeSegment head;




    public Snake() {
        head = new SnakeSegment();
        this.add(head);
        addSegment();
        addSegment();
        addSegment();
        addSegment();
        addSegment();
        addSegment();




    }

    public SnakeSegment addSegment(){
        SnakeSegment last = this.get(size()-1);
        float x = last.centerX();
        float y = last.centerY();
        x-=(10*last.dx);
        y-=10*last.dy;

        SnakeSegment s = new SnakeSegment(x,y,last.dx, last.dy);
        this.add(s);
//        if(last.turnPointList.size()>0)
//            s.turnPointList.add(new Tuple(last.turnPointList.get(0).get(0), last.turnPointList.get(0).get(1),
//                    last.turnPointList.get(0).get(2), last.turnPointList.get(0).get(3)));
        return s;

    }

    public boolean update(Canvas canvas, Sprite apple) {
        boolean touched = false;
        if (RectF.intersects(head, apple)){

            addSegment();
            touched = true;
        }

        for(int i=size()-1; i>=0; i--){
            SnakeSegment current = this.get(i);
            System.out.println(size());

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
                System.out.println("testing: size = "+turnList.size());
                if (turnList.size()>0) {
                    Tuple tuple = turnList.get(0);
                    if (current.centerX()==tuple.get(0) && current.centerY()==tuple.get(1)) {
                        current.dx = tuple.get(2);
                        current.dy = tuple.get(3);
                        //current.setCenter(tuple.get(0), tuple.get(1));
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
        float val = (float) (5f*Math.sqrt(2));


        float diffx = (x - head.centerX());
        float diffy = (y - head.centerY());

        // i want dx and dy to add up to 10

//        if (x<head.centerX())
//            head.dx*=-1;


        float scale = (float) (Math.sqrt(diffy*diffy+ diffx*diffx));
        head.dx=diffx/scale*val;
        head.dy=diffy/scale*val;

        head.turnPointList.add(new Tuple(head.centerX(), head.centerY(), head.dx, head.dy));
    }


}
