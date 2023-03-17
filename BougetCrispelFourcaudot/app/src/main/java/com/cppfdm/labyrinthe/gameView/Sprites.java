package com.cppfdm.labyrinthe.gameView;

import android.graphics.Bitmap;
import android.view.View;

import com.cppfdm.labyrinthe.utils.AssetsCommand;
import com.cppfdm.labyrinthe.utils.ViewerCommand;

import java.io.IOException;
import java.util.ArrayList;

public class Sprites {
    private final View v;

    private ArrayList<Bitmap>[] sprites;
    private ArrayList<Bitmap>[] resizedSprites;
    int movement = 2;
    float animationPath = 0;
    int frame = 0;

    /**
     * Constructor
     *
     * @param v          the view
     * @param path       path to the sprites folder
     * @param loaderPath sprite loader
     * @throws IOException
     */
    public Sprites(View v, String path, String loaderPath) throws IOException {
        this.v = v;
        readLoader(path, loaderPath);
    }

    public void readLoader(String path, String loaderPath) throws IOException {
        String loader = AssetsCommand.readFile(v, loaderPath);
        if (loader == null) {
            throw new IOException();
        }
        String[] each = loader.split("\n");
        if (each[0].equals("!")) {
            int size = each.length - 1;
            int choice = (int) (Math.random() * size) + 1;
            System.out.println(choice);
            String[] newLoader = each[choice].split("!");
            System.out.println(newLoader[0]);
            readLoader(newLoader[0], newLoader[1]);
            return;
        }

        sprites = new ArrayList[each.length];
        resizedSprites = new ArrayList[each.length];
        int i = 0;
        for (String line : each) {
            sprites[i] = new ArrayList<>();
            resizedSprites[i] = new ArrayList<>();
            String[] gifs = line.split(">");
            for (String gif : gifs) {
                sprites[i].add(ViewerCommand.getBitmap(v, path + gif));
            }
            i++;
        }
    }

    /**
     * Resized the sprites
     *
     * @param scale scale
     */
    public void resized(int scale) {
        for (int i = 0; i < resizedSprites.length; i++) {
            resizedSprites[i].clear();
            for (Bitmap m : sprites[i]) {
                resizedSprites[i].add(ViewerCommand.resizeBitmapWidth(m, scale));
            }
        }
    }

    /**
     * Change the list for the movement
     *
     * @param movement id of the list
     */
    public void changeMovement(int movement) {
        if (movement > 3 || movement < 0) {
            return;
        }
        this.movement = movement;
    }

    /**
     * Get the size of the current list of sprites
     *
     * @return the size
     */
    public int getSize() {
        return this.resizedSprites[movement].size();
    }

    /**
     * Set number of frame for make the animation
     *
     * @param frame number of frame
     */
    public void setAnimationOn(int frame) {
        if (frame <= 0) {
            return;
        }
        animationPath = getSize() / (float) frame;
        this.frame = 0;
    }

    /**
     * Return the sprite
     *
     * @return bitmap
     */
    public Bitmap handleSprite() {
        frame++;
        int frameToGet = (int) (frame * animationPath);
        if (frameToGet > getSize()) {
            frameToGet = 0;
        }
        return resizedSprites[movement].get(frameToGet % getSize());
    }
}
