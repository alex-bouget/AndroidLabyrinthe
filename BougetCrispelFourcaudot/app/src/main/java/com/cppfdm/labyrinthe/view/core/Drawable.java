package com.cppfdm.labyrinthe.view.core;

import android.graphics.Canvas;
import android.graphics.Paint;

public interface Drawable {
    void paint(Canvas canvas, Paint paint);
    Drawable getDrawableParent();
    Drawable getDrawableRoot();
    void  setDrawableParent(Drawable drawable);
}
