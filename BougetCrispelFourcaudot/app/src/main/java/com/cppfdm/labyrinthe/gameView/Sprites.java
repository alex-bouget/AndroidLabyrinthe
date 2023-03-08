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
    int movement = 2;
    float animationPath = 0;
    int frame = 0;

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

    public void changeMovement(int movement) {
        if (movement > 3 || movement < 0) {
            return;
        }
        this.movement = movement;
    }

    public int getSize() {
        return this.resizedSprites[movement].size();
    }

    public void setAnimationOn(int frame) {
        if (frame <= 0) {
            return;
        }
        animationPath = ((float)frame) / getSize();
        this.frame = 0;
    }

    /**
     * Return the sprite
     * @return bitmap
     */
    public Bitmap handleSprite() {
        frame++;
        int frameToGet = (int)(frame * animationPath);
        if (frameToGet > getSize()) {
            frameToGet = 0;
        }
        return resizedSprites[movement].get(frameToGet%getSize());
    }
}
