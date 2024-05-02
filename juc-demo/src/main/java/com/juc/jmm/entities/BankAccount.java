package com.juc.jmm.entities;

import lombok.Data;

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
    String bankName = "CCB";
   public int money = 0;

    public void add(){
        money++;
    }
}
