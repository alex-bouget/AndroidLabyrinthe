package com.cppfdm.labyrinthe.gameView.tileset;

import android.view.View;

import com.cppfdm.labyrinthe.game.Case;
import com.cppfdm.labyrinthe.view.tileset.AbstractTileset;

public class DefaultTileset extends AbstractTileset {
    private final static String ASSETS_PATH = "tiles/default/";

    /**
     * Constructor
     *
     * @param v the view of the parent
     */
    public DefaultTileset(View v) {
        super(v);
        addBitmap("exit", ASSETS_PATH + "exit.gif");
        addBitmap("start", ASSETS_PATH + "start.gif");
        addBitmap("ground", ASSETS_PATH + "ground.gif");
        addBitmap("wall", ASSETS_PATH + "wall.gif");
    }

    /**
     * Get the names of the tiles
     *
     * @param aCase the case for the tiles
     * @return name of the tiles
     */
    @Override
    public String getTilesName(Case aCase) {
        if (aCase == null) {
            return "wall";
        }
        return "ground";
    }

    /**
     * Get name of exit tiles
     *
     * @return name of the tiles
     */
    @Override
    public String getExitTilesName() {
        return "exit";
    }

    /**
     * Get start tiles
     *
     * @return bitmap of the tiles
     */
    @Override
    public String getStartTilesName() {
        return "start";
    }

    /**
     * Get name of background tiles
     *
     * @return name of the tiles
     */
    @Override
    public String getBackgroundTilesName() {
        return null;
    }
}
