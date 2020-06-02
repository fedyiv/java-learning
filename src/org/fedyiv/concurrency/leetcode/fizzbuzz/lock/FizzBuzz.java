package org.fedyiv.concurrency.leetcode.fizzbuzz.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

//This solution works fast only because fairness parameter of ReentrantLock
public class FizzBuzz {
    private int n;
    private volatile int currentNumber = 1;
    private Lock lock = new ReentrantLock(true);


    public FizzBuzz(int n) {
        this.n = n;

    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {

        while (currentNumber <= n) {
            lock.lock();
            try {
                if(currentNumber>n)
                    break;
                if (currentNumber % 3 == 0 && currentNumber % 5 != 0) {
                    printFizz.run();
                    currentNumber++;
                }
            } finally {
                lock.unlock();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        while (currentNumber < n) {
            lock.lock();
            try {
                if(currentNumber>n)
                    break;
                if (currentNumber % 5 == 0 && currentNumber % 3 != 0) {
                    printBuzz.run();
                    currentNumber++;
                }
            } finally {
                lock.unlock();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (currentNumber <= n) {
            lock.lock();
            try {
                if(currentNumber>n)
                    break;
                if (currentNumber % 5 == 0 && currentNumber % 3 == 0) {
                    printFizzBuzz.run();
                    currentNumber++;
                }
            } finally {
                lock.unlock();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        while (currentNumber <= n) {
            lock.lock();
            try {
                if(currentNumber>n)
                    break;
                if (! (currentNumber % 5 == 0 || currentNumber % 3 == 0)) {
                    printNumber.accept(currentNumber);
                    currentNumber++;
                }
            } finally {
                lock.unlock();
            }
        }

    }
}
