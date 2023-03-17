package com.cppfdm.labyrinthe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cppfdm.labyrinthe.utils.SpriteEnum;
import com.cppfdm.labyrinthe.utils.ViewerCommand;

import java.util.Random;

public class SpriteChoiceActivity extends AppCompatActivity {
    public static int INTENT_ID = 64;

    LinearLayout linearLayout;
    int imageSize = 64;
    SpriteEnum choice;
    ImageButton buttonChoice;

    /**
     * Create the Activity
     *
     * @param savedInstanceState the last instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sprite_choice);
        linearLayout = (LinearLayout) findViewById(R.id.scrollSprite);
        int index = 0;
        for (SpriteEnum spriteEnum : SpriteEnum.getAllSprite()) {
            ImageButton imageButton = createSprite(spriteEnum);
            linearLayout.addView(createText(spriteEnum));
            linearLayout.addView(imageButton);
            if (index == 0) {
                buttonChoice = imageButton;
                choice = spriteEnum;
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
     * @param spriteEnum the sprite
     * @return a textView
     */
    TextView createText(SpriteEnum spriteEnum) {
        TextView textView = new TextView(getApplicationContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.gravity = Gravity.CENTER;
        params.leftMargin = 50;
        params.rightMargin = 50;
        textView.setLayoutParams(params);
        if (spriteEnum == null) {
            textView.setText("Aléatoire");
        } else {
            textView.setText(spriteEnum.getName());
        }

        // Change text color aaccording to the theme
        switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
            case Configuration.UI_MODE_NIGHT_YES:
                textView.setTextColor(getResources().getColor(R.color.naplesYellow));
                break;
            case Configuration.UI_MODE_NIGHT_NO:
                textView.setTextColor(getResources().getColor(R.color.Dark_slate_blue));
                break;
        }
        return textView;
    }

    /**
     * Create the button sprite
     *
     * @param spriteEnum the sprite
     * @return an ImageButton
     */
    ImageButton createSprite(SpriteEnum spriteEnum) {
        ImageButton imageButton = new ImageButton(getApplicationContext());
        // Create Style
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.leftMargin = 50;
        params.rightMargin = 50;
        imageButton.setLayoutParams(params);

        if (spriteEnum != null) {
            imageButton.setImageBitmap(ViewerCommand.resizeBitmap(spriteEnum.getIcon(linearLayout), imageSize, imageSize));
        }

        Drawable bg = imageButton.getBackground();
        /**
         * Function of the button
         * @param View the button
         */
        imageButton.setOnClickListener((view) -> {
            buttonChoice.setBackground(bg);
            buttonChoice = imageButton;
            choice = spriteEnum;
            buttonChoice.setBackgroundColor(getResources().getColor(R.color.teal_700));
        });
        return imageButton;
    }

    /**
     * Button of choice. Return the activity
     *
     * @param view the button
     */
    public void SpriteChoiceButton(View view) {
        Intent intent = new Intent();
        if (choice == null) {
            SpriteEnum[] spriteEnums = SpriteEnum.getAllSprite();
            choice = spriteEnums[(new Random()).nextInt(spriteEnums.length)];
        }
        intent.putExtra("sprite", Serializer.addToSerializer(choice));

        setResult(RESULT_OK, intent);
        finish();
    }
}