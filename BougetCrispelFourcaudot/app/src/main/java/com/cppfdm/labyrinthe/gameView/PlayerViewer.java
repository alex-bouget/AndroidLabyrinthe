package com.cppfdm.labyrinthe.gameView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.cppfdm.labyrinthe.game.Case;
import com.cppfdm.labyrinthe.game.Player;
import com.cppfdm.labyrinthe.view.Viewer;
import com.cppfdm.labyrinthe.utils.ViewerCommand;
import com.cppfdm.labyrinthe.view.core.AbstractDrawable;
import com.cppfdm.labyrinthe.view.core.Drawable;

import java.io.IOException;
import java.util.ArrayList;

public class PlayerViewer extends AbstractDrawable {
    private int scale = -1;
    private Viewer root;
    private Player player;
    private Sprites sprites;


    public PlayerViewer(Player player) {
        this.player = player;
    }

    public void resize(int scale) {
        this.scale = scale;
        sprites.resized(scale);
    }

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

    @Override
    public void paint(Canvas canvas, Paint paint) {
        Bitmap sprite = sprites.handleSprite();
        canvas.drawBitmap(sprite, canvas.getWidth()/2, (canvas.getHeight()/2)-(sprite.getHeight()-scale), paint);
    }
}
