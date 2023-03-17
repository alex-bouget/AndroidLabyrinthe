package com.cppfdm.labyrinthe.gameView;

import com.cppfdm.labyrinthe.view.Viewer;
import com.cppfdm.labyrinthe.view.core.AbstractDrawable;
import com.cppfdm.labyrinthe.view.core.Drawable;

import java.io.IOException;

public abstract class SpritesViewer extends AbstractDrawable {
    protected int scale = -1;
    protected Viewer root;
    protected Sprites sprites;
    protected String path;
    protected String loaderPath;

    /**
     * Constructor
     *
     * @param path       path where the sprites are
     * @param loaderPath path to the loader
     */
    public SpritesViewer(String path, String loaderPath) {
        this.path = path;
        this.loaderPath = loaderPath;
    }

    public SpritesViewer(String path) {
        this.path = path;
        this.loaderPath = path + "spriteLoader.txt";
    }


    /**
     * resize the player
     *
     * @param scale scale
     */
    public void resize(int scale) {
        this.scale = scale;
        sprites.resized(scale);
    }

    /**
     * Set the parent and get the root for catch the bitmap
     *
     * @param drawable parent
     */
    @Override
    public void setDrawableParent(Drawable drawable) {
        super.setDrawableParent(drawable);
        root = (Viewer) getDrawableRoot();
        try {
            sprites = new Sprites(root, path, loaderPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
