package org.fedyiv.algorithms.sort.merge;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class MergeSorterIntTest extends  MergeSortBaseTest{

    @Test
    void mergeSort() {

        MergeSorterInt mergeSort = new MergeSorterInt();

        List<Integer> testList = generateIntegerRandomSequence(15);

        mergeSort.sort(testList);


        List<Integer> expectedList = new ArrayList<>(testList);

        Collections.sort(expectedList);

        assertIterableEquals(expectedList, testList);

    }
}