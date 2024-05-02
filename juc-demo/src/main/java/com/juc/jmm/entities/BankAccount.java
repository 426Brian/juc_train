package com.juc.jmm.entities;

import lombok.Data;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * Classname: BankAccount
 * Pacage: com.juc.jmm.entities
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/5/2-10:57
 * Version: v1.0
 */
@Data
public class BankAccount {
    public volatile int money = 0;
    public volatile Boolean flag = Boolean.FALSE;
    AtomicIntegerFieldUpdater<BankAccount> fileUpdater = AtomicIntegerFieldUpdater.newUpdater(BankAccount.class, "money");
    AtomicReferenceFieldUpdater<BankAccount, Boolean> flagUpdater = AtomicReferenceFieldUpdater.newUpdater(BankAccount.class, Boolean.class, "flag");

    String bankName = "CCB";

    public void add() {
        money++;
    }

    public void transferMoney(BankAccount bankAccount) {
        fileUpdater.getAndIncrement(bankAccount);
    }

    public void flagChange(BankAccount bankAccount) {
        if (flagUpdater.compareAndSet(bankAccount, Boolean.FALSE, Boolean.TRUE)) {
            System.out.println(Thread.currentThread().getName()+" 更新flag成功, 当前值 flag=" + Boolean.TRUE);
        } else {
            System.out.println(Thread.currentThread().getName()+" flag已经被其他线程更改, 当前值 flag=" + Boolean.TRUE);
        }
    }
}
