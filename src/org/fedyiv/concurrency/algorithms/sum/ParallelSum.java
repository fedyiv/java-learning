package org.fedyiv.concurrency.algorithms.sum;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ParallelSum {

    private static final int NUM_OF_THREADS = 12;
    ExecutorService executor = Executors.newFixedThreadPool(NUM_OF_THREADS);

    public int sequentialSum(int[] array) {
        return sequentialPartialSum(array, 0, array.length-1);
    }

    public static int sequentialPartialSum(int[] array, int start, int end) {
        int sum = 0;

        for (int i = start; i <= end; i++) {
            sum += array[i];
        }
        return sum;
    }

    public int parallelSum(int[] array) throws ExecutionException, InterruptedException {


        List<Future<Integer>> futurePartialSums = new ArrayList<>(NUM_OF_THREADS);

        for (int i = 1; i <= NUM_OF_THREADS; i++) {

            int start = (i-1)*(array.length/NUM_OF_THREADS);
            int end = i*(array.length/NUM_OF_THREADS) -1 ;
            if(i == NUM_OF_THREADS)
                end = array.length-1;
            futurePartialSums.add(executor.submit(new SumWorker(array,start, end)));

        }

        int sum = 0;
        for(Future<Integer> partialSum:futurePartialSums) {
            sum += partialSum.get();
        }

        return sum;

    }


    public static class SumWorker implements Callable<Integer> {

        int[] array;
        int start;
        int end;

        public SumWorker(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }


        @Override
        public Integer call() throws Exception {
            return ParallelSum.sequentialPartialSum(array, start, end);
        }
    }
}
