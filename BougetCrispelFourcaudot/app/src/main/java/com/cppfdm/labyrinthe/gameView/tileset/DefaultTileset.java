package com.cppfdm.labyrinthe.gameView.tileset;

import android.view.View;

import com.cppfdm.labyrinthe.game.Case;
import com.cppfdm.labyrinthe.view.tileset.AbstractTileset;

public class DefaultTileset extends AbstractTileset {
    private final static String ASSETS_PATH = "tiles/default/";

    public DefaultTileset(View v) {
        super(v);
        addBitmap("exit", ASSETS_PATH + "exit.gif");
        addBitmap("start", ASSETS_PATH + "start.gif");
        addBitmap("ground", ASSETS_PATH + "ground.gif");
        addBitmap("wall", ASSETS_PATH + "wall.gif");
    }

    @Override
    public String getTilesName(Case aCase) {
        if (aCase == null) {
            return "wall";
        }
        return "ground";
    }

    @Override
    public String getExitTilesName() {
        return "exit";
    }

    @Override
    public String getStartTilesName() {
        return "start";
    }
}
