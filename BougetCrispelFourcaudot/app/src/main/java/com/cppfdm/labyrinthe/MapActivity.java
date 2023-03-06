package com.cppfdm.labyrinthe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cppfdm.labyrinthe.game.Player;

public class MapActivity extends AppCompatActivity {
    private Player hero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Intent intent = getIntent();
        hero = (Player) Serializer.get(intent.getExtras().getString("hero"));
    }

    public void closeMap(View view){
        setResult(RESULT_CANCELED);
        finish();
    }
}