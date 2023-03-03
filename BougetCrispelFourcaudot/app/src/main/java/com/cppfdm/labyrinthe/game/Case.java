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

    protected void generateNeighbours(Labyrinth laby) {
        int row = laby.getROW();
        int col = laby.getCOL();

        // Generate the coordinate of each case's neighbours
        Coord[] nextTo = new Coord[4];
        nextTo[0] = new Coord((this.pos.getX()+1) % col, this.pos.getY()); // right
        nextTo[1] = new Coord((this.pos.getX()-1) % col, this.pos.getY()); // left
        nextTo[2] = new Coord(this.pos.getX(), (this.pos.getY()-1) % row); // up
        nextTo[3] = new Coord(this.pos.getX(), (this.pos.getY()+1) % row); // down

        // Add results in this.neighbours
        int index = 0;
        for (Coord c : nextTo) {
            if (Math.abs(c.getX()-this.pos.getX()) + Math.abs(c.getY()-this.pos.getY()) != 1) {
                Case current = laby.getCase(c);
                if (current != null) {
                    this.neighbours[index] = current;
                    index++;
                }
            }
        }
    }
}
