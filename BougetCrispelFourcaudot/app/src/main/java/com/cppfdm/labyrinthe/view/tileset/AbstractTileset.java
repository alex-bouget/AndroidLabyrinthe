package com.cppfdm.labyrinthe.view.tileset;

import android.graphics.Bitmap;
import android.view.View;

import com.cppfdm.labyrinthe.game.Case;
import com.cppfdm.labyrinthe.utils.ViewerCommand;

import java.util.HashMap;

public abstract class AbstractTileset implements TilesInterfaces {
    private final HashMap<String, Bitmap> tileset = new HashMap<>();
    private final View v;
    protected int frame;

    /**
     * Constructor
     *
     * @param v view parent
     */
    public AbstractTileset(View v) {
        this.v = v;
        this.frame=0;
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
        Bitmap bitmap = ViewerCommand.getBitmap(v, id);
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
        Bitmap bitmap = ViewerCommand.getBitmap(v, values);
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
        return (getTilesName(aCase) == null )? null :  tileset.get(getTilesName(aCase));
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

    /**
     * Set the frame at the start of a new draw
     *
     * @param frame number of frame
     */
    @Override
    public void setFrame(int frame) {
        this.frame = frame;
    }

    /**
     * Get the background tile
     *
     * @return Bitmap
     */
    @Override
    public Bitmap getBackgroundTiles() {
        String backName = getBackgroundTilesName();
        if (backName == null) {
            return null;
        }
        return tileset.get(getBackgroundTilesName());
    }


}
