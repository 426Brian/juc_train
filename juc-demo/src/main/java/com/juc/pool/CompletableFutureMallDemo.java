package com.juc.pool;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * ClassName: CompletableFutureMallDemo
 * Pacage: com.juc.pool
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/4/8-22:27
 * Version: V1.0
 */
public class CompletableFutureMallDemo {
    static List<NetMall> list = Arrays.asList(
            new NetMall("jd"),
            new NetMall("dangdang"),
            new NetMall("pdd"),
            new NetMall("tmall"),
            new NetMall("dewu"),
            new NetMall("taobao"));

    public static void main(String[] args) {
//        test();

//        System.out.println(ThreadLocalRandom.current().nextDouble() * 2 + "mysql".charAt(0));
        System.out.println("别急，故意在方法calcPrice()里用了延时，放大程序运行时间，请耐心等待");

        long start = System.currentTimeMillis();
        List<String> list2 = getPrice(list, "mysql");

        list2.stream().forEach(System.out::println);

        long end = System.currentTimeMillis();
        System.out.println("--- costTime " + (end - start) + "毫秒");

        System.out.println("******************");
        long start2 = System.currentTimeMillis();
        List list3 = getPriceByCompletableFuture(list, "mysql");

        list3.stream().forEach(System.out::println);

        long end2 = System.currentTimeMillis();
        System.out.println("--- costTime " + (end2 - start2) + "毫秒");
    }

    public static List<String> getPrice(List<NetMall> list, String productName) {
        return list
                .stream()
                .map(netMall ->
                        String.format(productName + " in %s price is %.2f元",
                                netMall.getNetMallName(),
                                netMall.calcPrice(productName))
                )
                .collect(Collectors.toList());

    }

    public static List<String> getPriceByCompletableFuture(List<NetMall> list, String productName) {
        return list
                .stream()
                .map(netMall -> CompletableFuture.supplyAsync(() ->
                        String.format(productName + " in %s price is %.2f元",
                                netMall.getNetMallName(),
                                netMall.calcPrice(productName)))
                )
                .collect(Collectors.toList())
                .stream()
                .map(s -> s.join())
                .collect(Collectors.toList());
    }

    private static void test() {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            return "CompletableFuture";
        });

        // get() 在编译时需要处理异常，join() 不需要
//        completableFuture.get();
        System.out.println(completableFuture.join());
    }
}


@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)// 可以给实例对象链式赋属性值
class NetMall {
    @Getter
    private String netMallName;

    public double calcPrice(String productName) {
        // 故意延迟，放大程序运行时间
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);
    }
}
