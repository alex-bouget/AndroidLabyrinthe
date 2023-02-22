package com.cppfdm.labyrinthe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.IOException;
import java.util.ArrayList;

public class LabyrintheChooserActivity extends AppCompatActivity {
    String filePath = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labyrinthe_chooser);

        AssetManager assetManager = getAssets();
        LinearLayout layout = (LinearLayout) findViewById(R.id.labyrintheList);
        try {
            int index = 0;
            for (String filename : assetManager.list("laby/")) {
                Button b = createButton(filename, "laby/" + filename);
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
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(filepath);
            }
        });
        return newButton;
    }
}