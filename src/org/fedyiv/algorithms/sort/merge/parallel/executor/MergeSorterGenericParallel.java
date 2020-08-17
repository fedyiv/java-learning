package org.fedyiv.algorithms.sort.merge.parallel.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MergeSorterGenericParallel<T extends Comparable<T>> {


    private Object[] array;

    //There is a problem here for big lists, as the program will use number of threads up to N
    private ExecutorService executor = Executors.newFixedThreadPool(16);


    public void sort(List<T> list) throws ExecutionException, InterruptedException {

        array = list.toArray();

        MergeSortWorker worker = new MergeSortWorker(0, list.size() - 1);

        executor.submit(worker).get();

        for (int i = 0; i < list.size(); i++)
            list.set(i, (T) array[i]);


    }


    private class MergeSortWorker implements Callable<Void> {

        int start;
        int end;

        public MergeSortWorker(int start, int end) {
            this.start = start;
            this.end = end;

        }

        @Override
        public Void call() throws Exception {

            if (start == end)
                return null;

            int middle = start + (end - start) / 2;


            if (((ThreadPoolExecutor) executor).getActiveCount() < ((ThreadPoolExecutor) executor).getMaximumPoolSize() - 1) {
                MergeSortWorker leftWorker = new MergeSortWorker(start, middle);
                MergeSortWorker rightWorker = new MergeSortWorker(middle + 1, end);

                Future<Void> leftResult = executor.submit(leftWorker);
                Future<Void> rightResult = executor.submit(rightWorker);

                leftResult.get();
                rightResult.get();

            } else {
                mergeSortSequential(start, middle);
                mergeSortSequential(middle + 1, end);
            }


            merge(start, middle, end);

            return null;
        }
    }


    private void mergeSortSequential(int start, int end) {


        if (start == end)
            return;

        int middle = start + (end - start) / 2;

        mergeSortSequential(start, middle);
        mergeSortSequential(middle + 1, end);


        merge(start, middle, end);

    }


    private void merge(int start, int middle, int end) {

        int i = start; // start...middle
        int j = middle + 1; //middle+1...end
        int k = 0;

        List<Object> tempList = new ArrayList<>(end - start + 1);

        while (i < middle + 1 && j < end + 1) {
            if (((T) array[i]).compareTo((T) array[j]) < 0) {
                tempList.add(k, array[i]);
                i++;
                k++;
            } else if (((T) array[i]).compareTo((T) array[j]) > 0) {
                tempList.add(k, array[j]);
                j++;
                k++;
            } else {
                tempList.add(k, array[i]);
                i++;
                k++;
            }
        }


        while (i < middle + 1) {
            tempList.add(k, array[i]);
            i++;
            k++;
        }

        while (j < end + 1) {
            tempList.add(k, array[j]);
            j++;
            k++;
        }


        for (int m = start, n = 0; m < end + 1; m++, n++) {
            array[m] = tempList.get(n);
        }


    }

}