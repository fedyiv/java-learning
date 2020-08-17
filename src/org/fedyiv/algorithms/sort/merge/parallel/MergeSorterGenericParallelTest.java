package org.fedyiv.algorithms.sort.merge.parallel;

import org.fedyiv.algorithms.sort.merge.parallel.forkjoin.MergeSorterForkJoin;
import org.fedyiv.helpers.RandomUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;


class MergeSorterGenericParallelTest {

    @Test
    public void  testParralelSort() throws ExecutionException, InterruptedException {
        MergeSorterForkJoin<Integer> mergeSort = new MergeSorterForkJoin<>();

        List<Integer> testList = RandomUtils.generateIntegerRandomSequence(1000);

        mergeSort.sort(testList);

        List<Integer> expectedList = new ArrayList<>(testList);
        Collections.sort(expectedList);
        assertIterableEquals(expectedList, testList);

    }


    @Test
    void sortIntsPerformance() throws ExecutionException, InterruptedException {

        MergeSorterForkJoin<Integer> mergeSort = new MergeSorterForkJoin<>();

        List<Integer> testList = RandomUtils.generateIntegerRandomSequence(10000000);

        mergeSort.sort(testList);
    }

}