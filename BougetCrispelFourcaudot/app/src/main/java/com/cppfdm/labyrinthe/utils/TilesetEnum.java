package com.cppfdm.labyrinthe.utils;

import android.view.View;

import com.cppfdm.labyrinthe.game.Labyrinth;
import com.cppfdm.labyrinthe.gameView.tileset.DefaultTileset;
import com.cppfdm.labyrinthe.gameView.tileset.GrassTileSet;
import com.cppfdm.labyrinthe.gameView.tileset.HarborTileSet;
import com.cppfdm.labyrinthe.gameView.tileset.RoadTileSet;
import com.cppfdm.labyrinthe.view.tileset.TilesInterfaces;

public enum TilesetEnum {
    DEFAULT("DEFAULT", 1),
    GRASS("GRASS", 2),
    HARBOR("HARBOR", 3),
    ROAD("ROAD", 4);

    /**
     * Get all tileset
     * @return list of tilesetEnum
     */
    public static TilesetEnum[] getAllTileset() {
        return TilesetEnum.class.getEnumConstants();
    }

    private final String name;
    private final int id;

    /**
     * Constructor
     *
     * @param name name of the tileset
     * @param id id of the tileset
     */
    private TilesetEnum(String name, int id) {
        this.name = name;
        this.id = id;
    }

    /**
     * Get the name of the tileset
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * get the tileset
     *
     * @param v a view
     * @param labyrinth the labyrinth
     * @return tilesInterfaces
     */
    public TilesInterfaces getTileset(View v, Labyrinth labyrinth) {
        switch (id) {
            case 1:
                return new DefaultTileset(v);
            case 2:
                return new GrassTileSet(v, labyrinth);
            case 3:
                return new HarborTileSet(v, labyrinth);
            case 4:
                return new RoadTileSet(v, labyrinth);
            default:
                return null;
        }
    }
}
