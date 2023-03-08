package com.cppfdm.labyrinthe.view.tileset;

import android.graphics.Bitmap;

import com.cppfdm.labyrinthe.game.Case;

import java.util.HashMap;

public interface TilesInterfaces {
    Bitmap getTiles(Case aCase);
    String getTilesName(Case aCase);
    Bitmap getExitTiles();
    String getExitTilesName();
    Bitmap getStartTiles();
    String getStartTilesName();
    HashMap<String, Bitmap> getAllTiles();
}
