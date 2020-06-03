package org.fedyiv.algorithms.sort.merge;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MergeSorterIntTest {

    @Test
    void mergeSort() {

        MergeSorterInt mergeSort = new MergeSorterInt();

        List<Integer> testList = Arrays.asList(new Integer[]{1, 9, 2, 8, 5, 7, 8, 1, 4, 6, 0, 9});

        mergeSort.sort(testList);


        List<Integer> expectedList = new ArrayList<>(testList);

        Collections.sort(expectedList);

        assertIterableEquals(expectedList, testList);

    }
}