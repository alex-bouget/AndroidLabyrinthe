package com.cppfdm.labyrinthe.game;

import java.util.Objects;

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

    /**
     * Is the object equals
     * @param o the object to test
     * @return true if equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coord coord = (Coord) o;
        return x == coord.x && y == coord.y;
    }

    /**
     * Hashcode function
     * @return the hashcode of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Coord{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
