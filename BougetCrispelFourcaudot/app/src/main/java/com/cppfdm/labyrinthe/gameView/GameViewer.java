package com.cppfdm.labyrinthe.gameView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.appcompat.app.AppCompatActivity;

import com.cppfdm.labyrinthe.EndGameActivity;
import com.cppfdm.labyrinthe.utils.SpriteEnum;
import com.cppfdm.labyrinthe.game.Case;
import com.cppfdm.labyrinthe.game.Coordinate;
import com.cppfdm.labyrinthe.game.Enemy;
import com.cppfdm.labyrinthe.game.Labyrinth;
import com.cppfdm.labyrinthe.game.Player;
import com.cppfdm.labyrinthe.utils.ViewerCommand;
import com.cppfdm.labyrinthe.view.Viewer;
import com.cppfdm.labyrinthe.view.core.AbstractDrawable;
import com.cppfdm.labyrinthe.view.core.Drawable;
import com.cppfdm.labyrinthe.view.tileset.TilesInterfaces;
import com.cppfdm.labyrinthe.view.tileset.TilesetResizer;

public class GameViewer extends AbstractDrawable {
    private static final int ENEMY_DELAY = 10;
    private static final int NUMBER_VIEW = 8;
    public static final int ANIMATION_DELAY = 3;

    private int frame = 0;
    private int scale = 96;
    Viewer root;
    Player player;
    EnemyViewer[] enemyViewers;
    PlayerViewer playerViewer;
    TilesetResizer tileset;
    private Coordinate lastCoordinates;
    private Coordinate playerCoordinates;
    private int xOffset = 0;
    private int yOffset = 0;
    private int animationFrame = 1;
    private int animationWinDead = 0;
    private final TilesInterfaces tilesInterfaces;
    int enemyCalc = 0;
    Bitmap win;
    Bitmap died;

    SpriteEnum playerSprite;
    SpriteEnum monsterSprite;


    /**
     * Constructor
     *
     * @param player          the player
     * @param playerSprite    sprite of the player
     * @param monsterSprite   sprite of the monster
     * @param tilesInterfaces tileset
     */
    public GameViewer(Player player, SpriteEnum playerSprite, SpriteEnum monsterSprite, TilesInterfaces tilesInterfaces) {
        this.player = player;
        lastCoordinates = player.getCurrentCase().getCoordinate();
        this.playerSprite = playerSprite;
        this.monsterSprite = monsterSprite;
        this.tilesInterfaces = tilesInterfaces;
    }

    /**
     * get the scale
     *
     * @return scale
     */
    public int getScale() {
        return scale;
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
        tileset = new TilesetResizer(tilesInterfaces);
        playerViewer = new PlayerViewer(player, playerSprite);
        playerViewer.setDrawableParent(this);
        Enemy[] enemies = player.getLaby().getEnemies();
        enemyViewers = new EnemyViewer[enemies.length];
        for (int i = 0; i < enemies.length; i++) {
            enemyViewers[i] = new EnemyViewer(enemies[i], monsterSprite);
            enemyViewers[i].setDrawableParent(this);
        }
        win = ViewerCommand.resizeBitmap(ViewerCommand.getBitmap(root, "img/win.png"), root.getWidth(), root.getHeight());
        died = ViewerCommand.resizeBitmap(ViewerCommand.getBitmap(root, "img/died.png"), root.getWidth(), root.getHeight());
        int newScale = Math.max(root.getHeight(), root.getWidth()) / ((2 * NUMBER_VIEW) - 2);
        resize(newScale);
    }

    /**
     * Calculate the positions
     *
     * @param position position
     * @return Coordinates of the position
     */
    public Coordinate calcPosition(Coordinate position) {
        int width = root.getWidth();
        int height = root.getHeight();
        Coordinate playerCase = playerCoordinates;
        int x = (position.getX() - playerCase.getX()) * scale + (width / 2) - xOffset + (xOffset / ANIMATION_DELAY) * animationFrame;
        int y = (position.getY() - playerCase.getY()) * scale + (height / 2) - yOffset + (yOffset / ANIMATION_DELAY) * animationFrame;
        return new Coordinate(x, y);
    }

    /**
     * Paint the element
     *
     * @param canvas element for paint inside
     * @param paint  can be useful
     */
    @Override
    public void paint(Canvas canvas, Paint paint) {
        Bitmap winOrDead = null;
        if (player.isWin()) {
            winOrDead = win;
        }
        if (player.isDead()) {
            winOrDead = died;
        }
        if (winOrDead != null) {
            if (animationWinDead < 10) {
                canvas.drawBitmap(winOrDead, 0, 0, paint);
                animationWinDead++;
                return;
            }
            Intent intent = new Intent();
            AppCompatActivity app = (AppCompatActivity) root.getContext();
            intent.setClass(app, EndGameActivity.class);
            intent.putExtra("result", (win.equals(winOrDead)) ? "win" : "died");
            app.startActivityForResult(intent, EndGameActivity.INTENT_ID);
            animationWinDead = 0;
            root.pause();
            return;
        }
        tileset.setFrame(frame);
        if (enemyCalc >= ENEMY_DELAY) {
            player.getLaby().moveEnemies();
            enemyCalc = 0;
        }
        playerCoordinates = player.getCurrentCase().getCoordinate();
        enemyCalc++;
        if (!lastCoordinates.equals(playerCoordinates)) {
            xOffset = (lastCoordinates.getX() - playerCoordinates.getX()) * scale;
            yOffset = (lastCoordinates.getY() - playerCoordinates.getY()) * scale;
            lastCoordinates = playerCoordinates;
        }

        Labyrinth labyrinth = player.getLaby();
        Coordinate startView = new Coordinate(
                playerCoordinates.getX() - NUMBER_VIEW,
                playerCoordinates.getY() - NUMBER_VIEW
        );
        Coordinate endView = new Coordinate(
                playerCoordinates.getX() + NUMBER_VIEW,
                playerCoordinates.getY() + NUMBER_VIEW
        );
        Bitmap background = tileset.getBackgroundTiles();
        for (int xSize = startView.getX(); xSize < endView.getX(); xSize++) {
            for (int ySize = startView.getY(); ySize < endView.getY(); ySize++) {
                Case aCase = labyrinth.getCase(new Coordinate(xSize, ySize));
                Bitmap bitmap = tileset.getTiles(aCase);
                Coordinate bitPos = calcPosition(new Coordinate(xSize, ySize));
                if (background != null) {
                    canvas.drawBitmap(
                            background,
                            bitPos.getX(),
                            bitPos.getY(),
                            paint
                    );
                }
                if (bitmap != null) {
                    canvas.drawBitmap(
                            bitmap,
                            bitPos.getX(),
                            bitPos.getY(),
                            paint
                    );
                }
            }
        }
        Coordinate exitPos = calcPosition(labyrinth.getEndCoordinate());
        canvas.drawBitmap(
                tileset.getExitTiles(),
                exitPos.getX(),
                exitPos.getY(),
                paint
        );
        Coordinate beginPos = calcPosition(labyrinth.getStartCoordinate());
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
        frame = (frame + 1) % 100;
    }
}
