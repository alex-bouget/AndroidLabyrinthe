package com.cppfdm.labyrinthe.view.core;

import android.content.Context;
import android.graphics.Canvas;
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
            draw(canvas);
            holder.unlockCanvasAndPost(canvas);
        }
    }
}
