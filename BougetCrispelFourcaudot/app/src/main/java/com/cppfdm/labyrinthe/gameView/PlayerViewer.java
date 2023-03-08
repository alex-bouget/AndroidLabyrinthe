package com.cppfdm.labyrinthe.gameView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.cppfdm.labyrinthe.game.Case;
import com.cppfdm.labyrinthe.game.Player;
import com.cppfdm.labyrinthe.view.core.AbstractDrawable;
import com.cppfdm.labyrinthe.view.core.Drawable;

import java.util.ArrayList;

public class PlayerViewer extends AbstractDrawable {
    private Player player;
    private Case lastPos;
    private ArrayList<Bitmap>[] sprites;


    public PlayerViewer(Player player) {
        this.player = player;
        lastPos = player.getCurrentCase();
        sprites = new ArrayList[4];
        // UP RIGHT DOWN LEFT
    }

    @Override
    public void setDrawableParent(Drawable drawable) {
        super.setDrawableParent(drawable);
        sprites[2] = new ArrayList<>();
        sprites[2].add()
    }

    @Override
    public void paint(Canvas canvas, Paint paint) {


    }
}
