package com.cppfdm.labyrinthe.view.tileset;

import android.graphics.Bitmap;

import com.cppfdm.labyrinthe.game.Case;
import com.cppfdm.labyrinthe.view.ViewerCommand;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class TilesetResizer implements TilesInterfaces {
    TilesInterfaces tileset;
    HashMap<String, Bitmap> tilesetResized;

    public TilesetResizer(TilesInterfaces tileset) {
        this.tileset = tileset;
    }

    public void resized(int scale) {
        HashMap<String, Bitmap> tilesNotResized = tileset.getAllTiles();
        for (String i: tilesNotResized.keySet()) {
            tilesetResized.put(i, ViewerCommand.resizeBitmap(tilesNotResized.get(i), scale, scale));
        }
    }

    @Override
    public Bitmap getTiles(Case aCase) {
        return tilesetResized.get(getTilesName(aCase));
    }

    @Override
    public String getTilesName(Case aCase) {
        return tileset.getTilesName(aCase);
    }

    @Override
    public Bitmap getExitTiles() {
        return tilesetResized.get(getExitTilesName());
    }

    @Override
    public String getExitTilesName() {
        return tileset.getExitTilesName();
    }

    @Override
    public Bitmap getStartTiles() {
        return tilesetResized.get(getStartTilesName());
    }

    @Override
    public String getStartTilesName() {
        return tileset.getStartTilesName();
    }

    @Override
    public HashMap<String, Bitmap> getAllTiles() {
        return tilesetResized;
    }
}
