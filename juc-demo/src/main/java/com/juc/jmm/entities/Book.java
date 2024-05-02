package com.juc.jmm.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Classname: Book
 * Pacage: com.juc.jmm
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/5/2-8:10
 * Version: v1.0
 */
@AllArgsConstructor
@Data
public class Book {
    int id;
    String bookName;
}
