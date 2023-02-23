package com.cppfdm.labyrinthe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LabyrintheChooserActivity extends AppCompatActivity {
    String filePath = null;
    Button setter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labyrinthe_chooser);

        AssetManager assetManager = getAssets();
        LinearLayout layout = (LinearLayout) findViewById(R.id.labyrintheList);
        try {
            int index = 0;
            for (String filename : assetManager.list("laby/")) {
                Button b = createButton(filename.substring(0, filename.indexOf('.')), "laby/" + filename);
                System.out.println(b.getText().toString());
                layout.addView(b, index);
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Button createButton(String name, String filepath) {
        Button newButton = new Button(getApplicationContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.leftMargin = 50;
        params.rightMargin = 50;
        newButton.setLayoutParams(params);
        newButton.setText(name);
        Drawable bg = newButton.getBackground();
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filePath = filepath;
                if (setter != null) {
                    setter.setBackground(bg);
                }
                if (view.equals(setter)) {
                    view.setBackground(bg);

                    setter = null;
                    filePath = null;
                    return;
                }
                view.setBackgroundColor(getResources().getColor(R.color.teal_700));
                setter = (Button) view;
            }
        });
        return newButton;
    }

    public void labyrintheChooserchoose(View view) {
        if (filePath == null) {
            return;
        }
        Object map = null;
        String data = null;
        try {
            InputStream level2 = getAssets().open(filePath);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(level2));
            StringBuilder f = new StringBuilder();

            String tmp = "";
            while ((tmp = buffer.readLine()) != null) {
                f.append("\n").append(tmp);
            }
            data = f.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //TODO
        finish();
    }
}