package com.cppfdm.labyrinthe.view.core;

class CoreRunner extends Thread {
    private final static long delay = 100; // ms
    private boolean isCoreRunning = true;
    private boolean isCorePausing = true;
    CoreRunnerCallback callback;

    public CoreRunner(CoreRunnerCallback callback) {
        super();
        this.callback = callback;
    }

    public void stopCore() {
        isCoreRunning = false;
        try {
            this.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void startCore() {
        stopCore();
        isCoreRunning = true;
        isCorePausing = false;
        this.start();
    }

    public void pauseCore() {
        if (this.isAlive()) {
            isCorePausing = true;
        }
    }

    public void resumeCore() {
        if (this.isAlive()) {
            isCorePausing = false;
        }
    }

    @Override
    public void run() {
        long lastUpdatedTime = 0;
        while (isCoreRunning) {
            if (!isCorePausing) {
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