package pool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: FutureCombine
 * Pacage: com.juc.pool
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/4/9-11:50
 * Version: V1.0
 */
public class FutureCombine {
    public static void main(String[] args) {
//        combineDemo();
        combineTest();
    }

    private static void combineDemo() {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " 线程启动");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            return 10;
        });
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " 线程启动");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            return 20;
        });
        CompletableFuture<Integer> result = future1.thenCombine(future2, (x, y) -> {
            System.out.println("两个任务开始合并");
            return x + y;
        });
        System.out.println(result.join());
    }

    private static void combineTest() {
        System.out.println(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " 线程启动");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 10;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " 线程启动");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 20;
        }), (x, y) -> {
            return x + y;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " 线程启动");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 5;
        }), (x, y) -> {
            return x+y;
        }).join());
    }
}
