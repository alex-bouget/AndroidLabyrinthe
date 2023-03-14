package com.cppfdm.labyrinthe.gameView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.cppfdm.labyrinthe.utils.SpriteEnum;
import com.cppfdm.labyrinthe.game.Coord;
import com.cppfdm.labyrinthe.game.Enemy;
import com.cppfdm.labyrinthe.view.core.Drawable;

public class EnemyViewer extends SpritesViewer {
    Enemy enemy;
    GameViewer gameViewer;
    private Coord lastCoordinates;
    int animationFrame;
    private int xOffset = 0;
    private int yOffset = 0;

    /**
     * Constructor
     *
     * @param enemy the enemy to view
     * @param sprite sprite of the enemy
     */
    public EnemyViewer(Enemy enemy, SpriteEnum sprite) {
        super(sprite.getPath(), sprite.getLoaderPath());
        this.enemy = enemy;
        lastCoordinates = enemy.getPos();
    }

    /**
     * Set the parent and get the root for catch the bitmap
     *
     * @param drawable parent
     */
    @Override
    public void setDrawableParent(Drawable drawable) {
        super.setDrawableParent(drawable);
        gameViewer = (GameViewer) getDrawableParent();
    }

    /**
     * Paint the element
     *
     * @param canvas element for paint inside
     * @param paint  can be useful
     */
    @Override
    public void paint(Canvas canvas, Paint paint) {
        if (!lastCoordinates.equals(enemy.getPos())) {
            Coord newCoordinates = enemy.getPos();
            int x = (lastCoordinates.getX() - newCoordinates.getX());
            int y = (lastCoordinates.getY() - newCoordinates.getY());
            xOffset = x * scale;
            yOffset = y * scale;
            int calc = Math.abs((x-2)*x) + Math.abs((y-1)*y);
            sprites.changeMovement(calc);
            sprites.setAnimationOn(GameViewer.ANIMATION_DELAY);
            lastCoordinates = newCoordinates;
        }
        Coord coordinatesBefore = gameViewer.calcPosition(enemy.getPos());
        Coord coordinates = new Coord(
                coordinatesBefore.getX() + xOffset - (xOffset / GameViewer.ANIMATION_DELAY) * animationFrame,
                coordinatesBefore.getY() + yOffset - (yOffset / GameViewer.ANIMATION_DELAY) * animationFrame
        );
        Bitmap sprite = sprites.handleSprite();
        int height= coordinates.getY() - (sprite.getHeight() - scale);
        if (sprite.getHeight() == scale) {
            height -= scale/4;
        }
        canvas.drawBitmap(
                sprite,
                coordinates.getX(),
                height,
                paint
        );
        if (xOffset != 0 || yOffset != 0) {
            animationFrame++;
            if (animationFrame >= GameViewer.ANIMATION_DELAY) {
                xOffset = 0;
                yOffset = 0;
                animationFrame = 1;
            }
        }
    }
}
