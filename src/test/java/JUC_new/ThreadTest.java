package JUC_new;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * ClassName: ThreadTest
 * Pacage: JUC_new
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/4/8-18:04
 * Version: V1.0
 */
public class ThreadTest {

    @Test
    public void test1() {
        new Thread(() -> System.out.println(Thread.currentThread().getName()), "Thread_AA").start();
    }

    @Test
    public void test2() throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            return 1024;
        });
        new Thread(futureTask, "Thread_AA").start();
        Integer result = futureTask.get();
        System.out.println(result);
    }

    @Test
    public void test3() {
        // 3个任务，一个main 线程处理
        long start = System.currentTimeMillis();
        try {
            TimeUnit.MILLISECONDS.sleep(500);
            TimeUnit.MILLISECONDS.sleep(300);
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long end = System.currentTimeMillis();
        System.out.println((end - start) + "毫秒 " + Thread.currentThread().getName() + "线程");

    }

    @Test
    public void test4() throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        long start = System.currentTimeMillis();
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "futureTask";
        });
        threadPool.submit(futureTask);
        FutureTask<String> futureTask2 = new FutureTask<>(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "futureTask2";
        });
        threadPool.submit(futureTask2);
        System.out.println("futureTask result == " + futureTask.get());
        System.out.println("futureTask2 result == " + futureTask2.get());
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start + "毫秒");
        threadPool.shutdown();
    }

    @Test
    public void test5() throws Exception {
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            System.out.println("come in futureTask");
            TimeUnit.SECONDS.sleep(5);
            return "futureTask";
        });

        new Thread(futureTask, "Thread_AA").start();
        System.out.println("main Thread 忙其他的 ***** ");
        // futureTask.get() 没有计算结束，会阻塞程序，因此放在程序后
//        System.out.println("futureTask result == " + futureTask.get(3,TimeUnit.SECONDS)); 该方法报异常，不优雅
        String result = "";
        while (true) {
            // 用状态判断任务轮询
            if (futureTask.isDone()) {
                result = futureTask.get();
                break;
            } else {
                TimeUnit.MILLISECONDS.sleep(800);
                System.out.println("等待futureTask 完成任务");
            }
        }
        System.out.println("futureTask result == " + result);
    }

    @Test
    public void test6() throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, threadPool);
        System.out.println(voidCompletableFuture.get());

        threadPool.shutdown();
    }


    @Test
    public void test7() throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " come in");
            int result = ThreadLocalRandom.current().nextInt();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return result;
        });
        System.out.println(Thread.currentThread().getName() + "先去忙其他事儿");
        System.out.println("completableFuture result == " + completableFuture.get());
    }

    @Test
    public void test8() throws ExecutionException, InterruptedException {
        // *** 警告： 该方法在Junit 测试环境下有bug, 需要在常规类 main 方法中测试
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
//         主线程不要立刻结束，否则CompletableFuture 默认使用的线程池会自动立刻关闭，主线程暂停3秒
//            TimeUnit.SECONDS.sleep(3);
    }
}
