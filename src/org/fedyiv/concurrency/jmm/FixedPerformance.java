package org.fedyiv.concurrency.jmm;

public class FixedPerformance<T> {
    volatile T box;

    public synchronized void set(T v) {
        if (box == null) {
            box = v;
        }
    }

    public  T get() {
        return box;
    }
}
