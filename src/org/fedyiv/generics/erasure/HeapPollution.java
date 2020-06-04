package org.fedyiv.generics.erasure;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class HeapPollution {

    static List<String> polluteHeap() {
        List l = new ArrayList<Number>();
        l.add(1);
        l.add("test");// add whatever we want

        List<String>  stringList = l;

        stringList.add("qqq");

        return stringList;

    }

    @Test
    public void test() {

        List<String> list = polluteHeap();

        for(Object o:list) {
            System.out.println("New object received");

            if(o instanceof Object)
                System.out.println("object");

            if(o instanceof String)
                System.out.println("string");

            if(o instanceof Number)
                System.out.println("number");
        }

    }
}
