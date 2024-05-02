package com.juc.jmm.entities;

import lombok.Data;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

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
    String bankName = "CCB";
    AtomicIntegerFieldUpdater<BankAccount> fileUpdater = AtomicIntegerFieldUpdater.newUpdater(BankAccount.class, "money");

    public void add() {
        money++;
    }

    public void transferMoney(BankAccount bankAccount) {
        fileUpdater.getAndIncrement(bankAccount);
    }
}
