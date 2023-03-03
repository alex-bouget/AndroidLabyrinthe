package com.cppfdm.labyrinthe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;

import com.cppfdm.labyrinthe.game.Labyrinth;

public class MainActivity extends AppCompatActivity {

    //Displayed labyrinth
    private Labyrinth labyrinth;
    private final int MAP_CODE = 14;

    /***
     * Called when the appliction is created
     *
     * @param savedInstanceState the instance that the application have to create
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SurfaceView game = (SurfaceView) findViewById(R.id.game);
        game.setMinimumHeight(game.getWidth());

        runLabyrintheChoose();
    }

    /**
     * Run choose Labyrinth
     */
    public void runLabyrintheChoose() {
        Intent intent = new Intent();
        intent.setClass(this, LabyrintheChooserActivity.class);
        startActivityForResult(intent, LabyrintheChooserActivity.INTENT_ID);
    }

    /**
     * Callback of activity
     *
     * @param requestCode the id of the activity
     * @param resultCode the result code of the activity
     * @param data the intent returned by the activity
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == LabyrintheChooserActivity.INTENT_ID) {
            if (resultCode == RESULT_OK) {
                labyrinth = (Labyrinth) Serializer.get(data.getStringExtra("labyrinth"));
                //TODO
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /***
     * Do a left move for the player
     *
     * @param view view where the action come from
     */
    public void moveLeft(View view){

    }

    /***
     * Do a right move for the player
     *
     * @param view view where the action come from
     */
    public void moveRight(View view){

    }

    /***
     * Do a up move for the player
     *
     * @param view view where the action come from
     */
    public void moveUp(View view){

    }

    /***
     * Do a down move for the player
     *
     * @param view view where the action come from
     */
    public void moveDown(View view){

    }

    /***
     * Show the map of selected labyrinth to the player
     *
     * @param view view where the action come from
     */
    public void showMap(View view){
        Intent intent = new Intent();
        intent.setClass(this, MapActivity.class);
        intent.putExtra("labyrinth",Serializer.addToSerializer(labyrinth));
        startActivityForResult(intent, MAP_CODE);
    }
}