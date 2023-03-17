package com.cppfdm.labyrinthe.game;

public class Enemy {

    // Position of the enemy
    Coordinate pos;

    /**
     * Constructor of an enemy
     * @param coordinate position of the Enemy
     */
    public Enemy(Coordinate coordinate) {
        this.pos = coordinate;
    }

    /**
     * Getter of pos attribute
     * @return Coordinate object pos
     */
    public Coordinate getPos() {
        return pos;
    }

    /**
     * Setter of pos parameter
     * @param c the new position
     */
    public void setPos(Coordinate c) {
        this.pos = c;
    }
}
