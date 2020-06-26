package org.fedyiv.concurrency.leetcode.cache;

import org.fedyiv.concurrency.leetcode.cache.impl.ConcurrentCacheWithOneWriterReadWriteLock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class ConcurrentCacheWithOneWriterTest {

    public volatile boolean testIsActive = true;

    private AtomicInteger correctReadsCounter = new AtomicInteger();
    private AtomicInteger incorrectReadsCounter = new AtomicInteger();


    public class Producer implements Runnable {

        ConcurrentCacheWithOneWriter<Integer, Integer> cache;
        Queue<Map.Entry<Integer, Integer>> queue;
        Integer key = 1;


        public Producer(ConcurrentCacheWithOneWriter<Integer, Integer> cache, Queue<Map.Entry<Integer, Integer>> queue) {
            this.cache = cache;
            this.queue = queue;
        }

        Random rand = new Random();

        @Override
        public void run() {
            try {
                System.out.println("Producer: Starting");
                while (testIsActive) {


                    Integer value = rand.nextInt();

                    //  System.out.println("Producer: Generated entry (key,value) = (" + key + ", " + value + ")");

                    cache.put(key, value);
                    queue.add(new AbstractMap.SimpleEntry<>(key, value));
                    key++;

                    //   System.out.println("Producer: queue size = " + queue.size());

                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }


    public class Consumer implements Runnable {

        ConcurrentCacheWithOneWriter<Integer, Integer> cache;
        Queue<Map.Entry<Integer, Integer>> queue;

        public Consumer(ConcurrentCacheWithOneWriter<Integer, Integer> cache, Queue<Map.Entry<Integer, Integer>> queue) {
            this.cache = cache;
            this.queue = queue;
        }

        @Override
        public void run() {

         //   System.out.println("Consumer: Starting");
            Map.Entry<Integer, Integer> queueEntry = null;
            try {
                while (testIsActive) {
                    //    System.out.println("Consumer: queue size = " + queue.size());
                     //  System.out.println("Consumer: Polling queue");
                    queueEntry = queue.poll();

                    if (queueEntry == null)
                        continue;

                    //System.out.println("Consumer: Extracted entry (key,value) = (" + queueEntry.getKey() + ", " + queueEntry.getValue() + ")");


                    Integer cacheValueInteger = cache.get(queueEntry.getKey());

                    if(cacheValueInteger == null) {
                        System.out.println("Incorrect read: key = " + queueEntry.getKey()
                                + " queue value = " + queueEntry.getValue() + " cached value = null" );
                        incorrectReadsCounter.incrementAndGet();
                        continue;
                    }

                    int cacheValue = cacheValueInteger;


                    if (queueEntry.getValue() == cacheValue)
                        correctReadsCounter.incrementAndGet();
                    else {
                        System.out.println("Incorrect read: key = " + queueEntry.getKey()
                                + " queue value = " + queueEntry.getValue()
                                + "cache value = " + cacheValue != null ? cacheValue : "null");
                        incorrectReadsCounter.incrementAndGet();
                    }


                    //      System.out.println("Consumer: Test succesfull");

                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            } catch (Exception e) {
                System.out.println("Consumer: Extracted entry (key,value) = (" + queueEntry.getKey() + ", " + queueEntry.getValue() + ")");
                e.printStackTrace();
                incorrectReadsCounter.incrementAndGet();
            }

        }
    }


    @Test
    public void testKeyAvailableImmediatelyAfterPut() throws InterruptedException {

        Queue queue = new ConcurrentLinkedQueue<Map.Entry<Integer, Integer>>();
        ConcurrentCacheWithOneWriter<Integer, Integer> cache = new ConcurrentCacheWithOneWriterReadWriteLock<>();

        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.submit(new Consumer(cache, queue));
        executorService.submit(new Producer(cache, queue));
        executorService.submit(new Consumer(cache, queue));
        executorService.submit(new Consumer(cache, queue));
        executorService.submit(new Consumer(cache, queue));


        Thread.sleep(10000);
        testIsActive = false;

        executorService.shutdown();

        executorService.awaitTermination(60, TimeUnit.SECONDS);


        System.out.println("Incorrect reads: " + incorrectReadsCounter.get() + ", correct reads = " + correctReadsCounter.get());

        Assertions.assertEquals(0, incorrectReadsCounter.get());

    }

    //    @Test
    public void testAllReaderThreadsSeeTheSameValue() {

    }

}