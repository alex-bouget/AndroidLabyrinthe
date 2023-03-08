package com.cppfdm.labyrinthe.gameView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.cppfdm.labyrinthe.game.Case;
import com.cppfdm.labyrinthe.game.Coord;
import com.cppfdm.labyrinthe.game.Labyrinth;
import com.cppfdm.labyrinthe.game.Player;
import com.cppfdm.labyrinthe.gameView.tileset.DefaultTileset;
import com.cppfdm.labyrinthe.view.Viewer;
import com.cppfdm.labyrinthe.view.core.AbstractDrawable;
import com.cppfdm.labyrinthe.view.core.Drawable;
import com.cppfdm.labyrinthe.view.tileset.TilesetResizer;

public class GameViewer extends AbstractDrawable {
    private int scale = 64;
    Viewer root;
    Player player;
    PlayerViewer playerViewer;
    TilesetResizer tileset;


    /**
     * Constructor
     *
     * @param player the player
     */
    public GameViewer(Player player) {
        this.player = player;
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
        playerViewer = new PlayerViewer();
        playerViewer.setDrawableParent(this);
        resize(scale);
    }

    /**
     * Paint the element
     *
     * @param canvas element for paint inside
     * @param paint  can be useful
     */
    @Override
    public void paint(Canvas canvas, Paint paint) {
        int width = root.getWidth();
        int height = root.getHeight();
        Labyrinth labyrinth = player.getLaby();
        Coord playerCase = player.getCurrentCase().getCoord();
        for (int xSize = 0; xSize < labyrinth.getCOL(); xSize++) {
            for (int ySize = 0; ySize < labyrinth.getROW(); ySize++) {
                Case aCase = labyrinth.getCase(new Coord(xSize, ySize));
                Bitmap bitmap = tileset.getTiles(aCase);
                canvas.drawBitmap(
                        bitmap,
                        (xSize - playerCase.getX()) * scale + (width / 2),
                        (ySize - playerCase.getY()) * scale + (height / 2),
                        paint
                );
            }
        }
        Coord exitCoordinates = labyrinth.getEndCoord();
        canvas.drawBitmap(
                tileset.getExitTiles(),
                (exitCoordinates.getX() - playerCase.getX()) * scale + (width / 2),
                (exitCoordinates.getY() - playerCase.getY()) * scale + (height / 2),
                paint
        );
        Coord beginCoordinates = labyrinth.getStartCoord();
        canvas.drawBitmap(
                tileset.getStartTiles(),
                (beginCoordinates.getX() - playerCase.getX()) * scale + (width / 2),
                (beginCoordinates.getY() - playerCase.getY()) * scale + (height / 2),
                paint
        );
        playerViewer.paint(canvas, paint);
    }
}
