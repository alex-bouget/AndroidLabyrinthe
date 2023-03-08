package com.cppfdm.labyrinthe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;

import com.cppfdm.labyrinthe.game.Labyrinth;
import com.cppfdm.labyrinthe.game.Player;
import com.cppfdm.labyrinthe.gameView.GameViewer;
import com.cppfdm.labyrinthe.view.Viewer;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private Viewer game;
    private Player hero;
    private final int MAP_CODE = 14;
    private GameViewer viewer;

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
                hero = new Player(labyrinth);
                viewer = new GameViewer(hero);
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
        hero.moveLeft();
        System.out.println(hero.getCurrentCase().getCoord());
    }

    /***
     * Do a right move for the player
     *
     * @param view view where the action come from
     */
    public void moveRight(View view){
        hero.moveRight();
        System.out.println(hero.getCurrentCase().getCoord());
    }

    /***
     * Do a up move for the player
     *
     * @param view view where the action come from
     */
    public void moveUp(View view){
        System.out.println(hero.moveUp());
        System.out.println(hero.getCurrentCase().getCoord());
    }

    /***
     * Do a down move for the player
     *
     * @param view view where the action come from
     */
    public void moveDown(View view){
        hero.moveDown();
        System.out.println(hero.getCurrentCase().getCoord());
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

    /**
     * initialize all component
     */
    private void initComponent(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        Viewer game = findViewById(R.id.game);
        game.getLayoutParams().width = width;
        game.getLayoutParams().height = width;

        //UP button layout
        LinearLayout up = findViewById(R.id.up);
        up.getLayoutParams().height = (int)((height-game.getLayoutParams().height)/3);
        up.getLayoutParams().width = up.getLayoutParams().height;

        //LEFT and RIGHT layout
        LinearLayout lR = findViewById(R.id.leftAndRight);
        lR.getLayoutParams().height = (int)((height-game.getLayoutParams().height)/3);
        lR.getLayoutParams().width = (int)(2.5*lR.getLayoutParams().height);

        //LEFT button
        Button left = findViewById(R.id.left);
        left.getLayoutParams().width = lR.getLayoutParams().height;

        //RIGHT button
        Button right = findViewById(R.id.right);
        right.getLayoutParams().width = lR.getLayoutParams().height;

        //DOWN button layout
        LinearLayout down = findViewById(R.id.down);
        down.getLayoutParams().height = (int)((height-game.getLayoutParams().height)/3);
        down.getLayoutParams().width = up.getLayoutParams().height;

        //MAP button
        LinearLayout map = findViewById(R.id.map);
        map.setPadding(width-width/10,(int)(height/1.8),0,0);

        LinearLayout buttons = findViewById(R.id.buttons);
        buttons.setPadding(0,game.getLayoutParams().height + (height - game.getLayoutParams().height)/35,0,0);

        int nightModeFlags =
                getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                //left.setLinkTextColor(getResources().getColor(R.color.naplesYellow));
                break;

            case Configuration.UI_MODE_NIGHT_NO:
               // doStuff();
                break;

            case Configuration.UI_MODE_NIGHT_UNDEFINED:
               // doStuff();
                break;
        }

    }
}
