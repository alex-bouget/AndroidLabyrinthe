package com.cppfdm.labyrinthe.utils;

import android.graphics.Bitmap;
import android.view.View;

public enum SpriteEnum {
    LINK("LINK", "sprites/link/"),
    MONSTER("MONSTER", "sprites/monster/");

    /**
     * Get all Sprite
     *
     * @return list of sprite
     */
    public static SpriteEnum[] getAllSprite() {
        return SpriteEnum.class.getEnumConstants();
    }

    private final String name;
    private final String path;
    private final String loaderPath;

    private SpriteEnum(String name, String path, String loaderPath) {
        this.name = name;
        this.path = path;
        this.loaderPath = loaderPath;
    }

    private SpriteEnum(String name, String path) {
        this.name = name;
        this.path = path;
        this.loaderPath = path + "spriteLoader.txt";
    }

    /**
     * Get the name of the sprite
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the path
     *
     * @return path
     */
    public String getPath() {
        return path;
    }

    /**
     * Get the loader path
     *
     * @return loader path
     */
    public String getLoaderPath() {
        return loaderPath;
    }

    /**
     * Get the Bitmap
     *
     * @param v a view
     * @param path the path of the bitmap
     * @param loaderPath the path of the loaderPath
     * @return the down sprite
     */
    private Bitmap getIcon(View v, String path, String loaderPath) {
        String data = AssetsCommand.readFile(v, loaderPath);
        if (data == null) {
            return null;
        }
        String[] file = data.split("\n");
        if (file[0].equals("!")) {
            String[] subEnum = file[1].split("!");
            return getIcon(v, subEnum[0], subEnum[1]);
        }
        return ViewerCommand.getBitmap(v, path + file[2].split(">")[0]);
    }

    /**
     * Get the Bitmap
     *
     * @param v a view
     * @return the down sprite
     */
    public Bitmap getIcon(View v) {
        return getIcon(v, path, loaderPath);
    }
}
