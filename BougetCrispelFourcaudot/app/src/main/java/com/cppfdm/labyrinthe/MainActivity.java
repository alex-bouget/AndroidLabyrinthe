package com.cppfdm.labyrinthe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent();
        intent.setClass(this, LabyrintheChooserActivity.class);
        startActivity(intent);
        return;
        /*
        AssetManager assetManager = getAssets();
        BufferedReader bufferedReader = null;
        String data = "";
        try {
            InputStream level2 = getAssets().open("laby/level2.txt");
            bufferedReader = new BufferedReader(new InputStreamReader(level2));
            StringBuilder f = new StringBuilder();
            String tmp = "";
            while ((tmp = bufferedReader.readLine()) != null) {
                f.append("\n").append(tmp);
            }
            data = f.toString();
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println(data);*/
    }
}