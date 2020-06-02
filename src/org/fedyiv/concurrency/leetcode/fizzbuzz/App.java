package org.fedyiv.concurrency.leetcode.fizzbuzz;






import org.fedyiv.concurrency.leetcode.fizzbuzz.semaphores.FizzBuzz;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.IntConsumer;

import static java.lang.Thread.sleep;

public class App {

    FizzBuzz fizzBuzz;
    CountDownLatch latch;

    public static void main(String[] args) {

        App app = new App();

        app.simulate();

    }

    public void simulate() {
        latch = new CountDownLatch(4);
        fizzBuzz = new FizzBuzz(15);

        ExecutorService executor = Executors.newFixedThreadPool(4);

        executor.submit(new FizzWorker());
        executor.submit(new BuzzWorker());
        executor.submit(new FizzBuzzWorker());
        executor.submit(new NumberWorker());

        executor.shutdown();

        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdownNow();

    }

    private  class FizzWorker implements Runnable {
        @Override
        public void run() {
            latch.countDown();
            try {
                latch.await();
                fizzBuzz.fizz(() ->{System.out.println("fizz");});
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    private  class BuzzWorker implements Runnable {
        @Override
        public void run() {
            latch.countDown();
            try {
                latch.await();
                fizzBuzz.buzz(() ->{System.out.println("buzz");});
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private  class FizzBuzzWorker implements Runnable {
        @Override
        public void run() {
            latch.countDown();
            try {
                latch.await();
                fizzBuzz.fizzbuzz(() ->{System.out.println("fizzbuzz");});
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private  class NumberWorker implements Runnable {
        @Override
        public void run() {
            latch.countDown();
            try {
                latch.await();
                fizzBuzz.number(new IntConsumer() {
                    @Override
                    public void accept(int i) {
                        System.out.println(i);
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
