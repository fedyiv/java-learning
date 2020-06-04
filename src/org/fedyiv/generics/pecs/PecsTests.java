package org.fedyiv.generics.pecs;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PecsTests {
    // See https://habr.com/ru/company/sberbank/blog/416413/

    @Test
    public void testExtends() {

        List<Integer> ints = new ArrayList<>();

        ints.add(1);
        ints.add(2);
        //ints.add(2.5); //compile time error, because we are trying to add double into integers list

        List<? extends Number> intNums = ints;


        //List<Number> pureNumberList = ints; // Compile time error because generics in java are invariant without wildcard

        intNums.add(null);
        //nums.add(1); //compile time error because list knows that it extends Number, means that it can contain any type less than Number, and it can not validate at compile time whether we are adding correct tyoe or no

        System.out.println(intNums.get(0));

        List<Double> doubles = new ArrayList<>();

        doubles.add(1.1);
        doubles.add(2.2);

        List<? extends Number> doubleNum = doubles;

    }

    @Test
    public void testSuper() {

        List<Integer> ints = new ArrayList<>();

        ints.add(1);
        ints.add(2);


        List<? super Integer> superIntegerList = ints;

        superIntegerList.add(1);
        //Integer x =  superIntegerList.get(0);// compile time  error because we do not know upper bound (it is object) and can not safely cast it to integer
        Object o = superIntegerList.get(0);

    }

    @Test
    public void testProducerConsumerCombined() {


        List<? super Integer> consumer;
        List<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(2);

        produce(ints);
        consume(ints);

        fakeConsume(ints);

        consume(ints);
    }

    private void produce(List<? super Integer> consumer) {
        consumer.add(3);
        consumer.add(4);
    }

    private void consume(List<? extends Integer> producer) {
        for (int n : producer) {
            System.out.println(n);
        }
    }

    //We can not consider "? extends T" be safe from adding values into. See the example below.
    //This is called wildcard capture
    private void fakeConsume(List<? extends Integer> producer) {
        for (int n : producer) {
            System.out.println(n);
        }

        polluteList(producer);

    }

    private <T> void polluteList(List<T> list) {

        T element = list.get(0);

        list.add(element);
    }


}
