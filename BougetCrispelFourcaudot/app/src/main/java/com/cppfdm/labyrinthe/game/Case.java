package com.cppfdm.labyrinthe.game;

/**
 * Case class who represent a Case in the labyrinth
 */
public class Case {

    private Coord pos;
    Case[] neighbours;

    /**
     * Constructor of a Case with a Coord object
     * @param pos : A Coord object for the Case
     */
    public Case(Coord pos) {
        this.pos = pos;
        this.neighbours = new Case[4];
    }

    /**
     * Constructor of a Case with Cartesian coordinates
     * @param x : x position of the Case
     * @param y : y position of the Case
     */
    public Case(int x, int y) {
        this.pos = new Coord(x, y);
        this.neighbours = new Case[4];
    }

    /**
     * Get a copy of the Coordinate of a Case
     * @return the Coordinate object
     */
    public Coord getCoord() {
        return new Coord(this.pos.getX(), this.pos.getY());
    }
}
