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
        Bitmap murNotSized = BitmapFactory.decodeResource(root.getContext().getResources(), R.drawable.mur0);
        mur = resizeBitmap(murNotSized, scale, scale);

        Bitmap groundNotSized = BitmapFactory.decodeResource(root.getContext().getResources(), R.drawable.ground);
        ground = resizeBitmap(groundNotSized, scale, scale);
    }

    /**
     * Resize a bitmap
     *
     * @param toResize the Bitmap
     * @param sizeX the sizeX of the resized bitmap
     * @param sizeY the sizeY of the resized bitmap
     * @return
     */
    Bitmap resizeBitmap(Bitmap toResize, int sizeX, int sizeY) {
        // https://www.okhelp.cz/android/resize-a-bitmap-image-android-example/
        Paint paint = new Paint();
        Bitmap resized = Bitmap.createBitmap(sizeX, sizeY, Bitmap.Config.ARGB_8888); // Create a new bitmap

        RectF rectf = new RectF(0, 0, sizeX, sizeY);  // A rectangle that is the new size

        Canvas canvas = new Canvas(resized); // Canvas on the resized bitmap

        Path path = new Path();
        path.addRect(rectf, Path.Direction.CW);
        canvas.clipPath(path); // Lock the canvas with the size of the rectangle

        canvas.drawBitmap(toResize, new Rect(0, 0, toResize.getWidth(), toResize.getHeight()), new Rect(0, 0, sizeX, sizeY), paint);
        // Draw the not resized on the resized bitmap

        Matrix matrix = new Matrix();
        matrix.postScale(1f, 1f);

        Bitmap resizedBitmap = Bitmap.createBitmap(resized, 0, 0, sizeX, sizeY, matrix, true);
        // Filter the last bitmap (delete the pixel outside the canvas

        return resizedBitmap;
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
