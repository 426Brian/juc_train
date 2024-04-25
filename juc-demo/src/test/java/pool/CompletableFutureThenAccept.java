package pool;

import java.util.concurrent.CompletableFuture;

/**
 * ClassName: CompletableFutureThenAccept
 * Pacage: com.juc.pool
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/4/9-11:05
 * Version: V1.0
 */
public class CompletableFutureThenAccept {
    public static void main(String[] args) {
//        thenApplyDemo();
//        thenRun();
        thenAcceptDemo();
    }

    private static void thenApplyDemo() {
        CompletableFuture.supplyAsync(() -> {
            return 1;
        }).thenApply(f -> {
            return f + 2;
        }).thenApply(f -> {
            return f + 3;
        }).thenAccept(System.out::println);
    }
    private static void thenRun() {
        System.out.println(CompletableFuture.supplyAsync(() -> {
            return 1;
        }).thenRun(() -> {
        }).join());
    }
    private static void thenAcceptDemo() {
        CompletableFuture.supplyAsync(() -> {
            return 1;
        }).thenAccept(System.out::println);
    }
}
