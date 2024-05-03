package com.juc.jmm.entities;

/**
 * Classname: MyObejct
 * Pacage: com.juc.jmm.entities
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/5/3-17:30
 * Version: v1.0
 */
public class MyObejct {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("---- invoke finalize method ~");
    }
}
