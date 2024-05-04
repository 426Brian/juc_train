package com.juc.jmm;

import com.juc.jmm.entities.Customer;

/**
 * Classname: ObjectHeadDemo
 * Pacage: com.juc.jmm
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/5/3-18:57
 * Version: v1.0
 */
public class ObjectHeadDemo {
    public static void main(String[] args) {
        Object object = new Object();// 16 个字节，对象头中对对象标记占 8 字节， 对象类型占 8 字节
        System.out.println("object hashcode=" + object.hashCode());

        synchronized (object) {
        }

        System.gc();
        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        Customer customer3 = new Customer();
      }

}

