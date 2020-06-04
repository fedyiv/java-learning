package org.fedyiv.algorithms.sort.merge;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class MergeSorterGenericTest {

    @Test
    void sortInts() {

        MergeSorterGeneric<Integer> mergeSort = new MergeSorterGeneric<>();

        List<Integer> testList = Arrays.asList(1, 9, 2, 8, 5, 7, 8, 1, 4, 6, 0, 9);

        mergeSort.sort(testList);


        List<Integer> expectedList = new ArrayList<>(testList);

        Collections.sort(expectedList);

        assertIterableEquals(expectedList, testList);
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