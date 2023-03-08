package com.cppfdm.labyrinthe.gameView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.cppfdm.labyrinthe.game.Enemy;
import com.cppfdm.labyrinthe.game.Player;

public class EnemyViewer extends SpritesViewer {
    Enemy enemy;
    Player player;

    public EnemyViewer(Enemy enemy) {
        super("sprites/monster/", "sprites/monster/spriteLoader.txt");
        this.enemy = enemy;
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
