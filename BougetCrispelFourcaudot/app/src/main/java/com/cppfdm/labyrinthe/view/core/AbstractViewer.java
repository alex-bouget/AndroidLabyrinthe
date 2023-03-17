package com.cppfdm.labyrinthe.view.core;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public abstract class AbstractViewer extends SurfaceView implements CoreRunnerCallback, Drawable {
    private final CoreRunner core = new CoreRunner(this);

    /**
     * Constructor
     *
     * @param context the context
     */
    public AbstractViewer(Context context) {
        super(context);
    }

    /**
     * Constructor
     *
     * @param context the context
     * @param attrs   attributes
     */
    public AbstractViewer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Constructor
     *
     * @param context      the context
     * @param attrs        attributes
     * @param defStyleAttr styles attributes
     */
    public AbstractViewer(Context context, AttributeSet attrs, int defStyleAttr) {
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
    public AbstractViewer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * Start the viewer
     */
    public void start() {
        core.startCore();
    }

    /**
     * Stop the viewer
     */
    public void stop() {
        core.stopCore();
    }

    /**
     * Resume the viewer
     */
    public void resume() {
        core.resumeCore();
    }

    /**
     * Pause the viewer
     */
    public void pause() {
        core.pauseCore();
    }

    /**
     * Callback of the coreRunner
     */
    @Override
    public void coreRunnerCallback() {
        SurfaceHolder holder = getHolder();
        Canvas canvas = holder.lockCanvas(); // Get the canvas
        if (canvas != null) {
            Paint paint = new Paint();
            this.paint(canvas, paint); // Draw the viewer
            holder.unlockCanvasAndPost(canvas); // Load the canvas
        }
    }
}
