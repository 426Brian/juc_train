import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * ClassName: FutureTaskTest
 * Pacage: com.juc
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/4/7-18:47
 * Version: V1.0
 */
public class FutureTaskTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask futureTask = new FutureTask(()->{
            System.out.println(Thread.currentThread().getName());
            return 3;
        });
        FutureTask futureTask2 = new FutureTask(()->{
            System.out.println(Thread.currentThread().getName());
            return 3;
        });

        new Thread(futureTask, "AA").start();
        new Thread(futureTask2, "BB").start();
        while (!futureTask.isDone()){
            System.out.println("futuretask is done");
        }

        System.out.println(futureTask.get());
        System.out.println(futureTask2.get());
    }
}
