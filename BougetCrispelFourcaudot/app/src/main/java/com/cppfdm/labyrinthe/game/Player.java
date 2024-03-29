package com.cppfdm.labyrinthe.game;

public class Player {

    Labyrinth laby;
    Coordinate pos;

    /**
     * Constructor of player
     *
     * @param laby_ the laby where the player will play
     */
    public Player(Labyrinth laby_) {
        this.laby = laby_;
        this.pos = laby_.getStartCoordinate();
    }

    /**
     * Get the case object where the player is
     *
     * @return the Case
     */
    public Case getCurrentCase() {
        return this.laby.getCase(this.pos);
    }

    /**
     * Getter of laby
     *
     * @return the Labyrinth object
     */
    public Labyrinth getLaby() {
        return this.laby;
    }

    /**
     * Make the player move left
     *
     * @return if the movement has been done
     */
    public boolean moveLeft() {
        Coordinate target = new Coordinate(this.pos.getX() - 1, this.pos.getY());
        for (Case current : this.getCurrentCase().getNeighbours()) {
            if (current == null) {
                continue;
            }
            if (current.getCoordinate().equals(target)) {
                this.pos = target;
            }
        }
        return this.pos.equals(target);
    }

    /**
     * Make the player move right
     *
     * @return if the movement has been done
     */
    public boolean moveRight() {
        Coordinate target = new Coordinate(this.pos.getX() + 1, this.pos.getY());
        for (Case current : this.getCurrentCase().getNeighbours()) {
            if (current == null) {
                continue;
            }
            if (current.getCoordinate().equals(target)) {
                this.pos = target;
            }
        }
        return this.pos.equals(target);
    }

    /**
     * make the player move up
     *
     * @return if the movement has been done
     */
    public boolean moveUp() {
        Coordinate target = new Coordinate(this.pos.getX(), this.pos.getY() - 1);
        for (Case current : this.getCurrentCase().getNeighbours()) {
            if (current == null) {
                continue;
            }
            if (current.getCoordinate().equals(target)) {
                this.pos = target;
            }
        }
        return this.pos.equals(target);
    }

    /**
     * make the player move down
     *
     * @return if the movement has been done
     */
    public boolean moveDown() {
        Coordinate target = new Coordinate(this.pos.getX(), this.pos.getY() + 1);
        for (Case current : this.getCurrentCase().getNeighbours()) {
            if (current == null) {
                continue;
            }
            if (current.getCoordinate().equals(target)) {
                this.pos = target;
            }
        }
        return this.pos.equals(target);
    }

    /**
     * Return if player finish the labyrinth
     *
     * @return true if the labyrinth finished, false else
     */
    public boolean isWin() {
        return this.pos.equals(laby.getEndCoordinate());
    }

    /**
     * Return if the player collide with one of the enemy
     *
     * @return true if the is a collision, false else
     */
    public boolean isDead() {
        for (Enemy enemy : this.laby.getEnemies()) {
            if (this.pos.equals(enemy.getPos())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Reset the player and enemies position
     */
    public void reset() {
        pos = laby.getStartCoordinate();
        laby.resetEnemyCoordinates();
    }
}
