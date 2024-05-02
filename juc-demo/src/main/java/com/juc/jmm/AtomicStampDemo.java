package com.juc.jmm;

import com.juc.jmm.entities.Book;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * Classname: AtomicStampDemo
 * Pacage: com.juc.jmm
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/5/1-21:37
 * Version: v1.0
 */
public class AtomicStampDemo {
    public static void main(String[] args) {
        Book javaBook = new Book(1, "JavaBook");
        AtomicStampedReference<Book> stampedReference = new AtomicStampedReference<>(javaBook, 1);
        System.out.println(stampedReference.getReference() + "\t version: " + stampedReference.getStamp());
        Book mysqlBook = new Book(2, "MysqlBook");
        boolean b = stampedReference.compareAndSet(javaBook, mysqlBook, stampedReference.getStamp(), stampedReference.getStamp() + 1);
        System.out.println(b + "\t" + stampedReference.getReference()+ "\t version: " + stampedReference.getStamp());

        boolean b2 = stampedReference.compareAndSet(mysqlBook, javaBook, stampedReference.getStamp(), stampedReference.getStamp() + 1);
        System.out.println(b2 + "\t" + stampedReference.getReference()+ "\t version: " + stampedReference.getStamp());
    }
}


