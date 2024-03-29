package com.cppfdm.labyrinthe.game;

import java.util.Collection;
import java.util.Random;

public class Labyrinth {

    // Dimensions of the labyrinth
    final int ROW;
    final int COL;
    // All cases
    Case[][] cases;
    // Important Case
    Case start;
    Case end;
    // Enemies
    final int NB_ENEMIES = 10;
    Enemy[] enemies;

    /**
     * Constructor of the Labyrinth
     *
     * @param row_   number of rows
     * @param col_   number of columns
     * @param start_ Coordinate of the start
     * @param end_   Coordinate of the end
     * @param cases_ the Coordinate of each cases
     */
    public Labyrinth(int row_, int col_, Coordinate start_, Coordinate end_, Collection<Coordinate> cases_) {
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
        for (Coordinate c : cases_) {
            this.cases[c.getY()][c.getX()] = new Case(c);
        }

        // Generate the neighbours of each Cases
        for (Coordinate c : cases_) {
            this.cases[c.getY()][c.getX()].generateNeighbours(this);
        }

        start = this.getCase(start_);
        end = this.getCase(end_);

        // Create enemies
        enemies = new Enemy[this.NB_ENEMIES];
        for (int i = 0; i < this.NB_ENEMIES; i++) {
            enemies[i] = new Enemy(this.generateRandomEnemyCoordinate());
        }
    }

    /**
     * Generate a Coordinate object in a random non wall case and not on start case
     *
     * @return the correct Coordinate object
     */
    public Coordinate generateRandomEnemyCoordinate() {
        Random random = new Random();
        Case res;
        do {
            Coordinate randomCoordinate = new Coordinate(random.nextInt(this.getROW()), random.nextInt(this.getCOL()));
            res = this.getCase(randomCoordinate);
        } while (res == null || res.getCoordinate().equals(this.start.getCoordinate()));
        return res.getCoordinate();
    }

    /**
     * Move all the enemies to a random neighbour
     */
    public void moveEnemies() {
        for (Enemy enemy : this.enemies) {
            Coordinate newPos = this.getCase(enemy.getPos()).getRandomNeighbour().getCoordinate();
            enemy.setPos(newPos);
        }
    }

    /**
     * Getter of Enemies
     *
     * @return enemies attribute
     */
    public Enemy[] getEnemies() {
        return enemies;
    }

    /**
     * get a Case from his Coordinates
     *
     * @param c Coordinates of the case
     * @return the Case object
     */
    public Case getCase(Coordinate c) {
        if (c.getX() < 0 || c.getY() < 0) {
            return null;
        }
        if (c.getX() >= this.COL || c.getY() >= this.ROW) {
            return null;
        }
        return this.cases[c.getY()][c.getX()];
    }

    /**
     * Get the start Coordinate
     *
     * @return the Coordinate object
     */
    public Coordinate getStartCoordinate() {
        return this.start.getCoordinate();
    }

    /**
     * Get the end Coordinate
     *
     * @return the Coordinate object
     */
    public Coordinate getEndCoordinate() {
        return this.end.getCoordinate();
    }

    /**
     * Getter of ROW parameter
     *
     * @return number of rows
     */
    public int getROW() {
        return this.ROW;
    }

    /**
     * Getter of COL parameters
     *
     * @return number of cols
     */
    public int getCOL() {
        return this.COL;
    }

    /**
     * Reset the enemies coordinate
     */
    public void resetEnemyCoordinates() {
        for (int i = 0; i < this.NB_ENEMIES; i++) {
            enemies[i].setPos(generateRandomEnemyCoordinate());
        }
    }
}
