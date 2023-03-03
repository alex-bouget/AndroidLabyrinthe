package com.cppfdm.labyrinthe.view.core;

import android.graphics.Canvas;

public abstract class AbstractDrawable implements Drawable {
    Drawable parent;
    @Override
    public Drawable getDrawableParent() {
        return parent;
    }

    @Override
    public Drawable getDrawableRoot() {
        return parent.getDrawableRoot();
    }

    @Override
    public void setDrawableParent(Drawable drawable) {
        parent = drawable;

    }
}
