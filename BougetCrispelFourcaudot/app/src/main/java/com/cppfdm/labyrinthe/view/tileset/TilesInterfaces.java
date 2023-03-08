package com.cppfdm.labyrinthe.view.tileset;

import android.graphics.Bitmap;

import com.cppfdm.labyrinthe.game.Case;

import java.util.HashMap;

public interface TilesInterfaces {
    /**
     * Get the tiles
     *
     * @param aCase the case for the tiles
     * @return bitmap of the tiles
     */
    Bitmap getTiles(Case aCase);

    /**
     * Get the names of the tiles
     *
     * @param aCase the case for the tiles
     * @return name of the tiles
     */
    String getTilesName(Case aCase);

    /**
     * Get exit tiles
     *
     * @return bitmap of the tiles
     */
    Bitmap getExitTiles();

    /**
     * Get name of exit tiles
     *
     * @return name of the tiles
     */
    String getExitTilesName();

    /**
     * Get start tiles
     *
     * @return bitmap of the tiles
     */
    Bitmap getStartTiles();

    /**
     * Get name of start tiles
     *
     * @return name of the tiles
     */
    String getStartTilesName();

    /**
     * Get the list of the tiles
     *
     * @return Hashmap<String, Bitmap>
     */
    HashMap<String, Bitmap> getAllTiles();
}
