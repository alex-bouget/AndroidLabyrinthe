package com.cppfdm.labyrinthe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cppfdm.labyrinthe.game.Player;
import com.cppfdm.labyrinthe.mapView.MapViewer;
import com.cppfdm.labyrinthe.view.Viewer;
import com.cppfdm.labyrinthe.view.core.Drawable;

public class MapActivity extends AppCompatActivity {
    private Player hero;
    private Viewer viewer;
    MapViewer mapViewer;

    /***
     * Called when the appliction is created
     *
     * @param savedInstanceState the instance that the application have to create
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Intent intent = getIntent();
        viewer = (Viewer) findViewById(R.id.game);
        hero = (Player) Serializer.get(intent.getExtras().getString("hero"));
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