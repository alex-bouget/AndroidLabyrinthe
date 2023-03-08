package com.cppfdm.labyrinthe.utils;

import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AssetsCommand {
    /**
     * Read a file in assets
     * @param v the view
     * @param path path
     * @return String file
     */
    public static String readFile(View v, String path) {
        String data;
        // Read the file
        try {
            InputStream level2 = v.getResources().getAssets().open(path);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(level2));
            StringBuilder f = new StringBuilder();

            String tmp;
            int i = 0;
            while ((tmp = buffer.readLine()) != null) {
                if (i != 0)
                    f.append("\n");
                f.append(tmp);
                i++;
            }
            data = f.toString();
        } catch (IOException e) {
            // View message error
            Toast toast = Toast.makeText(v.getContext().getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
            return null;
        }
        return data;
    }
}
