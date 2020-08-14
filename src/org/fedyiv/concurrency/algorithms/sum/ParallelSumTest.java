package org.fedyiv.concurrency.algorithms.sum;

import org.fedyiv.helpers.RandomUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

class ParallelSumTest {

    private static final  int MAX_ELEMENTS = 1000000000;

    @Test
    public void testParallelImplementationIsCorrect() throws ExecutionException, InterruptedException {

        ParallelSum parallelSum = new ParallelSum();

        int testArray[] = RandomUtils.generateIntegerRandomArray(1000);

        printArray(testArray);

        int sequentialSum = parallelSum.sequentialSum(testArray);

        int parallelSUm = parallelSum.parallelSum(testArray);

        Assertions.assertEquals(sequentialSum, parallelSUm);

    }

    @Test
    public void measureSequentialPerformance() throws ExecutionException, InterruptedException {

        ParallelSum parallelSum = new ParallelSum();
        int testArray[] = RandomUtils.generateIntegerRandomArray(MAX_ELEMENTS);
        parallelSum.sequentialSum(testArray);

    }

    @Test
    public void measureParallelPerformance() throws ExecutionException, InterruptedException {

        ParallelSum parallelSum = new ParallelSum();
        int testArray[] = RandomUtils.generateIntegerRandomArray(MAX_ELEMENTS);
        parallelSum.parallelSum(testArray);



    }

    private void printArray(int[] array) {
        for (int element : array) {
            System.out.print(element + ", ");
        }
        System.out.println();

    }

}