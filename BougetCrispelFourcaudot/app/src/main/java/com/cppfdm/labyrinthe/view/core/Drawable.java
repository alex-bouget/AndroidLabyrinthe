package com.cppfdm.labyrinthe.view.core;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Interface for all element than can be draw by the viewer
 */
public interface Drawable {
    /**
     * Paint the element
     *
     * @param canvas element for paint inside
     * @param paint can be useful
     */
    void paint(Canvas canvas, Paint paint);

    /**
     * Get the parent of the element
     * @return Drawable parent
     */
    Drawable getDrawableParent();

    /**
     * Get the viewer
     * @return Drawable viewer
     */
    Drawable getDrawableRoot();

    /**
     * set the parent of the element
     * @param drawable parent
     */
    void  setDrawableParent(Drawable drawable);
}
