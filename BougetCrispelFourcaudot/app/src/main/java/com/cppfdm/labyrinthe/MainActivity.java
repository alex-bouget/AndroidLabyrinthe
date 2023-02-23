package com.cppfdm.labyrinthe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SurfaceView game = (SurfaceView) findViewById(R.id.game);
        game.setMinimumHeight(game.getWidth());
    }

    public void moveLeft(View view){

    }

    public void moveRight(View view){

    }

    public void moveUp(View view){

    }

    public void moveDown(View view){

    }

}