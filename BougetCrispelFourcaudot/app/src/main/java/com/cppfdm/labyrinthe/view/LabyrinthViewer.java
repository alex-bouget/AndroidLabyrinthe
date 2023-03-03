package com.cppfdm.labyrinthe.view;

import android.graphics.Canvas;

import com.cppfdm.labyrinthe.game.Labyrinth;
import com.cppfdm.labyrinthe.view.core.Drawable;

public class LabyrinthViewer implements Drawable {
    Labyrinth labyrinth;

    public LabyrinthViewer(Labyrinth labyrinth) {
        this.labyrinth = labyrinth;
    }

    @Override
    public void paint(Canvas canvas) {

    }
}
