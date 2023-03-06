package com.cppfdm.labyrinthe.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;

import com.cppfdm.labyrinthe.R;
import com.cppfdm.labyrinthe.game.Case;
import com.cppfdm.labyrinthe.game.Coord;
import com.cppfdm.labyrinthe.game.Labyrinth;
import com.cppfdm.labyrinthe.view.core.AbstractDrawable;
import com.cppfdm.labyrinthe.view.core.Drawable;

/**
 * Test for the viewer
 */
public class LabyrinthViewer extends AbstractDrawable {
    public final static int scale = 64; // Scale of the tiles
    public int offsetX = 0;
    public int offsetY = 0;
    Labyrinth labyrinth;
    Bitmap mur;
    Bitmap ground;
    Viewer root; // The root viewer (Viewer class)

    /**
     * Constructor
     *
     * @param labyrinth the labyrinth
     */
    public LabyrinthViewer(Labyrinth labyrinth) {
        this.labyrinth = labyrinth;
    }

    /**
     * Set the parent and get the root for catch the bitmap
     *
     * @param drawable parent
     */
    @Override
    public void setDrawableParent(Drawable drawable) {
        super.setDrawableParent(drawable);

        root = (Viewer) getDrawableRoot();
        resized(scale);
    }

    public void resized(int scale) {
        Bitmap murNotSized = BitmapFactory.decodeResource(root.getContext().getResources(), R.drawable.mur0);
        mur = ViewerCommand.resizeBitmap(murNotSized, scale, scale);

        Bitmap groundNotSized = BitmapFactory.decodeResource(root.getContext().getResources(), R.drawable.ground);
        ground = ViewerCommand.resizeBitmap(groundNotSized, scale, scale);
    }


    /**
     * Draw the labyrinth
     *
     * @param canvas element for paint inside
     * @param paint can be useful
     */
    @Override
    public void paint(Canvas canvas, Paint paint) {
        for (int xSize = 0; xSize < labyrinth.getCOL(); xSize++) {
            for (int ySize = 0; ySize < labyrinth.getROW(); ySize++) {
                Case aCase = labyrinth.getCase(new Coord(xSize, ySize));
                if (aCase == null) {
                    canvas.drawBitmap(mur, xSize * scale + offsetX, ySize * scale + offsetY, paint);
                } else {
                    canvas.drawBitmap(ground, xSize * scale + offsetX, ySize * scale + offsetY, paint);
                }
            }
        }

    }
}
