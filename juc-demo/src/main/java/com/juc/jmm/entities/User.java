package com.juc.jmm.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Classname: User
 * Pacage: com.juc.jmm
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/5/2-8:10
 * Version: v1.0
 */
@Getter
@ToString
@AllArgsConstructor
public class User {
    String userName;
    int age;
}
