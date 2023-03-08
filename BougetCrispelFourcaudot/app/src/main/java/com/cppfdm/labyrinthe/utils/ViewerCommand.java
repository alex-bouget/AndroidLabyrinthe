package com.cppfdm.labyrinthe.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

import java.io.IOException;
import java.io.InputStream;

public class ViewerCommand {
    /**
     * Resize a bitmap
     *
     * @param toResize the Bitmap
     * @param sizeX the sizeX of the resized bitmap
     * @param sizeY the sizeY of the resized bitmap
     * @return Bitmap
     */
    public static Bitmap resizeBitmap(Bitmap toResize, int sizeX, int sizeY) {
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

        // Filter the last bitmap (delete the pixel outside the canvas

        return Bitmap.createBitmap(resized, 0, 0, sizeX, sizeY, matrix, true);
    }

    /**
     * Resize a bitmap
     *
     * @param toResize the Bitmap
     * @param width the width of the resized bitmap
     * @return
     */
    public static Bitmap resizeBitmapWidth(Bitmap toResize, int width) {
        float bitWidth = toResize.getWidth();
        float bitHeight = toResize.getHeight();
        //  bitHeight  bitWidth
        //   X         width
        return resizeBitmap(toResize, width, (int)((bitHeight*width)/bitWidth));
    }

    /**
     * Get a bitmap from drawable
     *
     * @param v the view
     * @param id id of the drawable
     * @return bitmap
     */
    public static Bitmap getBitmap(View v, int id) {
        return BitmapFactory.decodeResource(v.getContext().getResources(), id);
    }

    /**
     * Get a bitmap from assets
     *
     * @param v the view
     * @param path path to the asset
     * @return bitmap
     */
    public static Bitmap getBitmap(View v, String path) {
        InputStream is;
        try {
            is = v.getResources().getAssets().open(path);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return BitmapFactory.decodeStream(is);
    }

}
