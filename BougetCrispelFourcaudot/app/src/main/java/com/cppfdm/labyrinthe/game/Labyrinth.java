package com.cppfdm.labyrinthe.game;

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
        // fill the table with null to prevent null pointer exception
        for (int i = 0; i < row_; i++) {
            for (int j = 0; j < col_; j++) {
                this.cases[i][j] = null;
            }
        }
        // Add the cases in the labyrinth
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
    protected Case getCase(Coord c) {
        return this.cases[c.getX()][c.getY()];
    }

    /**
     * Get the start Coordinate
     * @return the Coordinate object
     */
    public Coord getStartCoord() {
        return this.start.getCoord();
    }

    /**
     * Getter of ROW parameter
     * @return number of rows
     */
    public int getROW() { return this.ROW; }

    /**
     * Getter of COL parameters
     * @return number of cols
     */
    public int getCOL() { return this.COL; }
}
