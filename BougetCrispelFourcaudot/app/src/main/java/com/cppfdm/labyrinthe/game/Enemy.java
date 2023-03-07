package com.cppfdm.labyrinthe.game;

public class Enemy {

    // Position of the enemy
    Coord pos;

    /**
     * Constructor of an enemy
     * @param coord position of the Enemy
     */
    public Enemy(Coord coord) {
        this.pos = coord;
    }

    /**
     * Getter of pos attribute
     * @return Coord object pos
     */
    public Coord getPos() {
        return pos;
    }

    /**
     * Setter of pos parameter
     * @param c the new position
     */
    public void setpos(Coord c) {
        this.pos = c;
    }
}
