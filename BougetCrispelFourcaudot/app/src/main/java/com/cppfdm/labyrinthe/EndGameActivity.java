package com.cppfdm.labyrinthe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EndGameActivity extends AppCompatActivity {
    public static final int INTENT_ID = 507;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
        String result = getIntent().getStringExtra("result");
        TextView text = (TextView) findViewById(R.id.textEndGame);
        if (result.equals("win")) {
            text.setText(getResources().getText(R.string.EndGame_Win));
        }
        if (result.equals("died")) {
            text.setText(getResources().getText(R.string.EndGame_Died));
        }
    }

    public void onMainMenu(View v) {
        Intent intent = new Intent();
        intent.putExtra("result", "return");
        setResult(RESULT_OK, intent);
        finish();
    }

    public void onContinue(View v) {
        Intent intent = new Intent();
        intent.putExtra("result", "continue");
        setResult(RESULT_OK, intent);
        finish();
    }
}