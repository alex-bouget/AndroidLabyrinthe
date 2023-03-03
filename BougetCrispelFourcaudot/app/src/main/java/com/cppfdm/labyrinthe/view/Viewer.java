package com.cppfdm.labyrinthe.view;

import android.content.Context;
import android.graphics.Canvas;

import com.cppfdm.labyrinthe.view.core.AbstractViewer;
import com.cppfdm.labyrinthe.view.core.Drawable;

import java.util.ArrayList;

public class Viewer extends AbstractViewer {
    ArrayList<Drawable> drawables = new ArrayList<>();

    public Viewer(Context context) {
        super(context);
    }

    @Override
    public void paint(Canvas canvas) {
        for (Drawable d: drawables) {
            d.paint(canvas);
        }
    }
}
