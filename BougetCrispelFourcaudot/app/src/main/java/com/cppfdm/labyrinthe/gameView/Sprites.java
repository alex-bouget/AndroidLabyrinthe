package com.cppfdm.labyrinthe.gameView;

import android.graphics.Bitmap;
import android.view.View;

import com.cppfdm.labyrinthe.utils.AssetsCommand;
import com.cppfdm.labyrinthe.utils.ViewerCommand;

import java.io.IOException;
import java.util.ArrayList;

public class Sprites {
    private View v;
    private ArrayList<Bitmap>[] sprites;
    private ArrayList<Bitmap>[] resizedSprites;

    /**
     * Constructor
     *
     * @param v the view
     * @param path path to the sprites folder
     * @param loaderPath sprite loader
     * @throws IOException
     */
    public Sprites(View v, String path, String loaderPath) throws IOException {
        this.v = v;
        String loader = AssetsCommand.readFile(v, loaderPath);
        System.out.println(loader);
        if (loader == null) {
            throw new IOException();
        }
        String[] each = loader.split("\n");
        sprites = new ArrayList[each.length];
        resizedSprites = new ArrayList[each.length];
        int i = 0;
        for (String line: each) {
            sprites[i] = new ArrayList<>();
            resizedSprites[i] = new ArrayList<>();
            String[] gifs = line.split(">");
            for (String gif: gifs) {
                sprites[i].add(ViewerCommand.getBitmap(v, path + gif));
            }
            System.out.println(sprites[i]);
            i++;
        }
    }

    /**
     * Resized the sprites
     * @param scale scale
     */
    public void resized(int scale) {
        for (int i=0; i < resizedSprites.length; i++) {
            resizedSprites[i].clear();
            for (Bitmap m: sprites[i]) {
                resizedSprites[i].add(ViewerCommand.resizeBitmapWidth(m, scale));
            }
        }
    }

    /**
     * Return the sprite
     * @return bitmap
     */
    public Bitmap handleSprite() {
        return resizedSprites[2].get(0);
    }
}
