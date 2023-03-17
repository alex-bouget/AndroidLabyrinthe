package com.cppfdm.labyrinthe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.cppfdm.labyrinthe.game.Player;
import com.cppfdm.labyrinthe.mapView.MapViewer;
import com.cppfdm.labyrinthe.view.Viewer;

public class MapActivity extends AppCompatActivity {
    private Viewer viewer;
    MapViewer mapViewer;

    /***
     * Called when the application is created
     *
     * @param savedInstanceState the instance that the application have to create
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Intent intent = getIntent();
        viewer = (Viewer) findViewById(R.id.game);
        Player hero = (Player) Serializer.get(intent.getExtras().getString("hero"));
        mapViewer = new MapViewer(hero);
        viewer.addDrawable(mapViewer);
        viewer.start();
    }


    /**
     * Close the map and return to labyrinth
     *
     * @param view view where the action come from
     */
    public void closeMap(View view){
        setResult(RESULT_CANCELED);
        viewer.stop();
        finish();
    }
}