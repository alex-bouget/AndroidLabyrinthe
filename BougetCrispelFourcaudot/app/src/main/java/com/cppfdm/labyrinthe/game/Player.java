package com.cppfdm.labyrinthe.game;

public class Player {

    Labyrinth laby;
    Coord pos;

    /**
     * Constructor of player
     * @param laby_ the laby where the player will play
     */
    public Player(Labyrinth laby_) {
        this.laby = laby_;
        this.pos = laby_.getStartCoord();
    }
}
