package com.cppfdm.labyrinthe.gameView.tileset;

import android.content.res.AssetManager;
import android.view.View;

import com.cppfdm.labyrinthe.game.Case;
import com.cppfdm.labyrinthe.game.Coordinate;
import com.cppfdm.labyrinthe.game.Labyrinth;
import com.cppfdm.labyrinthe.view.tileset.AbstractTileset;

import java.io.IOException;

public class HarborTileSet extends AbstractTileset{

    private final static String ASSETS_PATH = "tiles/harbor/";
    // Store each tile type to not make calculations for each render
    private final String[][] casesTiles;

    public HarborTileSet(View v, Labyrinth laby) {
        super(v);
        // Add all bitmap
        AssetManager assetManager = v.getResources().getAssets();
        try {
            for (String filename : assetManager.list(ASSETS_PATH)) {
                addBitmap(filename.substring(0, filename.length() - 4), ASSETS_PATH + filename);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        casesTiles = new String[laby.getCOL()][laby.getROW()];
        for (int i = 0; i < laby.getCOL(); i++) {
            for (int j = 0; j < laby.getROW(); j++) {
                generateTileType(i, j, laby);
            }
        }
    }

    /**
     * Generate and store the tile type in casesTiles
     * @param i x Coordinate of the case
     * @param j y Coordinate of the case
     * @param laby  the labyrinth
     */
    private void generateTileType(int i, int j, Labyrinth laby) {
        // handle null case
        if (laby.getCase(new Coordinate(i, j)) == null) {return;}
        // generate good case
        String imgPath = "";
        imgPath += laby.getCase(new Coordinate(i , j -1)) == null ? "0" : "1";
        imgPath += laby.getCase(new Coordinate(i -1 , j)) == null ? "0" : "1";
        imgPath += laby.getCase(new Coordinate(i +1, j)) == null ? "0" : "1";
        imgPath += laby.getCase(new Coordinate(i , j + 1)) == null ? "0" : "1";

        casesTiles[i][j] = imgPath;
    }

    /**
     * Get the tile name of the Case
     * @param aCase the case for the tiles
     * @return the tile name
     */
    @Override
    public String getTilesName(Case aCase) {
        if (aCase == null) {return null;}
        return casesTiles[aCase.getCoordinate().getX()][aCase.getCoordinate().getY()];
    }

    /**
     * Get the exit tile name
     * @return the exit tile name
     */
    @Override
    public String getExitTilesName() {
        return "end";
    }

    /**
     * Get the start tile name
     * @return the start tile name
     */
    @Override
    public String getStartTilesName() {
        return "start";
    }

    /**
     * Get the background tile name
     * @return the background tile name
     */
    @Override
    public String getBackgroundTilesName() {
        return "water" + (int)(frame%8/4);
    }
}
