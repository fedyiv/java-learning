package org.fedyiv.concurrency.leetcode.philosophers.simplest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/***
 * This is the simplest solution which allows to eat only one philosopher simulteneously.
 * For this purpose the whole table is used as a lock object. It is very simple, but it is not very effective, as it is clearly seen that both philosophers should be able to eat at the same time.
 */

public class DiningPhilosophers {

    private Lock tableLock = new ReentrantLock(true);

    public DiningPhilosophers() {

    }


    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {

        tableLock.lock();

        pickLeftFork.run();
        pickRightFork.run();
        eat.run();
        putLeftFork.run();
        putRightFork.run();

        tableLock.unlock();



    }
}