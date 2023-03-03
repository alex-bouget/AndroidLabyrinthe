package com.cppfdm.labyrinthe.game;

/**
 * Class to store and make easy operation on Cartesian coordinates
 */
public class Coord {
    private int x;
    private int y;

    /**
     * Constructor of Coord object
     * @param x : the x element of a coordinate
     * @param y : the y element of a coordinate
     */
    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter of x
     * @return the x element
     */
    public int getX() {
        return x;
    }

    /**
     * Getter of  y
     * @return the y element
     */
    public int getY() {
        return y;
    }
}