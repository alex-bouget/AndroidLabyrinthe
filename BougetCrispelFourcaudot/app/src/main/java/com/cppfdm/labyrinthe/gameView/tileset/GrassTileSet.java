package com.cppfdm.labyrinthe.gameView.tileset;

import android.content.res.AssetManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.cppfdm.labyrinthe.R;
import com.cppfdm.labyrinthe.game.Case;
import com.cppfdm.labyrinthe.game.Coord;
import com.cppfdm.labyrinthe.game.Labyrinth;
import com.cppfdm.labyrinthe.view.tileset.AbstractTileset;

import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GrassTileSet extends AbstractTileset {

    private final static String ASSETS_PATH = "tiles/grass/";
    private String[][] casesTiles;

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
        Set<String> allTiles = getAllTiles().keySet();
        casesTiles = new String[laby.getCOL()][laby.getROW()];
        for (int i = 0; i < laby.getCOL(); i++) {
            for (int j = 0; j < laby.getROW(); j++) {
                if (laby.getCase(new Coord(i, j)) == null) {
                    casesTiles[i][j] = "water";
                } else {
                    String imgPath = "";
                    for (int offX = -1; offX < 2; offX++) {
                        for (int offY = -1; offY < 2; offY++) {
                            imgPath += laby.getCase(new Coord(i + offY, j + offX)) == null ? "0" : "1";
                        }
                    }
                    if (imgPath.equals("111111111")) {
                        imgPath = "C_" + (int) (new Random().nextInt(12) + 1);
                    } else if (! allTiles.contains(imgPath)) {
                        imgPath = this.resolveImgPath(imgPath);
                    }
                    casesTiles[i][j] = imgPath;
                }
            }
        }
    }

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
        System.out.println("String simplify : " + img + "->" + res.toString());
        return res.toString();
    }

    @Override
    public String getTilesName(Case aCase) {
        if (aCase == null) {
            return "water";
        }
        Coord c = aCase.getCoord();
        return this.casesTiles[c.getX()][c.getY()];
    }

    @Override
    public String getExitTilesName() {
        return "000010000";
    }

    @Override
    public String getStartTilesName() {
        return "000010000";
    }
}
