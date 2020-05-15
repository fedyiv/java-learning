package org.fedyiv.concurrency.leetcode.foobar.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class FooBar {
    private int n;

    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private volatile boolean fooTurn = true;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            lock.lock();
            try {
                while (!fooTurn)
                    condition.await();

                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();

                fooTurn = false;
                condition.signal();
            } finally {
                lock.unlock();
            }
        }


    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            lock.lock();
            try {
                while (fooTurn)
                    condition.await();


                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();

                fooTurn = true;
                condition.signal();

            } finally {
                lock.unlock();
            }
        }
    }
}