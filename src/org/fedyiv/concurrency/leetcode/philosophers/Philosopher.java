package org.fedyiv.concurrency.leetcode.philosophers;

import org.fedyiv.concurrency.leetcode.philosophers.current.DiningPhilosophers;

public class Philosopher implements Runnable {

    DiningPhilosophers diningPhilosophers;
    int currentPhilosopher;
    int nEatings;

    public Philosopher(DiningPhilosophers diningPhilosophers, int currentPhilosopher, int nEatings) {
        this.diningPhilosophers = diningPhilosophers;
        this.currentPhilosopher = currentPhilosopher;
        this.nEatings = nEatings;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < nEatings; i++)
                diningPhilosophers.wantsToEat(currentPhilosopher,
                        () -> {
                            System.out.println("Philosopher " + currentPhilosopher + " Picking Left Fork");

                        },
                        () -> {
                            System.out.println("Philosopher " + currentPhilosopher + " Picking Right Fork");
                        },
                        () -> {
                            System.out.println("Philosopher " + currentPhilosopher + " Eating");

                        },
                        () -> {
                            System.out.println("Philosopher " + currentPhilosopher + " Putting Left Fork");

                        },
                        () -> {
                            System.out.println("Philosopher " + currentPhilosopher + " Putting Right Fork");

                        }
                );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
