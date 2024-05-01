package com.juc.jmm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Classname: AtomicReferenceDemo
 * Pacage: com.juc.jmm
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/5/1-16:40
 * Version: v1.0
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        AtomicReference<User> userAtomicReference = new AtomicReference<>();
        User z3 = new User("z3", 22);
        User Li4 = new User("li4", 28);

        userAtomicReference.set(z3);
        System.out.println(userAtomicReference.compareAndSet(z3, Li4) + "\t" + userAtomicReference.get());
        System.out.println(userAtomicReference.compareAndSet(z3, Li4) + "\t" + userAtomicReference.get());
    }
}

@Getter
@ToString
@AllArgsConstructor
class User {
    String userName;
    int age;
}