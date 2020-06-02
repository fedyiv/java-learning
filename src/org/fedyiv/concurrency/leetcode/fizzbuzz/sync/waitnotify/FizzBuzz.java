package org.fedyiv.concurrency.leetcode.fizzbuzz.sync.waitnotify;

import java.util.function.IntConsumer;

//This solution works slowly because of thread competing for the lock, while in the reality only one thread is supposed to proceed
public class FizzBuzz {
    private int n;
    private volatile int currentNumber = 1;

    public FizzBuzz(int n) {
        this.n = n;

    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        System.out.println("fizz::Entering, currentNumber = " + currentNumber);

        while (currentNumber <= n) {
            System.out.println("fizz::Entering while, currentNumber = " + currentNumber);
            synchronized (this) {
                System.out.println("fizz::In synchronized block, currentNumber = " + currentNumber);
                if (currentNumber > n)
                    break;
                if (currentNumber % 3 == 0 && currentNumber % 5 != 0) {
                    System.out.println("fizz::going to print, currentNumber = " + currentNumber);
                    printFizz.run();
                    currentNumber++;
                    System.out.println("fizz::before notifyAll, currentNumber = " + currentNumber);
                    notify();
                } else {
                    System.out.println("fizz::waiting, currentNumber = " + currentNumber);
                    wait(100);
                    System.out.println("fizz::waited, currentNumber = " + currentNumber);
                }
            }

        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        System.out.println("buzz::Entering, currentNumber = " + currentNumber);
        while (currentNumber < n) {
            System.out.println("buzz::Entering while, currentNumber = " + currentNumber);
            synchronized (this) {
                System.out.println("buzz::In synchronized block, currentNumber = " + currentNumber);
                if (currentNumber > n)
                    break;
                if (currentNumber % 5 == 0 && currentNumber % 3 != 0) {
                    System.out.println("buzz::going to print, currentNumber = " + currentNumber);
                    printBuzz.run();
                    currentNumber++;
                    System.out.println("buzz::before notifyAll, currentNumber = " + currentNumber);
                    notify();
                } else {
                    System.out.println("buzz::waiting, currentNumber = " + currentNumber);
                    wait(100);
                    System.out.println("buzz::waited, currentNumber = " + currentNumber);
                }
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        System.out.println("fizzbuzz::Entering, currentNumber = " + currentNumber);
        while (currentNumber <= n) {
            System.out.println("fizzbuzz::Entering while, currentNumber = " + currentNumber);
            synchronized (this) {
                System.out.println("fizzbuzz::In synchronized block, currentNumber = " + currentNumber);
                if (currentNumber > n)
                    break;
                if (currentNumber % 5 == 0 && currentNumber % 3 == 0) {
                    System.out.println("fizzbuzz::going to print, currentNumber = " + currentNumber);
                    printFizzBuzz.run();
                    currentNumber++;
                    System.out.println("fizzbuzz::before notifyAll, currentNumber = " + currentNumber);
                    notify();
                } else {
                    System.out.println("fizzbuzz::waiting, currentNumber = " + currentNumber);
                    wait(100);
                    System.out.println("fizzbuzz::waited, currentNumber = " + currentNumber);
                }
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        System.out.println("number::Entering, currentNumber = " + currentNumber);
        while (currentNumber <= n) {
            System.out.println("number::Entering while, currentNumber = " + currentNumber);
            synchronized (this) {
                System.out.println("number::In synchronized block, currentNumber = " + currentNumber);
                if (currentNumber > n)
                    break;
                if (!(currentNumber % 5 == 0 || currentNumber % 3 == 0)) {
                    System.out.println("number::going to print, currentNumber = " + currentNumber);
                    printNumber.accept(currentNumber);
                    currentNumber++;
                    System.out.println("number::before notifyAll, currentNumber = " + currentNumber);
                    notify();
                } else {
                    System.out.println("number::waiting, currentNumber = " + currentNumber);
                    wait(100);
                    System.out.println("number::waited, currentNumber = " + currentNumber);
                }
            }
        }

    }
}
