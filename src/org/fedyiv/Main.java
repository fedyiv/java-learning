package org.fedyiv;






import org.fedyiv.concurrency.leetcode.foobar.lock.FooBar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static final int N = 50;

    public static void main(String[] args) throws InterruptedException {
        // write your code here

        ExecutorService executor = Executors.newFixedThreadPool(5);

        FooBar fooBar =  new FooBar(N);

        executor.submit(new Foo(fooBar));
        executor.submit(new Bar(fooBar));

        executor.shutdown();
    }

    public static class Foo implements Runnable {

        FooBar fooBar;

        public Foo(FooBar fooBar) {
            this.fooBar = fooBar;

        }

        @Override
        public void run() {

            try {
                fooBar.foo(() -> {
                    System.out.println("foo");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static class Bar implements Runnable {

        FooBar fooBar;

        public Bar(FooBar fooBar) {
            this.fooBar = fooBar;

        }

        @Override
        public void run() {

            try {
                fooBar.bar(() -> {
                    System.out.println("bar");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
