package com.cppfdm.labyrinthe.game;

import java.util.ArrayList;
import java.util.Collection;

public class Labyrinth {

    // Dimensions of the labyrinth
    final int ROW;
    final int COL;
    // All cases
    Case[][] cases;
    // Important Case
    Case start;
    Case end;

    /**
     * Constructor of the Labyrinth
     * @param row_ number of rows
     * @param col_ number of columns
     * @param start_ Coordinate of the start
     * @param end_ Coordinate of the end
     * @param cases_ the Coord of each cases
     */
    public Labyrinth(int row_, int col_, Coord start_, Coord end_, Collection<Coord> cases_) {
        this.ROW = row_;
        this.COL = col_;
        this.cases = new Case[row_][col_];
        for (Coord c : cases_) {
            this.cases[c.getX()][c.getY()] = new Case(c);
        }
        start = this.getCase(start_);
        end = this.getCase(end_);
    }

    /**
     * get a Case from his Coordinates
     * @param c Coordinates of the case
     * @return the Case object
     */
    private Case getCase(Coord c) {
        return this.cases[c.getX()][c.getY()];
    }

    /**
     * Get the start Coordinate
     * @return the Coordinate object
     */
    public Coord getStartCoord() {
        return this.start.getCoord();
    }
}
