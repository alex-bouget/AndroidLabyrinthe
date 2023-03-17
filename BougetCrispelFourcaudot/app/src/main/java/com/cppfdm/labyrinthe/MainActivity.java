package com.cppfdm.labyrinthe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.cppfdm.labyrinthe.game.Labyrinth;
import com.cppfdm.labyrinthe.game.Player;
import com.cppfdm.labyrinthe.gameView.GameViewer;
import com.cppfdm.labyrinthe.utils.SpriteEnum;
import com.cppfdm.labyrinthe.view.Viewer;

import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    public static final int INTENT_ID = 203;
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
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                game.setMinimumHeight(game.getWidth());
                Labyrinth labyrinth = (Labyrinth) Serializer.get(getIntent().getStringExtra("labyrinth"));
                SpriteEnum heroSprite = (SpriteEnum) Serializer.get(getIntent().getStringExtra("player"));
                SpriteEnum monsterSprite =  (SpriteEnum) Serializer.get(getIntent().getStringExtra("monster"));
                hero = new Player(labyrinth);
                viewer = new GameViewer(hero, heroSprite, monsterSprite);
                game.addDrawable(viewer);
                game.stop();
                game.start();
            }
        }, 500);   //2 seconds
    }

    /***
     * Do a left move for the player
     *
     * @param view view where the action come from
     */
    public void moveLeft(View view) {
        if (hero == null || hero.isDead() || hero.isWin()) {
            return;
        }
        hero.moveLeft();
    }

    /***
     * Do a right move for the player
     *
     * @param view view where the action come from
     */
    public void moveRight(View view) {
        if (hero == null || hero.isDead() || hero.isWin()) {
            return;
        }
        hero.moveRight();
    }

    /***
     * Do a up move for the player
     *
     * @param view view where the action come from
     */
    public void moveUp(View view) {
        if (hero == null || hero.isDead() || hero.isWin()) {
            return;
        }
        hero.moveUp();
    }

    /***
     * Do a down move for the player
     *
     * @param view view where the action come from
     */
    public void moveDown(View view) {
        if (hero == null || hero.isDead() || hero.isWin()) {
            return;
        }
        hero.moveDown();
    }


    /***
     * Show the map of selected labyrinth to the player
     *
     * @param view view where the action come from
     */
    public void showMap(View view) {
        if (hero == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, MapActivity.class);
        intent.putExtra("hero", Serializer.addToSerializer(hero));
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

        //MENU button
        LinearLayout menu = findViewById(R.id.menu);
        menu.setPadding(width/70,(int)(height/1.8),0,0);

        //all buttons
        LinearLayout buttons = findViewById(R.id.buttons);
        buttons.setPadding(0,game.getLayoutParams().height + (height - game.getLayoutParams().height)/35,0,0);
    }
    /***
     * Show the menu to choose labyrinth
     *
     * @param view view where the action come from
     */
    public void showMenu(View view){
        finish();
    }

    /**
     * Callback of activity
     *
     * @param requestCode the id of the activity
     * @param resultCode  the result code of the activity
     * @param data        the intent returned by the activity
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == EndGameActivity.INTENT_ID) {
            if (resultCode == RESULT_OK) {
                String result = data.getStringExtra("result");
                if (result.equals("return")) {
                    finish();
                }
                if (result.equals("continue")) {
                    game.resume();
                    hero.reset();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
