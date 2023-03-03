package com.cppfdm.labyrinthe.view;

import android.graphics.Canvas;

import com.cppfdm.labyrinthe.R;
import com.cppfdm.labyrinthe.game.Labyrinth;
import com.cppfdm.labyrinthe.view.core.Drawable;

public class LabyrinthViewer implements Drawable {
    Labyrinth labyrinth;
    int murId = R.drawable.mur0;
    int groundID = R.drawable.ground;

    public LabyrinthViewer(Labyrinth labyrinth) {
        this.labyrinth = labyrinth;
    }

    @Override
    public void paint(Canvas canvas) {
        

    }
}
