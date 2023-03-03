package com.cppfdm.labyrinthe;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cppfdm.labyrinthe.game.Coord;
import com.cppfdm.labyrinthe.game.Labyrinth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LabyrintheChooserActivity extends AppCompatActivity {
    /**
     * Id of the intention
     */
    static final int INTENT_ID = 24;

    /**
     * Filepath of the current
     */
    String filePath = null;

    /**
     * Button of the current
     */
    Button setter = null;

    /**
     * Create the Activity
     * @param savedInstanceState the last instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labyrinthe_chooser);

        // Load button from the file in the assetManager
        AssetManager assetManager = getAssets();
        LinearLayout layout = findViewById(R.id.labyrintheList);
        try {
            int index = 0;
            for (String filename : assetManager.list("laby/")) {
                Button b = createButton(filename.substring(0, filename.indexOf('.')), "laby/" + filename);
                layout.addView(b, index);
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Create the button for one choice
     * @param name Name in the button
     * @param filepath File of the choice
     * @return the button
     */
    public Button createButton(String name, String filepath) {
        Button newButton = new Button(getApplicationContext());
        // Create Style
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.leftMargin = 50;
        params.rightMargin = 50;
        newButton.setLayoutParams(params);

        newButton.setText(name);

        Drawable bg = newButton.getBackground();
        /*
         * Function of the button
         * @param View the button
         */
        newButton.setOnClickListener((view) -> {
            filePath = filepath;
            if (setter != null) {
                setter.setBackground(bg);
            }
            if (view.equals(setter)) {
                view.setBackground(bg);
                setter = null;
                filePath = null;
            } else {
                view.setBackgroundColor(getResources().getColor(R.color.teal_700));
                setter = (Button) view;
            }
        });
        return newButton;
    }

    /**
     * Button of choice. Return the activity
     * @param view the button
     */
    public void labyrintheChooserChoose(View view) {
        if (filePath == null) {
            Toast toast = Toast.makeText(getApplicationContext(), getResources().getText(R.string.LabyChooser_msg_not), Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        String data;
        // Read the file
        try {
            InputStream level2 = getAssets().open(filePath);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(level2));
            StringBuilder f = new StringBuilder();

            String tmp;
            while ((tmp = buffer.readLine()) != null) {
                f.append("\n").append(tmp);
            }
            data = f.toString();
        } catch (IOException e) {
            // View message error
            Toast toast = Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        // Decode in coordonats
        String[] eachLine = data.split("\n");
        ArrayList<Coord> coordinate = new ArrayList<>();
        for (String l: eachLine) {
            if (l.trim().equals("")) {
                continue;
            }
            String[] d = l.split(" ");
            coordinate.add(new Coord(Integer.parseInt(d[0]), Integer.parseInt(d[1])));
        }
        // Get size of the labyrinth
        int row = coordinate.get(0).getX();
        int col = coordinate.get(0).getY();
        coordinate.remove(0);

        // create labyrinth object
        Coord start = coordinate.get(0);
        Coord end = coordinate.get(1);
        Labyrinth map = new Labyrinth(row, col, start, end, coordinate);

        Intent intent = new Intent();
        intent.putExtra("labyrinth", Serializer.addToSerializer(map));

        setResult(RESULT_OK, intent);

        finish();
    }
}