package org.fedyiv.concurrency.jmm;

public class PerformanceProblemForGet<T> {
    T box;

    public synchronized void set(T v) {
        if (box == null) {
            box = v;
        }
    }

    public synchronized T get() {
        //This is highly not optimal
        return box;
    }
}
