package com.cppfdm.labyrinthe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cppfdm.labyrinthe.game.Labyrinth;
import com.cppfdm.labyrinthe.game.Player;
import com.cppfdm.labyrinthe.gameView.GameViewer;
import com.cppfdm.labyrinthe.utils.AssetsCommand;
import com.cppfdm.labyrinthe.utils.SpriteEnum;
import com.cppfdm.labyrinthe.utils.ViewerCommand;

public class HomeActivity extends AppCompatActivity {
    SpriteEnum heroSprite = SpriteEnum.LINK;
    SpriteEnum monsterSprite = SpriteEnum.MONSTER;
    Labyrinth labyrinth;
    String labyrinthName = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        updateIcons();
    }

    public void updateIcons() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        ImageView playerImageView = (ImageView) findViewById(R.id.playerSpriteImageView);
        playerImageView.setImageBitmap(ViewerCommand.resizeBitmapWidth(heroSprite.getIcon(playerImageView), displayMetrics.widthPixels/10));


        ImageView monsterImageView = (ImageView) findViewById(R.id.monsterSpriteImageView);
        monsterImageView.setImageBitmap(ViewerCommand.resizeBitmapWidth(monsterSprite.getIcon(monsterImageView), displayMetrics.widthPixels/10));

        Button runButton = (Button) findViewById(R.id.runButton);

        if (labyrinthName == null || heroSprite == null || monsterSprite == null) {
            runButton.setEnabled(false);
        } else {
            runButton.setEnabled(true);
        }

        TextView textView = (TextView) findViewById(R.id.labyrinthTextView);
        if (labyrinthName == null) {
            textView.setText("None");
            return;
        }
        textView.setText(labyrinthName);
    }

    /**
     * Run choose Labyrinthe
     */
    public void runLabyrintheChoose(View v) {
        Intent intent = new Intent();
        intent.setClass(this, LabyrintheChooserActivity.class);
        startActivityForResult(intent, LabyrintheChooserActivity.INTENT_ID);
    }


    public void runPlayerChoice(View v) {
        runSpriteChoiceActivity(false);
    }

    public void runEnemyChoice(View v) {
        runSpriteChoiceActivity(true);
    }

    /**
     * Run the sprite choice
     *
     * @param monster choice the monster
     */
    public void runSpriteChoiceActivity(boolean monster) {
        Intent intent = new Intent();
        intent.setClass(this, SpriteChoiceActivity.class);
        int add = (monster) ? 1 : 0;
        startActivityForResult(intent, SpriteChoiceActivity.INTENT_ID+add);
    }

    public void runGame(View v) {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        intent.putExtra("labyrinth", Serializer.addToSerializer(labyrinth));
        intent.putExtra("player", Serializer.addToSerializer(heroSprite));
        intent.putExtra("monster", Serializer.addToSerializer(monsterSprite));
        startActivityForResult(intent, MainActivity.INTENT_ID);
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
        if (requestCode == LabyrintheChooserActivity.INTENT_ID) {
            if (resultCode == RESULT_OK) {
                labyrinth = (Labyrinth) Serializer.get(data.getStringExtra("labyrinth"));
                labyrinthName = data.getStringExtra("name");
            }
        }
        if (requestCode == SpriteChoiceActivity.INTENT_ID) {
            if (resultCode == RESULT_OK) {
                heroSprite = (SpriteEnum) Serializer.get(data.getStringExtra("sprite"));
            }
        }
        if (requestCode == SpriteChoiceActivity.INTENT_ID+1) {
            if (resultCode == RESULT_OK) {
                monsterSprite = (SpriteEnum) Serializer.get(data.getStringExtra("sprite"));
            }
        }
        updateIcons();
        super.onActivityResult(requestCode, resultCode, data);
    }
}