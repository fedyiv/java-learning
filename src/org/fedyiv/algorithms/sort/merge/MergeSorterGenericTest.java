package org.fedyiv.algorithms.sort.merge;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class MergeSorterGenericTest extends MergeSortBaseTest {

    @Test
    void sortInts() {

        MergeSorterGeneric<Integer> mergeSort = new MergeSorterGeneric<>();

        List<Integer> testList = generateIntegerRandomSequence(100000);

        mergeSort.sort(testList);


        List<Integer> expectedList = new ArrayList<>(testList);

        Collections.sort(expectedList);

        assertIterableEquals(expectedList, testList);
    }

    @Test
    void sortIntsPerformance() {

        MergeSorterGeneric<Integer> mergeSort = new MergeSorterGeneric<>();

        List<Integer> testList = generateIntegerRandomSequence(10000000);

        mergeSort.sort(testList);
    }

    @Test
    void sortStrings() {

        MergeSorterGeneric<String> mergeSort = new MergeSorterGeneric<>();

        List<String> testList = Arrays.asList("1","3","0","test");

        mergeSort.sort(testList);


        List<String> expectedList = new ArrayList<>(testList);

        Collections.sort(expectedList);

        assertIterableEquals(expectedList, testList);
    }

}