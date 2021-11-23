package com.jaggadavid.snake;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.start);




    }

    public void startGame(View view) {
        setContentView(R.layout.activity_main);
    }
}