package com.cppfdm.labyrinthe.gameView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.cppfdm.labyrinthe.game.Case;
import com.cppfdm.labyrinthe.game.Coord;
import com.cppfdm.labyrinthe.game.Enemy;
import com.cppfdm.labyrinthe.game.Player;

public class EnemyViewer extends SpritesViewer {
    Enemy enemy;
    Player player;

    public EnemyViewer(Enemy enemy, Player player) {
        super("sprites/monster/", "sprites/monster/spriteLoader.txt");
        this.enemy = enemy;
        this.player = player;
    }

    /**
     * Paint the element
     *
     * @param canvas element for paint inside
     * @param paint  can be useful
     */
    @Override
    public void paint(Canvas canvas, Paint paint) {
        Coord playerCoordinates = player.getCurrentCase().getCoord();
        Bitmap sprite = sprites.handleSprite();
        canvas.drawBitmap(
                sprite,
                (enemy.getPos().getX() - playerCoordinates.getX()) * scale + canvas.getWidth() / 2,
                (enemy.getPos().getY() - playerCoordinates.getY()) * scale + (canvas.getHeight() / 2) - (sprite.getHeight() - scale),
                paint);
    }
}
