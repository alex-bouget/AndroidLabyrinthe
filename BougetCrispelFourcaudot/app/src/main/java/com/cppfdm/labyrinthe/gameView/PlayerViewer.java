package com.cppfdm.labyrinthe.gameView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.cppfdm.labyrinthe.game.Coord;
import com.cppfdm.labyrinthe.game.Player;
import com.cppfdm.labyrinthe.view.Viewer;
import com.cppfdm.labyrinthe.view.core.AbstractDrawable;
import com.cppfdm.labyrinthe.view.core.Drawable;

import java.io.IOException;

public class PlayerViewer extends SpritesViewer {
    Player player;
    Coord lastCoordinates;
    /**
     * Constructor
     */
    public PlayerViewer(Player player) {
        super("sprites/link/");
        this.player = player;
        lastCoordinates = player.getCurrentCase().getCoord();
    }

    /**
     * Paint the element
     *
     * @param canvas element for paint inside
     * @param paint  can be useful
     */
    @Override
    public void paint(Canvas canvas, Paint paint) {
        if (!lastCoordinates.equals(player.getCurrentCase().getCoord())) {
            Coord newCoordinates = player.getCurrentCase().getCoord();
            int x = (lastCoordinates.getX() - newCoordinates.getX());
            int y = (lastCoordinates.getY() - newCoordinates.getY());
            int calc = Math.abs((x-2)*x) + Math.abs((y-1)*y);
            sprites.changeMovement(calc);
            sprites.setAnimationOn(GameViewer.ANIMATION_DELAY);
            lastCoordinates = newCoordinates;
        }
        Bitmap sprite = sprites.handleSprite();
        canvas.drawBitmap(sprite, canvas.getWidth()/2, (canvas.getHeight()/2)-(sprite.getHeight()-scale), paint);
    }
}
