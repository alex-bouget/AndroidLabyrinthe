package com.cppfdm.labyrinthe.game;

import androidx.annotation.NonNull;

import java.util.Objects;

/**
 * Class to store and make easy operation on Cartesian coordinates
 */
public class Coordinate {
    private final int x;
    private final int y;

    /**
     * Constructor of Coordinate object
     *
     * @param x : the x element of a coordinate
     * @param y : the y element of a coordinate
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter of x
     *
     * @return the x element
     */
    public int getX() {
        return x;
    }

    /**
     * Getter of  y
     *
     * @return the y element
     */
    public int getY() {
        return y;
    }

    /**
     * Is the object equals
     *
     * @param o the object to test
     * @return true if equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate coordinate = (Coordinate) o;
        return x == coordinate.x && y == coordinate.y;
    }

    /**
     * Hashcode function
     *
     * @return the hashcode of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Transform a coordinates to string
     *
     * @return string
     */
    @NonNull
    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
