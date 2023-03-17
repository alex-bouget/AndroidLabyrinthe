package com.cppfdm.labyrinthe.view.core;

public abstract class AbstractDrawable implements Drawable {
    Drawable parent;

    /**
     * Get the parent of the element
     *
     * @return Drawable parent
     */
    @Override
    public Drawable getDrawableParent() {
        return parent;
    }

    /**
     * Get the viewer
     *
     * @return Drawable viewer
     */
    @Override
    public Drawable getDrawableRoot() {
        return parent.getDrawableRoot();
    }

    /**
     * set the parent of the element
     *
     * @param drawable parent
     */
    @Override
    public void setDrawableParent(Drawable drawable) {
        parent = drawable;

    }
}
