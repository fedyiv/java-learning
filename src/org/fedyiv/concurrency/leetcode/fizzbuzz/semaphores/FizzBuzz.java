package org.fedyiv.concurrency.leetcode.fizzbuzz.semaphores;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class FizzBuzz {
    private int n;
    private volatile int currentNumber = 1;
    private Semaphore fizzSemaphore = new Semaphore(1);
    private Semaphore buzzSemaphore = new Semaphore(1);
    private Semaphore fizzbuzzSemaphore = new Semaphore(1);
    private Semaphore numberSemaphore = new Semaphore(1);

    private synchronized void signalToNextThread() {

        System.out.println("Semaphores state before signal: fizz = " + fizzbuzzSemaphore.availablePermits()
                + " buzz = " + buzzSemaphore.availablePermits()
        + " fizbuzz = " + fizzbuzzSemaphore.availablePermits()
                + " number = " + numberSemaphore.availablePermits());

        if (currentNumber % 5 == 0 && currentNumber % 3 == 0) {
            fizzbuzzSemaphore.release();
        } else if (currentNumber % 3 == 0) {
            fizzSemaphore.release();
        } else if (currentNumber % 5 == 0) {
            buzzSemaphore.release();
        } else {
            numberSemaphore.release();
        }

        System.out.println("Semaphores state after signal: fizz = " + fizzbuzzSemaphore.availablePermits()
                + " buzz = " + buzzSemaphore.availablePermits()
                + " fizbuzz = " + fizzbuzzSemaphore.availablePermits()
                + " number = " + numberSemaphore.availablePermits());

    }

    private synchronized void releaseAllSemaphores() {
        if (fizzSemaphore.availablePermits() == 0)
            fizzSemaphore.release();
        if (buzzSemaphore.availablePermits() == 0)
            buzzSemaphore.release();
        if (fizzbuzzSemaphore.availablePermits() == 0)
            fizzbuzzSemaphore.release();
        if (numberSemaphore.availablePermits() == 0)
            numberSemaphore.release();
    }

    public FizzBuzz(int n) {
        this.n = n;
        try {
            fizzSemaphore.acquire();
            buzzSemaphore.acquire();
            fizzbuzzSemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        System.out.println("fizz::Entering, currentNumber = " + currentNumber);

        while (currentNumber <= n) {
            System.out.println("fizz::Entering while, currentNumber = " + currentNumber);
            fizzSemaphore.acquire();
            if (currentNumber <= n) {
                System.out.println("fizz::going to print, currentNumber = " + currentNumber);
                printFizz.run();
                currentNumber++;
                signalToNextThread();
            }

        }
        System.out.println("fizz::before releaseing all semaphores");
        releaseAllSemaphores();
    }


    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        System.out.println("buzz::Entering, currentNumber = " + currentNumber);
        while (currentNumber <= n) {
            System.out.println("buzz::Entering while, currentNumber = " + currentNumber);
            buzzSemaphore.acquire();
            if (currentNumber <= n) {
                System.out.println("buzz::going to print, currentNumber = " + currentNumber);
                printBuzz.run();
                currentNumber++;
                signalToNextThread();
            }

        }
        System.out.println("buzz::before releaseing all semaphores");
        releaseAllSemaphores();
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        System.out.println("fizzbuzz::Entering, currentNumber = " + currentNumber);
        while (currentNumber <= n) {
            System.out.println("fizzbuzz::Entering while, currentNumber = " + currentNumber);
            fizzbuzzSemaphore.acquire();
            if (currentNumber <= n) {
                System.out.println("fizzbuzz::going to print, currentNumber = " + currentNumber);
                printFizzBuzz.run();
                currentNumber++;
                signalToNextThread();
            }

        }
        System.out.println("fizzbuzz::before releaseing all semaphores");
        releaseAllSemaphores();
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        System.out.println("number::Entering, currentNumber = " + currentNumber);
        while (currentNumber <= n) {
            System.out.println("number::Entering while, currentNumber = " + currentNumber);
            numberSemaphore.acquire();
            if (currentNumber <= n) {
                System.out.println("number::going to print, currentNumber = " + currentNumber);
                printNumber.accept(currentNumber);
                currentNumber++;
                signalToNextThread();
            }

        }
        System.out.println("number::before releaseing all semaphores");
        releaseAllSemaphores();

    }
}
