package com.cppfdm.labyrinthe.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.cppfdm.labyrinthe.view.core.AbstractViewer;
import com.cppfdm.labyrinthe.view.core.Drawable;

import java.util.ArrayList;

public class Viewer extends AbstractViewer {
    ArrayList<Drawable> drawables = new ArrayList<>();

    /**
     * Constructor
     *
     * @param context the context
     */
    public Viewer(Context context) {
        super(context);
    }

    /**
     * Constructor
     *
     * @param context the context
     * @param attrs   attributes
     */
    public Viewer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor
     *
     * @param context      the context
     * @param attrs        attributes
     * @param defStyleAttr styles attributes
     */
    public Viewer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Constructor
     *
     * @param context      the context
     * @param attrs        attributes
     * @param defStyleAttr styles attributes
     * @param defStyleRes  styles resources
     */
    public Viewer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * Add drawable
     *
     * @param drawable drawable to add
     */
    public void addDrawable(Drawable drawable) {
        drawables.add(drawable);
        drawable.setDrawableParent(this);
    }

    /**
     * paint all drawable child
     *
     * @param canvas element for paint inside
     * @param paint  can be useful
     */
    @Override
    public void paint(Canvas canvas, Paint paint) {
        canvas.drawColor(Color.BLACK);
        for (Drawable d : drawables) {
            d.paint(canvas, paint);
        }
    }

    /**
     * Get the parent of the element
     *
     * @return Drawable parent
     */
    @Override
    public Drawable getDrawableParent() {
        return null;
    }

    /**
     * Get the viewer
     *
     * @return Drawable viewer
     */
    @Override
    public Drawable getDrawableRoot() {
        return this;
    }

    /**
     * set the parent of the element
     *
     * @param drawable parent
     */
    @Override
    public void setDrawableParent(Drawable drawable) {
    }
}
