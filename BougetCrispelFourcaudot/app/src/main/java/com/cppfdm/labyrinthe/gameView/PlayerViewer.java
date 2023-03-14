package com.cppfdm.labyrinthe.gameView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.cppfdm.labyrinthe.utils.SpriteEnum;
import com.cppfdm.labyrinthe.game.Coord;
import com.cppfdm.labyrinthe.game.Player;

public class PlayerViewer extends SpritesViewer {
    Player player;
    Coord lastCoordinates;
    /**
     * Constructor
     * @param player player
     * @param sprite sprite of the player
     */
    public PlayerViewer(Player player, SpriteEnum sprite) {
        super(sprite.getPath(), sprite.getLoaderPath());
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
        int height = (canvas.getHeight()/2) - (sprite.getHeight() - scale);
        if (sprite.getHeight() == scale) {
            height -= scale/4;
        }
        canvas.drawBitmap(sprite, canvas.getWidth()/2, height, paint);
    }
}
