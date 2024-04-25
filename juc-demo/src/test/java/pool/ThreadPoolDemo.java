package pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ClassName: ThreadPoolDemo
 * Pacage: com.juc.pool
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/4/8-7:44
 * Version: V1.0
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        // 5 条线程
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        ExecutorService executorService3 = Executors.newCachedThreadPool();
        // 10 个任务
        try {
            for (int i = 0; i < 30; i++) {
                executorService3.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " 办理业务");
                });
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            executorService3.shutdown();
        }


    }
}
