package com.cppfdm.labyrinthe;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.cppfdm.labyrinthe.utils.TilesetEnum;

import java.util.Random;

public class TilesetChoiceActivity extends AppCompatActivity {
    public static int INTENT_ID = 56;

    LinearLayout linearLayout;
    TilesetEnum choice;
    Button buttonChoice;

    /**
     * Create the Activity
     *
     * @param savedInstanceState the last instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        linearLayout = (LinearLayout) findViewById(R.id.scroller);
        int index = 0;
        for (TilesetEnum tilesetEnum : TilesetEnum.getAllTileset()) {
            Button imageButton = createSprite(tilesetEnum);
            linearLayout.addView(createText(tilesetEnum));
            linearLayout.addView(imageButton);
            if (index == 0) {
                buttonChoice = imageButton;
                choice = tilesetEnum;
            }
            index++;
        }
        linearLayout.addView(createText(null));
        linearLayout.addView(createSprite(null));
        buttonChoice.setBackgroundColor(getResources().getColor(R.color.teal_700));
    }

    /**
     * Create the text
     *
     * @param tilesetEnum the sprite
     * @return a textView
     */
    TextView createText(TilesetEnum tilesetEnum) {
        TextView textView = new TextView(getApplicationContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.gravity = Gravity.CENTER;
        params.leftMargin = 50;
        params.rightMargin = 50;
        textView.setLayoutParams(params);
        if (tilesetEnum == null) {
            textView.setText(getResources().getText(R.string.Choice_random));
        } else {
            textView.setText(tilesetEnum.getName());
        }
        textView.setTextColor(getResources().getColor(R.color.lemonMeringue));
        return textView;
    }

    /**
     * Create the button sprite
     *
     * @param tilesetEnum the sprite
     * @return an ImageButton
     */
    Button createSprite(TilesetEnum tilesetEnum) {
        Button imageButton = new Button(getApplicationContext());
        // Create Style
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.leftMargin = 50;
        params.rightMargin = 50;
        imageButton.setLayoutParams(params);

        if (tilesetEnum != null) {
            imageButton.setText(tilesetEnum.getName());
        }

        Drawable bg = imageButton.getBackground();
        /*
         * Function of the button
         * @param View the button
         */
        imageButton.setOnClickListener((view) -> {
            buttonChoice.setBackground(bg);
            buttonChoice = imageButton;
            choice = tilesetEnum;
            buttonChoice.setBackgroundColor(getResources().getColor(R.color.teal_700));
        });
        return imageButton;
    }

    /**
     * Button of choice. Return the activity
     *
     * @param view the button
     */
    public void choiceButton(View view) {
        Intent intent = new Intent();
        if (choice == null) {
            TilesetEnum[] tilesetEnums = TilesetEnum.getAllTileset();
            choice = tilesetEnums[(new Random()).nextInt(tilesetEnums.length)];
        }
        intent.putExtra("tileset", Serializer.addToSerializer(choice));

        setResult(RESULT_OK, intent);
        finish();
    }
}