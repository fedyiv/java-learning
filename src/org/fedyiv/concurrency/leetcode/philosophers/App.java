package org.fedyiv.concurrency.leetcode.philosophers;

import org.fedyiv.concurrency.leetcode.philosophers.current.DiningPhilosophers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class App {

    public static void main(String[] args) throws InterruptedException {
        // write your code here

        DiningPhilosophers diningPhilosophers = new DiningPhilosophers();

        ExecutorService executor = Executors.newFixedThreadPool(5);


        for (int i = 0; i < 5; i++) {
            executor.submit(new Philosopher(diningPhilosophers, i,2));
        }


        executor.shutdown();


    }
}
