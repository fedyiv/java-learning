package org.fedyiv.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomUtils {

    private static Random rand = new Random();

    public static List<Integer> generateIntegerRandomSequence(int length) {

        List<Integer> list = new ArrayList<>(length);

        for(int i = 0; i < length; i++) {
            list.add(rand.nextInt());
        }

        return  list;

    }

    public static int[] generateIntegerRandomArray(int length, int bound) {
        int [] array = new int [length];

        for(int i = 0; i < length; i++) {
            array[i] = rand.nextInt(bound);
        }

        return  array;
    }

    public static int[] generateIntegerRandomArray(int length) {
        return  generateIntegerRandomArray(length, Integer.MAX_VALUE);
    }

}
