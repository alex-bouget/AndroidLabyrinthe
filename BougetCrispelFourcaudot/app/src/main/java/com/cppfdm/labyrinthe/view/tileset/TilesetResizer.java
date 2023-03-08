package com.cppfdm.labyrinthe.view.tileset;

import android.graphics.Bitmap;

import com.cppfdm.labyrinthe.game.Case;
import com.cppfdm.labyrinthe.utils.ViewerCommand;

import java.util.HashMap;

public class TilesetResizer implements TilesInterfaces {
    TilesInterfaces tileset;
    HashMap<String, Bitmap> tilesetResized = new HashMap<>();

    /**
     * Constructor
     *
     * @param tileset basis tileset
     */
    public TilesetResizer(TilesInterfaces tileset) {
        this.tileset = tileset;
    }

    /**
     * Resized the basis tileset
     *
     * @param scale size of the tileset in pixels
     */
    public void resized(int scale) {
        tilesetResized.clear();
        HashMap<String, Bitmap> tilesNotResized = tileset.getAllTiles();
        for (String i : tilesNotResized.keySet()) {
            tilesetResized.put(i, ViewerCommand.resizeBitmap(tilesNotResized.get(i), scale, scale));
        }
    }

    /**
     * Get the tiles
     *
     * @param aCase the case for the tiles
     * @return bitmap of the tiles
     */
    @Override
    public Bitmap getTiles(Case aCase) {
        return tilesetResized.get(getTilesName(aCase));
    }


    /**
     * Get the names of the tiles
     *
     * @param aCase the case for the tiles
     * @return name of the tiles
     */
    @Override
    public String getTilesName(Case aCase) {
        return tileset.getTilesName(aCase);
    }

    /**
     * Get exit tiles
     *
     * @return bitmap of the tiles
     */
    @Override
    public Bitmap getExitTiles() {
        return tilesetResized.get(getExitTilesName());
    }


    /**
     * Get name of exit tiles
     *
     * @return name of the tiles
     */
    @Override
    public String getExitTilesName() {
        return tileset.getExitTilesName();
    }


    /**
     * Get start tiles
     *
     * @return bitmap of the tiles
     */
    @Override
    public Bitmap getStartTiles() {
        return tilesetResized.get(getStartTilesName());
    }

    /**
     * Get name of start tiles
     *
     * @return name of the tiles
     */
    @Override
    public String getStartTilesName() {
        return tileset.getStartTilesName();
    }

    /**
     * Get the list of the tiles
     *
     * @return Hashmap<String, Bitmap>
     */
    @Override
    public HashMap<String, Bitmap> getAllTiles() {
        return tilesetResized;
    }
}
