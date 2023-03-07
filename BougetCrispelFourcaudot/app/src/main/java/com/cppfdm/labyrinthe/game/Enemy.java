package com.cppfdm.labyrinthe.game;

public class Enemy {

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
}
