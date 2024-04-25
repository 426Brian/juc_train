package com.juc.pool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: CompletableFutureTest2
 * Pacage: com.juc.pool
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/4/9-10:29
 * Version: V1.0
 */
public class CompletableFutureThenApplyOrHandle {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
//        thenApplyTest(threadPool);
        handleTest(threadPool);
    }

    public static void thenApplyTest(ExecutorService threadPool) {
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("111");
            return 1;
        }, threadPool).thenApply(f -> {
//            int i = 10 / 0;
            // 当前出错后，将不继续往下运行，参见 handle（有异常可继续)
            System.out.println("222");
            return f + 2;
        }).thenApply(f -> {
            System.out.println("333");
            return f + 3;
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println("无异常");
                System.out.println("计算结果 v ==" + v);
            }

        }).exceptionally(e -> {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        });

        System.out.println(Thread.currentThread().getName() + "------- 主线程先去忙其他业务");
        threadPool.shutdown();
       /* try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
    }

    public static void handleTest(ExecutorService threadPool) {
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("111");
            return 1;
        }, threadPool).handle((f, e) -> {
            int i = 10 / 0;
            // 当前出错后，将继续往下运行，参见 thenapply (有异常不可继续)
            System.out.println("222");
            return f + 2;
        }).handle((f, e) -> {
            System.out.println("333");
            return f + 3;
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println("无异常");
                System.out.println("计算结果 v ==" + v);
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        });

        System.out.println(Thread.currentThread().getName() + "------- 主线程先去忙其他业务");
        threadPool.shutdown();
       /* try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
    }
}
