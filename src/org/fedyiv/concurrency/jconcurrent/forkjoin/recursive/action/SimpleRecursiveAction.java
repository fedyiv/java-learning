package org.fedyiv.concurrency.jconcurrent.forkjoin.recursive.action;

import java.util.concurrent.RecursiveAction;

public class SimpleRecursiveAction extends RecursiveAction {

    int simulatedWork;

    SimpleRecursiveAction(int simulatedWork){
        this.simulatedWork = simulatedWork;
    }

    @Override
    protected void compute() {

        if(simulatedWork > 10) {
            System.out.println("Going parallel for simulatedWork = " + simulatedWork);

            SimpleRecursiveAction simpleRecursiveAction1 = new SimpleRecursiveAction(simulatedWork/2);
            SimpleRecursiveAction simpleRecursiveAction2 = new SimpleRecursiveAction(simulatedWork/2);

            simpleRecursiveAction1.fork();
            simpleRecursiveAction2.fork();

            simpleRecursiveAction1.join();
            simpleRecursiveAction2.join();

        } else {
            System.out.println("No need for parallel execution, going sequential for simulatedWork = " + simulatedWork);
        }

    }
}
