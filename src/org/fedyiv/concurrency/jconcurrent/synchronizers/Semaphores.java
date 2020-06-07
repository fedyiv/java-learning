package org.fedyiv.concurrency.jconcurrent.synchronizers;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Semaphore;

public class Semaphores {

    @Test
    //It is possible to release more permits than we initially acquired.
    public void testReleasingMoreThanInitialCapacity() throws InterruptedException {

        final int initialCapacity = 3;

        Semaphore semaphore = new Semaphore(initialCapacity);

        System.out.println("Initially available permits: " +  semaphore.availablePermits());

        semaphore.release(5);

        System.out.println("Available permits after release: " +  semaphore.availablePermits());


        semaphore.acquire(2);

        System.out.println("Available permits after acquiring 2 permits: " +  semaphore.availablePermits());



    }


}
