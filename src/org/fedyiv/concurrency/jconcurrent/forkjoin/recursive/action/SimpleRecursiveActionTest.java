package org.fedyiv.concurrency.jconcurrent.forkjoin.recursive.action;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;


class SimpleRecursiveActionTest {

    @Test
    public void testSequential() {

        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

        SimpleRecursiveAction simpleRecursiveAction = new SimpleRecursiveAction(5);

        pool.invoke(simpleRecursiveAction);

    }

    @Test
    public void testParallel() {

        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

        SimpleRecursiveAction simpleRecursiveAction = new SimpleRecursiveAction(20);

        pool.invoke(simpleRecursiveAction);

    }

}