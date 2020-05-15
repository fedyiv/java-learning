package org.fedyiv.concurrency.leetcode.philosophers.current;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

/***
 This good solution on Reentrant Locks
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



        int leftFork = getLeftForkNumber(philosopher);
        int rightFork = getRightForkNumber(philosopher);

    //    System.out.println("Philosopher " + philosopher + " want to eat with forks " + leftFork + " and " + rightFork);

        Lock leftForkLock = forksLocks.get(leftFork);
        Lock rightForkLock = forksLocks.get(rightFork);

        Random rand = new Random();

        while (true) {
            try {
                sleep(1L + rand.nextInt(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!leftForkLock.tryLock()) {
         //       System.out.println("Philosopher " + philosopher + " was not able acquire fork " + leftFork);
                continue;
            }
        //    System.out.println("Philosopher " + philosopher + " was able acquire fork " + leftFork);

            if (!rightForkLock.tryLock()) {
          //      System.out.println("Philosopher " + philosopher + " was not able acquire fork " + rightFork);
                leftForkLock.unlock();
                continue;
            }
      //      System.out.println("Philosopher " + philosopher + " was able acquire fork " + rightFork);
            break;
        }

     //   System.out.println("Philosopher " + philosopher + "  acquired forks " + leftFork + " and " + rightFork);

        pickLeftFork.run();
        pickRightFork.run();
        eat.run();
        putLeftFork.run();
        putRightFork.run();

        leftForkLock.unlock();
        rightForkLock.unlock();

     //   System.out.println("Philosopher " + philosopher + "  released forks " + leftFork + " and " + rightFork);

    //    System.out.println("Philosopher " + philosopher + " starting to think");

    }
}