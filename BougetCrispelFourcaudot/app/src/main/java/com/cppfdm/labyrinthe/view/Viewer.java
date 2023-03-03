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

    public Viewer(Context context) {
        super(context);
    }
    public Viewer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public Viewer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public Viewer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void addDrawable(Drawable drawable) {
        drawables.add(drawable);
    }

    @Override
    public void paint(Canvas canvas, Paint paint) {
        canvas.drawColor(Color.BLACK);
        for (Drawable d: drawables) {
            d.paint(canvas, paint);
        }
    }

    @Override
    public Drawable getDrawableParent() {
        return null;
    }

    @Override
    public Drawable getDrawableRoot() {
        return this;
    }

    @Override
    public void setDrawableParent(Drawable drawable) {
    }
}
