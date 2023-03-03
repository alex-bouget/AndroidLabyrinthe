package com.cppfdm.labyrinthe.view.core;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public abstract class AbstractViewer extends SurfaceView implements CoreRunnerCallback, Drawable {
    private CoreRunner core;
    private boolean isRun = false;

    public AbstractViewer(Context context) {
        super(context);
        core = new CoreRunner(this);
    }
    public AbstractViewer(Context context, AttributeSet attrs) {
        super(context, attrs);
        core = new CoreRunner(this);
    }
    public AbstractViewer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        core = new CoreRunner(this);
    }
    public AbstractViewer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        core = new CoreRunner(this);
    }

    public void start() {
        core.startCore();
    }

    public void stop() {
        core.stopCore();
    }

    public void resume() {
        core.resumeCore();
    }

    public void pause() {
        core.pauseCore();
    }

    @Override
    public void coreRunnerCallback() {
        SurfaceHolder holder = getHolder();
        Canvas canvas = holder.lockCanvas();
        if (canvas != null) {
            Paint paint = new Paint();
            this.paint(canvas, paint);
            holder.unlockCanvasAndPost(canvas);
        }
    }
}
