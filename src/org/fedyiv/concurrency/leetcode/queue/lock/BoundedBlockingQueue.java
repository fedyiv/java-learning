package org.fedyiv.concurrency.leetcode.queue.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBlockingQueue {

    private final int capacity;
    private final int[] buffer;
    private volatile int head;//first element in logical circular buffer
    private volatile int size = 0;
    private ReentrantLock lock = new ReentrantLock(true);
    private Condition condition = lock.newCondition();

    public BoundedBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.buffer = new int[capacity];
        this.head = 0;

    }

    public void enqueue(int element) throws InterruptedException {
       // System.out.println("Entering enqueue " + element);
        while (true) {
            lock.lock();
            try {
               // System.out.println("Acquired enqueue lock");
                if (size < capacity ) {
                    addElementNextToHead(element);
                    condition.signalAll();
                    return;
                } else {
                    condition.await(100, TimeUnit.MILLISECONDS);
                }
            } finally {
                lock.unlock();
            }
        }


    }

    public int dequeue() throws InterruptedException {
    //    System.out.println("Entering dequeue");
        while (true) {
            lock.lock();
            try {
       //         System.out.println("Acquired dequeue lock");
                if (size > 0) {
                    int element = removeHeadElement();
                    condition.signalAll();
                    return element;
                } else {
                    condition.await(100, TimeUnit.MILLISECONDS);
                }
            } finally {
                lock.unlock();
            }
        }

    }

    public int size() {
        return size;
    }

    private void addElementNextToHead(int element) {
     //   System.out.println("ENTER Add element " + element + ", head=" + head + ", size=" + size);


        int indexToAdd;
        if(size == 0)
            indexToAdd = head;
        else if (head + size <= capacity - 1)
            indexToAdd = head + size;
        else
            indexToAdd = head + size - capacity;

        buffer[indexToAdd] = element;

        size++;
     //   System.out.println("EXIT Add element " + element + ", head=" + head + ", size=" + size);
    }

    private int removeHeadElement() {
    //    System.out.println("ENTER Remove element head=" + head + ", size=" + size);
        int elementToReturn = buffer[head];


        if(size == 1) {
        }
        else if (head == capacity - 1)
            head = 0;
        else
            head = head +1;


        size--;
    //    System.out.println("EXIT Remove element head=" + head + ", size=" + size);
        return elementToReturn;

    }
}