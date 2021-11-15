package com.jaggadavid.snake;

import java.util.ArrayList;

public class Tuple extends ArrayList<Float> {
    public Tuple(float x, float y, float dx, float dy){
        this.add(x);
        this.add(y);
        this.add(dx);
        this.add(dy);

    }
}
