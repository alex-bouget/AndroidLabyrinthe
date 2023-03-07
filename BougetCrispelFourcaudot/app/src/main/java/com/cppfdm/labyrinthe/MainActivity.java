package com.cppfdm.labyrinthe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.cppfdm.labyrinthe.game.Labyrinth;
import com.cppfdm.labyrinthe.game.Player;
import com.cppfdm.labyrinthe.view.LabyrinthViewer;
import com.cppfdm.labyrinthe.view.Viewer;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private Viewer game;
    private Player hero;
    private final int MAP_CODE = 14;
    private LabyrinthViewer viewer;

    /***
     * Called when the appliction is created
     *
     * @param savedInstanceState the instance that the application have to create
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();
        game = (Viewer) findViewById(R.id.game);
        game.setMinimumHeight(game.getWidth());

        runLabyrintheChoose();
    }

    /**
     * Run choose Labyrinthe
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
                Labyrinth labyrinth = (Labyrinth) Serializer.get(data.getStringExtra("labyrinth"));
                viewer = new LabyrinthViewer(labyrinth);
                game.addDrawable(viewer);
                game.start();
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
        viewer.offsetX += LabyrinthViewer.scale;

    }

    /***
     * Do a right move for the player
     *
     * @param view view where the action come from
     */
    public void moveRight(View view){
        viewer.offsetX -= LabyrinthViewer.scale;

    }

    /***
     * Do a up move for the player
     *
     * @param view view where the action come from
     */
    public void moveUp(View view){
        viewer.offsetY += LabyrinthViewer.scale;

    }

    /***
     * Do a down move for the player
     *
     * @param view view where the action come from
     */
    public void moveDown(View view){
        viewer.offsetY -= LabyrinthViewer.scale;

    }


    /***
     * Show the map of selected labyrinth to the player
     *
     * @param view view where the action come from
     */
    public void showMap(View view){
        Intent intent = new Intent();
        intent.setClass(this, MapActivity.class);
        intent.putExtra("hero",Serializer.addToSerializer(this.hero));
        startActivityForResult(intent, MAP_CODE);
    }

    private void initComponent(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        LinearLayout up = findViewById(R.id.up);
        up.getLayoutParams().width = (int)(width/5);
        up.getLayoutParams().height = (int)(width/5);
        up.setPadding(0,0,0,0);
    }
}
