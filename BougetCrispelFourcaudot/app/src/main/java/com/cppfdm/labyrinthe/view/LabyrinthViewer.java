package com.cppfdm.labyrinthe.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.cppfdm.labyrinthe.R;
import com.cppfdm.labyrinthe.game.Labyrinth;
import com.cppfdm.labyrinthe.view.core.AbstractDrawable;
import com.cppfdm.labyrinthe.view.core.Drawable;

public class LabyrinthViewer extends AbstractDrawable {
    public final static int scale = 16;
    Labyrinth labyrinth;
    Bitmap mur;
    Bitmap ground;
    Viewer root;

    public LabyrinthViewer(Labyrinth labyrinth) {
        this.labyrinth = labyrinth;
        root = (Viewer) getDrawableRoot();
        mur = BitmapFactory.decodeResource(root.getContext().getResources(), R.drawable.mur0);
        ground = BitmapFactory.decodeResource(root.getContext().getResources(), R.drawable.ground);
    }

    @Override
    public void paint(Canvas canvas, Paint paint) {
        for (int xSize=0; xSize<labyrinth.getCOL(); xSize++) {
                for (int ySize=0; ySize<labyrinth.getROW(); ySize++) {
                    Matrix matrix = new Matrix();
                    matrix.setScale(scale, scale);
                    matrix.setTranslate(xSize*scale, ySize*scale);
                    canvas.drawBitmap(mur, matrix, paint);
            }
        }

    }
}
