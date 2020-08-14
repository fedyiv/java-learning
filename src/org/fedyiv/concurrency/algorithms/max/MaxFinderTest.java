package org.fedyiv.concurrency.algorithms.max;

import org.fedyiv.helpers.RandomUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.ExecutionException;

class MaxFinderTest {

    @Test
    public void test() throws ExecutionException, InterruptedException {

        MaxFinder<Integer> maxFinder = new MaxFinder<>();

        Integer[] array = Arrays.stream(RandomUtils.generateIntegerRandomArray(100)).boxed().toArray(Integer[]::new);

        Integer max = maxFinder.findMax(array);

        System.out.println("Max=" + max);

        Integer expectedMax = Arrays.stream(array).max(Comparator.naturalOrder()).get();

        System.out.println("Expected max=" + expectedMax);

        Assertions.assertEquals(expectedMax, max);


    }

}