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

import com.cppfdm.labyrinthe.game.Coordinate;
import com.cppfdm.labyrinthe.game.Labyrinth;
import com.cppfdm.labyrinthe.utils.AssetsCommand;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
     *
     * @param savedInstanceState the last instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        // Load button from the file in the assetManager
        AssetManager assetManager = getAssets();
        LinearLayout layout = findViewById(R.id.scroller);

        try {
            int index = 0;
            String[] files = assetManager.list("laby/");
            Arrays.sort(files);
            for (String filename : sortList(files)) {
                Button b = createButton(filename.substring(0, filename.indexOf('.')), "laby/" + filename);
                layout.addView(b, index);
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sort list to good alphabetical order
     *
     * @param files list of string
     * @return new list of string
     */
    private String[] sortList(String[] files) {
        List<String> f = Arrays.asList(files);
        Collections.sort(f, (s, t1) -> {
            if (s.length() != t1.length()) {
                return s.length() - t1.length();
            }
            return s.compareTo(t1);
        });
        String[] data;
        try {
            data = (String[]) f.toArray();
            return data;
        } catch (ClassCastException e) {
            e.printStackTrace();
            return files;
        }
    }


    /**
     * Create the button for one choice
     *
     * @param name     Name in the button
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
     *
     * @param view the button
     */
    public void choiceButton(View view) {
        if (filePath == null) {
            Toast toast = Toast.makeText(getApplicationContext(), getResources().getText(R.string.LabyChooser_msg_not), Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        String data = AssetsCommand.readFile(view, filePath);
        // Decode in coordinates
        assert data != null;
        String[] eachLine = data.split("\n");
        ArrayList<Coordinate> coordinate = new ArrayList<>();
        for (String l : eachLine) {
            if (l.trim().equals("")) {
                continue;
            }
            String[] d = l.split(" ");
            coordinate.add(new Coordinate(Integer.parseInt(d[0]), Integer.parseInt(d[1])));
        }
        // Get size of the labyrinth
        int row = coordinate.get(0).getX();
        int col = coordinate.get(0).getY();
        coordinate.remove(0);

        // create labyrinth object
        Coordinate start = coordinate.get(0);
        Coordinate end = coordinate.get(1);
        Labyrinth map = new Labyrinth(row, col, start, end, coordinate);

        Intent intent = new Intent();
        intent.putExtra("labyrinth", Serializer.addToSerializer(map));
        intent.putExtra("name", setter.getText().toString());

        setResult(RESULT_OK, intent);

        finish();
    }

    public void back(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}