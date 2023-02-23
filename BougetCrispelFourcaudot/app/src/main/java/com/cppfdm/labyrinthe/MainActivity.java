package com.cppfdm.labyrinthe;

import androidx.annotation.Nullable;
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
        runLabyrintheChoose();
        return;
    }

    /**
     * Run choose Labyrinthe
     */
    public void runLabyrintheChoose() {
        Intent intent = new Intent();
        intent.setClass(this, LabyrintheChooserActivity.class);
        startActivityForResult(intent, LabyrintheChooserActivity.INTENT_ID);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == LabyrintheChooserActivity.INTENT_ID) {
            //TODO
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}