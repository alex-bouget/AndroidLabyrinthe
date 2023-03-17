package com.cppfdm.labyrinthe.gameView.tileset;

import android.content.res.AssetManager;
import android.view.View;

import com.cppfdm.labyrinthe.game.Case;
import com.cppfdm.labyrinthe.game.Coordinate;
import com.cppfdm.labyrinthe.game.Labyrinth;
import com.cppfdm.labyrinthe.view.tileset.AbstractTileset;

import java.io.IOException;
import java.util.Random;
import java.util.Set;

public class GrassTileSet extends AbstractTileset {

    private final static String ASSETS_PATH = "tiles/grass/";
    // Store each tile type to not make calculations for each render
    private final String[][] casesTiles;
    private final Set<String> allTiles;

    public GrassTileSet(View v, Labyrinth laby) {
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
        allTiles = getAllTiles().keySet();
        casesTiles = new String[laby.getCOL()][laby.getROW()];
        for (int i = 0; i < laby.getCOL(); i++) {
            for (int j = 0; j < laby.getROW(); j++) {
                generateTileType(i, j, laby);
            }
        }
    }

    /**
     * Generate the tile type for a tile in the laby
     *
     * @param i    X coordinate in the labyrinth
     * @param j    Y coordinate in the labyrinth
     * @param laby the labyrinth
     */
    private void generateTileType(int i, int j, Labyrinth laby) {
        if (laby.getCase(new Coordinate(i, j)) == null) {
            return;
        }
        StringBuilder imgPath = new StringBuilder();
        for (int offX = -1; offX < 2; offX++) {
            for (int offY = -1; offY < 2; offY++) {
                imgPath.append(laby.getCase(new Coordinate(i + offY, j + offX)) == null ? "0" : "1");
            }
        }
        String imagePath = null;
        if (imgPath.toString().equals("111111111")) {
            imagePath = "C_" + (int) (new Random().nextInt(12) + 1);
        } else if (!allTiles.contains(imgPath.toString())) {
            imagePath = this.resolveImgPath(imgPath.toString());
        }
        casesTiles[i][j] = imagePath;
    }

    /**
     * Change a non existing img path to an existing img path
     *
     * @param img the imgPath
     * @return the new imgPath
     */
    private String resolveImgPath(String img) {
        StringBuilder res = new StringBuilder(img);
        if (img.charAt(1) == '0' || img.charAt(3) == '0') {
            res.setCharAt(0, '0');
        }
        if (img.charAt(1) == '0' || img.charAt(5) == '0') {
            res.setCharAt(2, '0');
        }
        if (img.charAt(3) == '0' || img.charAt(7) == '0') {
            res.setCharAt(6, '0');
        }
        if (img.charAt(5) == '0' || img.charAt(7) == '0') {
            res.setCharAt(8, '0');
        }
        return res.toString();
    }

    /**
     * return the appropriate tile name
     *
     * @param aCase the case for the tiles
     * @return the tile name or null if background
     */
    @Override
    public String getTilesName(Case aCase) {
        if (aCase == null) {
            return null;
        }
        Coordinate c = aCase.getCoordinate();
        return this.casesTiles[c.getX()][c.getY()];
    }

    /**
     * Get the name of the exit tile
     *
     * @return the exit tile name
     */
    @Override
    public String getExitTilesName() {
        return "end" + (int) (frame % 5);
    }

    /**
     * Get the name of the entrance tile
     *
     * @return the entrance tile name
     */
    @Override
    public String getStartTilesName() {
        return "000010000";
    }

    /**
     * Get the background tile name
     *
     * @return the background tile name
     */
    @Override
    public String getBackgroundTilesName() {
        return "water" + (int) ((frame % 8 / 2) + 1);
    }
}
