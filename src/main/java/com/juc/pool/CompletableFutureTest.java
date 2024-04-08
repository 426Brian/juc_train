package com.juc.pool;

import java.util.concurrent.*;

/**
 * ClassName: CompletableFutureTest
 * Pacage: com.juc.pool
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/4/8-22:16
 * Version: V1.0
 */
public class CompletableFutureTest {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        try {
            CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName() + "--- come in");
                int result = ThreadLocalRandom.current().nextInt(10);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("1秒钟后出结果 " + result);
                return result;
            }, threadPool).whenComplete((v, e) -> {// v 是上一步任务的返回值即 return 的 result , e 是指异常
                if (e == null) {
                    // 表示无异常
                    System.out.println("计算完成，所得结果: " + v);
                }
            }).exceptionally(e -> {
                e.printStackTrace();
                System.out.println("异常情况： " + e.getCause() + e.getMessage());
                return null;
            });
            System.out.println(Thread.currentThread().getName() + "线程先去忙其他事儿");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
