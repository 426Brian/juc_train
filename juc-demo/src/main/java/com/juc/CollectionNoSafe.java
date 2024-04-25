package com.juc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ClassName: CollectionNoSafe
 * Pacage: com.juc
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/4/7-10:40
 * Version: V1.0
 */


public class CollectionNoSafe {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        CopyOnWriteArrayList<String> listSafe = new CopyOnWriteArrayList<>();
        HashMap<String, String> hashMap = new HashMap<>();

        // 不安全
//        test1(list);
        // 安全
//        test1(listSafe);
        testMap(hashMap);
    }

    public static void test1(List list) {
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, "A" + i).start();
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, "B" + i).start();

        }
    }

    public static void testMap(HashMap<String,String> map) {
        for (int i = 0; i < 30; i++) {
            String str = String.valueOf(i);
            new Thread(() -> {
                map.put(str,UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            }, "A" + i).start();
            new Thread(() -> {
                map.put(str,UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            }, "B" + i).start();

        }
    }

}
