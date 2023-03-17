package com.cppfdm.labyrinthe.game;

import java.util.Random;

/**
 * Case class who represent a Case in the labyrinth
 */
public class Case {

    private final Coordinate pos;
    Case[] neighbours;

    /**
     * Constructor of a Case with a Coordinate object
     *
     * @param pos : A Coordinate object for the Case
     */
    public Case(Coordinate pos) {
        this.pos = pos;
        this.neighbours = new Case[4];
    }

    /**
     * Constructor of a Case with Cartesian coordinates
     *
     * @param x : x position of the Case
     * @param y : y position of the Case
     */
    public Case(int x, int y) {
        this.pos = new Coordinate(x, y);
        this.neighbours = new Case[4];
    }

    /**
     * Get a copy of the Coordinate of a Case
     *
     * @return the Coordinate object
     */
    public Coordinate getCoordinate() {
        return new Coordinate(this.pos.getX(), this.pos.getY());
    }

    /**
     * Get the neighbours of a Case
     *
     * @return the neighbours
     */
    public Case[] getNeighbours() {
        return this.neighbours;
    }

    /**
     * Generate the neighbours of this case
     *
     * @param laby the Labyrinth of the Case
     */
    protected void generateNeighbours(Labyrinth laby) {
        int row = laby.getROW();
        int col = laby.getCOL();

        // Generate the coordinate of each case's neighbours
        Coordinate[] nextTo = new Coordinate[4];
        // java modulo can return negative number,
        // so we add row or col to wrap the modulo to its positive side
        nextTo[0] = new Coordinate((this.pos.getX() + 1 + col) % col, this.pos.getY()); // right
        nextTo[1] = new Coordinate((this.pos.getX() - 1 + col) % col, this.pos.getY()); // left
        nextTo[2] = new Coordinate(this.pos.getX(), (this.pos.getY() - 1 + row) % row); // up
        nextTo[3] = new Coordinate(this.pos.getX(), (this.pos.getY() + 1 + row) % row); // down

        // Add results in this.neighbours
        int index = 0;
        for (Coordinate c : nextTo) {
            // Is the case nextTo this ?
            if (Math.abs(c.getX() - this.pos.getX()) + Math.abs(c.getY() - this.pos.getY()) == 1) {
                Case current = laby.getCase(c);
                // Is the case exist ?
                if (current != null) {
                    this.neighbours[index] = current;
                    index++;
                }
            }
        }
    }

    /**
     * Get a random non-null neighbour
     *
     * @return the Case object
     */
    public Case getRandomNeighbour() {
        // Handle no neighbour
        if (this.neighbours[0] == null) {
            return this;
        }
        Case neighbour;
        Random random = new Random();
        do {
            int index = random.nextInt(4);
            neighbour = this.neighbours[index];
        } while (neighbour == null);
        return neighbour;
    }
}
