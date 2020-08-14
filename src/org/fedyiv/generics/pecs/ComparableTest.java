package org.fedyiv.generics.pecs;

import org.junit.jupiter.api.Test;

public class ComparableTest {

    public class Person implements Comparable<Person> {

        String name;

        public Person(String name) {
            this.name = name;
        }

        @Override
        public int compareTo(Person other) {

            System.out.println("Comparing persons:" + this + " and " + other);

            return name.compareTo(other.name);
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }


    public class Student extends Person {

        private String uni;

        public Student(String name, String uni) {
            super(name);
            this.uni = uni;
        }

        @Override
        public int compareTo(Person other) {

            System.out.println("Comparing students:" + name + " and " + other.name);

            Student otherStudent = (Student) other;

            return uni.compareTo(otherStudent.uni);
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", uni='" + uni + '\'' +
                    '}';
        }
    }


    //Bad implementation
    private class MaxFinder1<T extends Comparable<T>> {

        public T max(T x, T y) {

            if (x.compareTo(y) > 0)
                return x;
            else return y;
        }
    }

    //Good implementation
    private class MaxFinder2<T extends Comparable<? super T>> {

        public T max(T x, T y) {
            if (x.compareTo(y) > 0)
                return x;
            else return y;

        }

    }

    @Test
    public void test() {

        Person jack = new Person("Jack");
        Person bob = new Person("Bob");

        MaxFinder1<Person> personMaxFinder1 = new MaxFinder1<>();

        Student alice = new Student("Alice", "Poli");
        Student eve = new Student("Eve", "Poli");

        personMaxFinder1.max(jack, bob);
        personMaxFinder1.max(alice, eve);

        //Error -->
      //  MaxFinder1<Student> studentMaxFinder1 = new MaxFinder1<Student>();

        MaxFinder2<Person> personMaxFinder2 = new MaxFinder2<>();
        MaxFinder2<Student> studentMaxFinder2 = new MaxFinder2<>();

    }


}
