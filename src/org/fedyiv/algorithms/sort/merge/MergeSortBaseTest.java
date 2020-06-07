package org.fedyiv.algorithms.sort.merge;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MergeSortBaseTest {

    Random rand = new Random();

    protected List<Integer> generateIntegerRandomSequence(int length) {

        List<Integer> list = new ArrayList<>(length);

        for(int i = 0; i < length; i++) {
            list.add(rand.nextInt());
        }

        return  list;

    }
}
