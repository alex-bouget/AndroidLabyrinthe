package com.cppfdm.labyrinthe.view.tileset;

import android.graphics.Bitmap;

import com.cppfdm.labyrinthe.game.Case;

import java.util.HashMap;

public abstract class AbstractTileset implements TilesInterfaces {
    private HashMap<String, Bitmap> tileset = new HashMap<>();

    public AbstractTileset() {}

    protected boolean addBitmap(String key, Bitmap values) {
        if (tileset.containsKey(key)) {
            return false;
        }
        tileset.put(key, values);
        return true;
    }

    @Override
    public Bitmap getTiles(Case aCase) {
        return tileset.get(getTilesName(aCase));
    }

    @Override
    public Bitmap getExitTiles() {
        return tileset.get(getExitTilesName());
    }

    @Override
    public Bitmap getStartTiles() {
        return tileset.get(getStartTilesName());
    }

    @Override
    public HashMap<String, Bitmap> getAllTiles() {
        return new HashMap<>(tileset);
    }
}
