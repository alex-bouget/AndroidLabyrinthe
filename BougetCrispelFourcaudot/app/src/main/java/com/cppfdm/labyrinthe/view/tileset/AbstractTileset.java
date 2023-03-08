package com.cppfdm.labyrinthe.view.tileset;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import com.cppfdm.labyrinthe.game.Case;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public abstract class AbstractTileset implements TilesInterfaces {
    private final HashMap<String, Bitmap> tileset = new HashMap<>();
    private final View v;

    /**
     * Constructor
     *
     * @param v view parent
     */
    public AbstractTileset(View v) {
        this.v = v;
    }

    /**
     * Add a bitmap
     *
     * @param key    key of the bitmap
     * @param values bitmap
     * @return true if success
     */
    protected boolean addBitmap(String key, Bitmap values) {
        if (tileset.containsKey(key)) {
            return false;
        }
        tileset.put(key, values);
        return true;
    }

    /**
     * Add a bitmap from the drawable
     *
     * @param key key of the bitmap
     * @param id  id of the drawable
     * @return true if success
     */
    protected boolean addBitmap(String key, int id) {
        Bitmap bitmap = BitmapFactory.decodeResource(v.getContext().getResources(), id);
        return addBitmap(key, bitmap);
    }

    /**
     * Add a bitmap from the assets
     *
     * @param key    key of the bitmap
     * @param values path of the assets
     * @return true if success
     */
    protected boolean addBitmap(String key, String values) {
        InputStream is;
        try {
            is = v.getResources().getAssets().open(values);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        return addBitmap(key, bitmap);
    }

    /**
     * Get the tiles
     *
     * @param aCase the case for the tiles
     * @return bitmap of the tiles
     */
    @Override
    public Bitmap getTiles(Case aCase) {
        return tileset.get(getTilesName(aCase));
    }

    /**
     * Get exit tiles
     *
     * @return bitmap of the tiles
     */
    @Override
    public Bitmap getExitTiles() {
        return tileset.get(getExitTilesName());
    }

    /**
     * Get start tiles
     *
     * @return bitmap of the tiles
     */
    @Override
    public Bitmap getStartTiles() {
        return tileset.get(getStartTilesName());
    }

    /**
     * Get the list of the tiles
     *
     * @return Hashmap<String, Bitmap>
     */
    @Override
    public HashMap<String, Bitmap> getAllTiles() {
        return new HashMap<>(tileset);
    }
}
