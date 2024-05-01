package com.juc.jmm;

/**
 * Classname: SafeDoubleCheck
 * Pacage: com.juc.jmm
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/5/1-15:26
 * Version: v1.0
 */
public class SafeDoubleCheck {
    private volatile static SafeDoubleCheck safeDoubleCheck;

    private SafeDoubleCheck() {

    }

    public static SafeDoubleCheck getInstance() {
        if (safeDoubleCheck == null) {
            synchronized (SafeDoubleCheck.class) {
                if (safeDoubleCheck == null) {
                    safeDoubleCheck = new SafeDoubleCheck();
                }
            }
        }
        return safeDoubleCheck;
    }

    public static void main(String[] args) {

    }
}
