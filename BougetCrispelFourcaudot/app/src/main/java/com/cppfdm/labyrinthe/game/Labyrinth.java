package com.cppfdm.labyrinthe.game;

import java.util.ArrayList;

public class Labyrinth {

    final int ROW;
    final int COL;
    Case[][] cases;


    public Labyrinth(int row_, int col_, Coord start_, Coord end_, ArrayList<Coord> cases_) {
        this.ROW = row_;
        this.COL = col_;
        this.cases = new Case[row_][col_];
        for (Coord c : cases_) {

        }
    }
}
