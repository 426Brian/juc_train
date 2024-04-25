package com.juc.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * ClassName: Demo
 * Pacage: com.juc.callable
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/4/7-13:10
 * Version: V1.0
 */
public class Demo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread myThread = new MyThread();
        new Thread(myThread,"thread_AA").start();

        FutureTask futureTask = new FutureTask<Integer>(()->{
            System.out.println(Thread.currentThread().getName() + " come in callale");
            return 1024;
        });
        new Thread(futureTask,"thread_BB").start();
        new Thread(futureTask,"thread_CC").start();

        while (!futureTask.isDone()){
//            System.out.println("wait...");
        }
        System.out.println("callable result == "+futureTask.get());
    }

}

class MyThread implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}

class MyThread2 implements Callable {
    @Override
    public Object call() throws Exception {
        return null;
    }
}
