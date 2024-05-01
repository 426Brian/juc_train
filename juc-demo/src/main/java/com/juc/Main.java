package com.juc;

/**
 * Classname: ${NAME}
 * Pacage: com.juc
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/4/25-12:13
 * Version: v1.0
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        new Main().test();
        System.out.println("Hello world!");
    }

    public synchronized void test(){
        try {
            wait();  
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}