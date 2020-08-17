package org.fedyiv.concurrency.algorithms.max;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class MaxFinder<T extends Comparable<? super T>> {

    private static final int ARRAY_SIZE_FOR_SINGLE_THREAD_COMPUTING = 10;

    private class FindMaxRecursiveTask extends RecursiveTask<T> {

        private T[] array;
        private int start;
        private int end;

        private T max(T x, T y) {
            if (x.compareTo(y) > 0)
                return x;
            else
                return y;
        }

        public FindMaxRecursiveTask(T[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected T compute() {

            System.out.println("Entering compute method with params start = " + start + ", end = " + end);


            if (end - start + 1 <= ARRAY_SIZE_FOR_SINGLE_THREAD_COMPUTING) {

                System.out.println("Using single thread approach");

                T max = array[start];

                for (int i = start; i <= end; i++) {
                    max = max(max, array[i]);
                }

                return max;

            } else {

                System.out.println("Using multi thread approach");
                int middle = start + (end - start + 1) / 2;

                FindMaxRecursiveTask leftSubArrayTask = new FindMaxRecursiveTask(array, start, middle);
                FindMaxRecursiveTask rightSubArrayTask = new FindMaxRecursiveTask(array, middle + 1, end);

                leftSubArrayTask.fork();
                rightSubArrayTask.fork();
                //as an alternative we can call invokeAll(leftSubArrayTask,rightSubArrayTask)


                return max(leftSubArrayTask.join(), rightSubArrayTask.join());
            }

        }

    }


    public T findMax(T[] array) throws ExecutionException, InterruptedException {

        ForkJoinPool forkJoinPool = new ForkJoinPool();

        FindMaxRecursiveTask task = new FindMaxRecursiveTask(array, 0, array.length-1);

        return forkJoinPool.submit(task).get();


    }
}
