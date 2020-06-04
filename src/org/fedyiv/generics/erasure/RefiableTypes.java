package org.fedyiv.generics.erasure;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class RefiableTypes<T> {
    String s = "string";
    List<String> stringList = new ArrayList();
    T t;

    @Test
    public void test() {
        if (s instanceof String)
            System.out.println("String is refiable type, so we can use intanceOf");
/*
        if(stringList instanceof List<String>)
            System.out.println("Non-refiable types can not e used in instanceOf");

        if(stringList instanceof T)
            System.out.println("Non-refiable types can not e used in instanceOf");

            */

        String[]  stringArray= new String[] { "s1", "s2"};

        T[]  tArray;


    }

}
