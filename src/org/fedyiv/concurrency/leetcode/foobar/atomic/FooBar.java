package org.fedyiv.concurrency.leetcode.foobar.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

public class FooBar {
    private int n;

    AtomicBoolean fooTurn = new AtomicBoolean(true);

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            while (!fooTurn.get())
                Thread.sleep(0);


            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();

            fooTurn.set(false);

        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            while (fooTurn.get())
                Thread.sleep(0);

            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();

            fooTurn.set(true);


        }
    }
}