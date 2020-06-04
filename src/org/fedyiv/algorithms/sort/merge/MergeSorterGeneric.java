package org.fedyiv.algorithms.sort.merge;

import java.util.ArrayList;
import java.util.List;

public class MergeSorterGeneric<T extends Comparable<T>> {

    public void sort(List<T> list) {
        mergeSort(list, 0, list.size() - 1);

    }

    private void mergeSort(List<T> list, int start, int end) {

        //System.out.println("In mergeSort: start = " + start + ", end = " + end); test

        if (start == end)
            return;

        int middle = start + (end - start) / 2;

        mergeSort(list, start, middle);
        mergeSort(list, middle + 1, end);


        merge(list, start, middle, end);

    }

    private void merge(List<T> list, int start, int middle, int end) {

        int i = start; // start...middle
        int j = middle + 1; //middle+1...end
        int k = 0;

        List<T> tempList = new ArrayList<>(end - start + 1);

        while (i < middle + 1 && j < end + 1) {
            if (list.get(i).compareTo(list.get(j)) < 0) {
                tempList.add(k, list.get(i));
                i++;
                k++;
                continue;
            } else if (list.get(i).compareTo(list.get(j)) > 0) {
                tempList.add(k, list.get(j));
                j++;
                k++;

            } else {
                tempList.add(k, list.get(i));
                i++;
                k++;
                continue;
            }
        }


        while (i < middle + 1) {
            tempList.add(k, list.get(i));
            i++;
            k++;
        }

        while (j < end + 1) {
            tempList.add(k, list.get(j));
            j++;
            k++;
        }


        for (int m = start, n = 0; m < end + 1; m++, n++) {
            list.set(m, tempList.get(n));
        }


    }

}