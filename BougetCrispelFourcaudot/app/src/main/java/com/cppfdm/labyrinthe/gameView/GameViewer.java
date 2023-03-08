package com.cppfdm.labyrinthe.gameView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.cppfdm.labyrinthe.game.Case;
import com.cppfdm.labyrinthe.game.Coord;
import com.cppfdm.labyrinthe.game.Enemy;
import com.cppfdm.labyrinthe.game.Labyrinth;
import com.cppfdm.labyrinthe.game.Player;
import com.cppfdm.labyrinthe.gameView.tileset.DefaultTileset;
import com.cppfdm.labyrinthe.view.Viewer;
import com.cppfdm.labyrinthe.view.core.AbstractDrawable;
import com.cppfdm.labyrinthe.view.core.Drawable;
import com.cppfdm.labyrinthe.view.tileset.TilesetResizer;

public class GameViewer extends AbstractDrawable {
    private static final int ENEMY_DELAY = 10;
    public static final int ANIMATION_DELAY = 3;

    private int scale = 64;
    Viewer root;
    Player player;
    EnemyViewer[] enemyViewers;
    PlayerViewer playerViewer;
    TilesetResizer tileset;
    private Coord lastCoordinates;
    private int xOffset = 0;
    private int yOffset = 0;
    private int animationFrame = 1;
    int enemyCalc = 0;


    /**
     * Constructor
     *
     * @param player the player
     */
    public GameViewer(Player player) {
        this.player = player;
        lastCoordinates = player.getCurrentCase().getCoord();
    }

    /**
     * get the offset of the animation
     *
     * @return coordinates with offset
     */
    public Coord getOffset() {
        return new Coord(xOffset, yOffset);
    }

    /**
     * Resized the tileset
     *
     * @param scale scale
     */
    public void resize(int scale) {
        this.scale = scale;
        tileset.resized(scale);
        playerViewer.resize(scale);
        for (EnemyViewer enemyViewer : enemyViewers) {
            enemyViewer.resize(scale);
        }
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
        tileset = new TilesetResizer(new DefaultTileset(root));
        playerViewer = new PlayerViewer(player);
        playerViewer.setDrawableParent(this);
        Enemy[] enemies = player.getLaby().getEnemies();
        enemyViewers = new EnemyViewer[enemies.length];
        for (int i = 0; i < enemies.length; i++) {
            enemyViewers[i] = new EnemyViewer(enemies[i]);
            enemyViewers[i].setDrawableParent(this);
        }
        resize(scale);
    }

    /**
     * Calculate the positions
     *
     * @param position position
     * @return Coordinates of the position
     */
    public Coord calcPosition(Coord position) {
        int width = root.getWidth();
        int height = root.getHeight();
        Coord playerCase = player.getCurrentCase().getCoord();
        int x = (position.getX() - playerCase.getX()) * scale + (width / 2) - xOffset + (xOffset / ANIMATION_DELAY) * animationFrame;
        int y = (position.getY() - playerCase.getY()) * scale + (height / 2) - yOffset + (yOffset / ANIMATION_DELAY) * animationFrame;
        return new Coord(x, y);
    }

    /**
     * Paint the element
     *
     * @param canvas element for paint inside
     * @param paint  can be useful
     */
    @Override
    public void paint(Canvas canvas, Paint paint) {
        if (enemyCalc >= ENEMY_DELAY) {
            player.getLaby().moveEnemies();
            enemyCalc = 0;
        }
        enemyCalc++;
        if (!lastCoordinates.equals(player.getCurrentCase().getCoord())) {
            Coord newCoordinates = player.getCurrentCase().getCoord();
            xOffset = (lastCoordinates.getX() - newCoordinates.getX()) * scale;
            yOffset = (lastCoordinates.getY() - newCoordinates.getY()) * scale;
            System.out.println(xOffset);
            System.out.println(yOffset);
            lastCoordinates = newCoordinates;
        }

        Labyrinth labyrinth = player.getLaby();
        for (int xSize = 0; xSize < labyrinth.getCOL(); xSize++) {
            for (int ySize = 0; ySize < labyrinth.getROW(); ySize++) {
                Case aCase = labyrinth.getCase(new Coord(xSize, ySize));
                Bitmap bitmap = tileset.getTiles(aCase);
                Coord bitPos = calcPosition(new Coord(xSize, ySize));
                canvas.drawBitmap(
                        bitmap,
                        bitPos.getX(),
                        bitPos.getY(),
                        paint
                );
            }
        }
        Coord exitPos = calcPosition(labyrinth.getEndCoord());
        canvas.drawBitmap(
                tileset.getExitTiles(),
                exitPos.getX(),
                exitPos.getY(),
                paint
        );
        Coord beginPos = calcPosition(labyrinth.getStartCoord());
        canvas.drawBitmap(
                tileset.getStartTiles(),
                beginPos.getX(),
                beginPos.getY(),
                paint
        );
        playerViewer.paint(canvas, paint);
        for (EnemyViewer enemyViewer : enemyViewers) {
            enemyViewer.paint(canvas, paint);
        }
        if (xOffset != 0 || yOffset != 0) {
            animationFrame++;
            if (animationFrame >= ANIMATION_DELAY) {
                xOffset = 0;
                yOffset = 0;
                animationFrame = 1;
            }
        }
    }
}
