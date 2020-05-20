package org.fedyiv.concurrency.patterns.doublelocking;

public class DoubleCheckedLocking {


    /**
     * This implemenation won't work in multithreaded environment
     */
    public static class IncorrectImplementation {

        private static IncorrectImplementation instance;

        public IncorrectImplementation getInstance() {

            //Two threads might read that variable as null. Even if second thread read instance as not null, it does not mean it will read it the same during return
            if (instance == null)
                instance = new IncorrectImplementation(); //two threads might create new instance
            return instance;

        }
    }


    /**
     * Full synchronization solves the issues with multithreaded env, but by a high price.
     * We need synchronization only for case when instanse is under construction, but after that it is redundant and too slow.
     */
    public static class CorrectButSlowSingleton {
        private static CorrectButSlowSingleton instance;

        public synchronized CorrectButSlowSingleton getInstance() {

            if (instance == null)
                instance = new CorrectButSlowSingleton();
            return instance;

        }
    }

    /**
     * It is also not thread safe singleton, because when we first check instance first, it might be null for both threads.
     * And synchronized block content will be executed twice.
     * Also there is a race, when we check for null and return instance, those are two different reads, outside of happens before, thus there is a race between them
     */
    public static class IncorrectSynchronizationSingleton {
        private static IncorrectSynchronizationSingleton instance;

        public IncorrectSynchronizationSingleton getInstance() {

            if (instance == null)
                synchronized (IncorrectSynchronizationSingleton.class) {
                    instance = new IncorrectSynchronizationSingleton();
                }
            return instance;

        }
    }

    /**
     * Problem here is that there is a race, between checking if instance is null outside of synchronized block and returning instance
     */
    public static class IncorrectDoubleCheckedLockingSynchronizationSingleton {
        private static IncorrectDoubleCheckedLockingSynchronizationSingleton instance;

        public IncorrectDoubleCheckedLockingSynchronizationSingleton getInstance() {

            if (instance == null)
                synchronized (IncorrectDoubleCheckedLockingSynchronizationSingleton.class) {
                    if (instance == null)
                        instance = new IncorrectDoubleCheckedLockingSynchronizationSingleton();
                }
            return instance;

        }
    }

    /**
     * There are no problems here, as instance read/writes are part of happens before order.
     */
    public static class CorrectDoubleCheckedLockingSynchronizationSingleton {
        private static volatile CorrectDoubleCheckedLockingSynchronizationSingleton instance;

        public CorrectDoubleCheckedLockingSynchronizationSingleton getInstance() {

            if (instance == null)
                synchronized (CorrectDoubleCheckedLockingSynchronizationSingleton.class) {
                    if (instance == null)
                        instance = new CorrectDoubleCheckedLockingSynchronizationSingleton();
                }
            return instance;

        }
    }


}

