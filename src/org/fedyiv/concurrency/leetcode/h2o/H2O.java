package org.fedyiv.concurrency.leetcode.h2o;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class H2O {

    private BlockingQueue<Runnable> hydrogenQueue = new LinkedBlockingQueue<>();
    private BlockingQueue<Runnable> oxygenQueue = new LinkedBlockingQueue<>();

    public H2O() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

        hydrogenQueue.put(releaseHydrogen);
        triggerReleaseAttempt();

    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {

        oxygenQueue.put(releaseOxygen);
        triggerReleaseAttempt();

    }

    public synchronized  void triggerReleaseAttempt() {
        if(hydrogenQueue.size() >=2 && oxygenQueue.size() >= 1 ) {
            Runnable h1 = hydrogenQueue.remove();
            Runnable h2 = hydrogenQueue.remove();
            Runnable o = oxygenQueue.remove();

            h1.run();
            h2.run();
            o.run();

        }

    }


}