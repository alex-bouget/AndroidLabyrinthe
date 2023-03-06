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

    /**
     * Get the case object where the player is
     * @return the Case
     */
    public Case getCurrentCase() {
        return this.laby.getCase(this.pos);
    }

    /**
     * Make the player move left
     * @return if the movement has been done
     */
    public boolean moveLeft() {
        Coord target = new Coord(this.pos.getX()-1, this.pos.getY());
        for (Case c : this.getCurrentCase().getNeighbours()) {
            if (c == null) { continue; }
            if (c.getCoord().equals(target)) {
                this.pos = target;
            }
        }
        return this.pos.equals(target);
    }

    /**
     * Make the player move right
     * @return if the movement has been done
     */
    public boolean moveRight() {
        Coord target = new Coord(this.pos.getX()+1, this.pos.getY());
        for (Case c : this.getCurrentCase().getNeighbours()) {
            if (c == null) { continue; }
            if (c.getCoord().equals(target)) {
                this.pos = target;
            }
        }
        return this.pos.equals(target);
    }

    /**
     * make the player move up
     * @return if the movement has been done
     */
    public boolean moveUp() {
        Coord target = new Coord(this.pos.getX(), this.pos.getY()-1);
        for (Case c : this.getCurrentCase().getNeighbours()) {
            if (c == null) { continue; }
            if (c.getCoord().equals(target)) {
                this.pos = target;
            }
        }
        return this.pos.equals(target);
    }

    /**
     * make the player move down
     * @return if the movement has been done
     */
    public boolean moveDown() {
        Coord target = new Coord(this.pos.getX(), this.pos.getY()+1);
        for (Case c : this.getCurrentCase().getNeighbours()) {
            if (c == null) { continue; }
            if (c.getCoord().equals(target)) {
                this.pos = target;
            }
        }
        return this.pos.equals(target);
    }

    /**
     * Return if player finish the labyrinth
     * @return true if the labyrinth finished, false else
     */
    public boolean isWin() {
        return this.pos.equals(laby.getEndCoord());
    }
}
