package com.cppfdm.labyrinthe.gameView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.cppfdm.labyrinthe.game.Case;
import com.cppfdm.labyrinthe.game.Coord;
import com.cppfdm.labyrinthe.game.Enemy;
import com.cppfdm.labyrinthe.game.Player;
import com.cppfdm.labyrinthe.view.core.Drawable;

public class EnemyViewer extends SpritesViewer {
    Enemy enemy;
    GameViewer gameViewer;

    /**
     * Constructor
     *
     * @param enemy the enemy to view
     */
    public EnemyViewer(Enemy enemy) {
        super("sprites/monster/", "sprites/monster/spriteLoader.txt");
        this.enemy = enemy;
    }

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
        Bitmap sprite = sprites.handleSprite();
        Coord coordinates = gameViewer.calcPosition(enemy.getPos());
        canvas.drawBitmap(
                sprite,
                coordinates.getX(),
                coordinates.getY() - (sprite.getHeight() - scale),
                paint
        );
    }
}
