import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * ClassName: CompletableFutureTest
 * Pacage: com.juc
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/4/8-8:20
 * Version: V1.0
 */
public class CompletableFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " AA_");
        });
        voidCompletableFuture.get();

        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " BB_");
            int s = 1 / 0;
            return 1024;
        });

        integerCompletableFuture.whenComplete((t, u) -> {
            System.out.println("---t = " + t);
            System.out.println("---u = " + u);
        });
    }
}
