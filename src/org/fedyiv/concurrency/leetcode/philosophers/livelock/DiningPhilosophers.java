package org.fedyiv.concurrency.leetcode.philosophers.livelock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/***
 This solution illustrates livelock
 */

public class DiningPhilosophers {


    private final static int NUMBER_OF_PHILOSOPHERS = 5;
    private final List<Lock> forksLocks = new ArrayList<>();


    public DiningPhilosophers() {
        for (int i = 0; i < 5; i++)
            forksLocks.add(new ReentrantLock(true));

    }

    private int getRightForkNumber(int philosopher) {
        return philosopher;
    }

    private int getLeftForkNumber(int philosopher) {
        if (philosopher == 0)
            return NUMBER_OF_PHILOSOPHERS - 1;
        else
            return philosopher - 1;
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {

        System.out.println("Philosopher " + philosopher + " want to eat");

        int leftFork = getLeftForkNumber(philosopher);
        int rightFork = getRightForkNumber(philosopher);
        //Deadlock prone solution, it is intentional, for deadlock research purpose

        Lock leftForkLock = forksLocks.get(leftFork);
        Lock rightForkLock = forksLocks.get(rightFork);


        while (true) {
            if (!leftForkLock.tryLock())
                continue;
            if (!rightForkLock.tryLock()) {
                leftForkLock.unlock();
                continue;
            }
            break;
        }


        pickLeftFork.run();
        pickRightFork.run();
        eat.run();
        putLeftFork.run();
        putRightFork.run();

        leftForkLock.unlock();
        rightForkLock.unlock();

        System.out.println("Philosopher " + philosopher + " starting to think");

    }
}