package com.cppfdm.labyrinthe.view.core;

/**
 * CodeRunner. Thread who decide when redraw
 */
class CoreRunner extends Thread {
    private final static long delay = 100; // ms
    private boolean isCoreRunning = true;
    private boolean isCorePausing = true;
    CoreRunnerCallback callback;

    /**
     * Constructor
     * @param callback CoreRunnerCallback
     */
    public CoreRunner(CoreRunnerCallback callback) {
        super();
        this.callback = callback;
    }

    /**
     * Stop the core if it run
     */
    public void stopCore() {
        isCoreRunning = false;
        try {
            this.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * start the core
     */
    public void startCore() {
        stopCore();
        isCoreRunning = true;
        isCorePausing = false;
        this.start();
    }

    /**
     * pause the core. Continue to run but never redraw
     */
    public void pauseCore() {
        if (this.isAlive()) {
            isCorePausing = true;
        }
    }

    /**
     * resume the core
     */
    public void resumeCore() {
        if (this.isAlive()) {
            isCorePausing = false;
        }
    }

    /**
     * Thread runner
     */
    @Override
    public void run() {
        long lastUpdatedTime = 0;
        while (isCoreRunning) {
            if (isCorePausing) {
                try {
                    sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            long currentTime = System.currentTimeMillis();
            if (currentTime > lastUpdatedTime + delay) {
                lastUpdatedTime = currentTime;
                callback.coreRunnerCallback();
            }

        }
    }
}