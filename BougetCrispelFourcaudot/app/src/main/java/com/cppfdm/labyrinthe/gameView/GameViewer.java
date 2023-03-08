package com.cppfdm.labyrinthe.gameView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.cppfdm.labyrinthe.R;
import com.cppfdm.labyrinthe.game.Case;
import com.cppfdm.labyrinthe.game.Coord;
import com.cppfdm.labyrinthe.game.Labyrinth;
import com.cppfdm.labyrinthe.game.Player;
import com.cppfdm.labyrinthe.view.Viewer;
import com.cppfdm.labyrinthe.view.ViewerCommand;
import com.cppfdm.labyrinthe.view.core.AbstractDrawable;
import com.cppfdm.labyrinthe.view.core.Drawable;

import java.util.HashMap;

public class GameViewer extends AbstractDrawable {
    private int scale = 64;
    Viewer root;
    Player player;
    HashMap<String, Bitmap> tiles;


    /**
     * Constructor
     *
     * @param player the player
     */
    public GameViewer(Player player) {
        this.player = player;
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
        resized(scale);
    }

    /**
     * Add a tiles in the map
     *
     * @param name name of the tiles
     * @param id id of the tiles
     */
    protected void addTiles(String name, int id) {
        Bitmap notSized = BitmapFactory.decodeResource(root.getContext().getResources(), id);
        tiles.put(name, ViewerCommand.resizeBitmap(notSized, scale, scale));
    }

    /**
     * Resized the labyrinth and all bitmap
     *
     * @param scale scale in pixel
     */
    public void resized(int scale) {
        this.scale = scale;
        tiles = new HashMap<>();
        addTiles("wall", R.drawable.mur0);
        addTiles("ground", R.drawable.ground);
        addTiles("exit", R.drawable.sortie);
        addTiles("start", R.drawable.bluerock);
    }

    /**
     * Paint the element
     *
     * @param canvas element for paint inside
     * @param paint can be useful
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
                if (aCase == null) {
                    canvas.drawBitmap(
                            tiles.get("wall"),
                            (xSize - playerCase.getX()) * scale + (width / 2),
                            (ySize - playerCase.getY()) * scale + (height / 2),
                            paint
                    );
                } else {
                    canvas.drawBitmap(
                            tiles.get("ground"),
                            (xSize - playerCase.getX()) * scale + (width / 2),
                            (ySize - playerCase.getY()) * scale + (height / 2),
                            paint
                    );
                }
            }
        }
        Coord exitCoordinates = labyrinth.getEndCoord();
        canvas.drawBitmap(
                tiles.get("exit"),
                (exitCoordinates.getX() - playerCase.getX()) * scale + (width / 2),
                (exitCoordinates.getX() - playerCase.getY()) * scale + (height / 2),
                paint
        );
        Coord beginCoordinates = labyrinth.getStartCoord();
        canvas.drawBitmap(
                tiles.get("start"),
                (beginCoordinates.getX() - playerCase.getX()) * scale + (width / 2),
                (beginCoordinates.getX() - playerCase.getY()) * scale + (height / 2),
                paint
        );
        canvas.drawRect((float) (width / 2), (float) (height / 2), (float) (width / 2) + (float) scale, (float) (height / 2) + (float) scale, paint);
    }
}
