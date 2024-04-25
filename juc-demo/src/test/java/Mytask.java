import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * ClassName: Mytask
 * Pacage: com.juc
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/4/8-8:05
 * Version: V1.0
 */
public class Mytask extends RecursiveTask<Integer> {
    private static final Integer VALUE = 10;
    private int begin; // 拆分开始值
    private int end; // 拆分结束值
    private int result; // 返回结果

    public Mytask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if ((end - begin) <= VALUE) {
            for (int i = begin; i <= end; i++) {
                result = result + i;
            }
        }else {
            int middle = (begin+end)/2;
            Mytask task1 = new Mytask(begin,middle);
            Mytask task2 = new Mytask(middle+1,end);

            task1.fork();
            task2.fork();

            result = task1.join()+task2.join();
        }
        return result;
    }
}

class ForkJoin {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Mytask mytask = new Mytask(0, 100);

        // 分支合并池
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(mytask);

        Integer result = forkJoinTask.get();

        System.out.println(result);
    }
}