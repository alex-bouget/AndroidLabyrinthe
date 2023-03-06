package com.cppfdm.labyrinthe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cppfdm.labyrinthe.game.Player;

public class MapActivity extends AppCompatActivity {
    private Player hero;
    @Override

    /***
     * Called when the appliction is created
     *
     * @param savedInstanceState the instance that the application have to create
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Intent intent = getIntent();
        hero = (Player) Serializer.get(intent.getExtras().getString("hero"));
    }

    /**
     * Clase the map and return to labyrinth
     *
     * @param view view where the action come from
     */
    public void closeMap(View view){
        setResult(RESULT_CANCELED);
        finish();
    }
}