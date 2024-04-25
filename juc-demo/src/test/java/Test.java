/**
 * ClassName: Test
 * Pacage: com.juc
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/4/7-18:06
 * Version: V1.0
 */
public class Test {
    public static void main(String[] args) {
        RunTest runTest = new RunTest();
        new Thread(runTest, "AA").start();
    }
}

class RunTest extends Thread implements Runnable {
    @Override
    public void run() {
        System.out.println(this.getClass().getName());
    }
}
