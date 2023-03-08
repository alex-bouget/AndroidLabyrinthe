package com.cppfdm.labyrinthe.gameView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.cppfdm.labyrinthe.view.Viewer;
import com.cppfdm.labyrinthe.view.core.AbstractDrawable;
import com.cppfdm.labyrinthe.view.core.Drawable;

import java.io.IOException;

public class PlayerViewer extends AbstractDrawable {
    private int scale = -1;
    private Viewer root;
    private Sprites sprites;


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
            sprites = new Sprites(root, "sprites/", "sprites/spriteLoader.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Paint the element
     *
     * @param canvas element for paint inside
     * @param paint  can be useful
     */
    @Override
    public void paint(Canvas canvas, Paint paint) {
        Bitmap sprite = sprites.handleSprite();
        canvas.drawBitmap(sprite, canvas.getWidth()/2, (canvas.getHeight()/2)-(sprite.getHeight()-scale), paint);
    }
}
