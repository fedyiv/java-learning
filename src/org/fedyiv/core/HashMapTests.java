package org.fedyiv.core;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class HashMapTests {

    @Test
    public void testUsingArrayAsKey() {
        byte[] array1 = new byte[]{1, 2, 3, 4, 5};
        byte[] array2 = new byte[]{1, 2, 3, 4, 5};

        System.out.println("Array1 hashcode:" + array1.hashCode());
        System.out.println("Array2 hashcode:" + array2.hashCode());

        Map<byte[], String> map = new HashMap<>();
        map.put(array1, "Initial value for array1");
        map.put(array2, "Initial value for array2");

        printMap(map);


        array1[2] = 3;

        System.out.println("Array1 hashcode after elements change:" + array1.hashCode());

        System.out.println("map.get(array1)=" + map.get(array1));

        printMap(map);




    }

    private void printMap(Map<byte[], String> map) {
        System.out.println("Iterating over map");
        for(byte[] key: map.keySet()) {
            System.out.println("Key hashCode=" + key.hashCode() + ", key=" + key + ", value=" + map.get(key));
        }
    }


}
