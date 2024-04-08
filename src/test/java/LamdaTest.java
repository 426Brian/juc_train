import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * ClassName: LamdaTest
 * Pacage: com.juc.newFetrue
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/4/8-9:23
 * Version: V1.0
 */
public class LamdaTest {
    @Test
    public void test1() {
        // 1. 无参，无返回，接口实现类
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable");
            }
        };
        runnable.run();

        Runnable runnable2 = () -> {
            System.out.println("Runnable");
        };
        runnable2.run();
    }

    @Test
    public void test2() {
        // 2. 需要一个参数
        Consumer<String> consumer = new Consumer<>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        consumer.accept("谎言和誓言的区别是什么");

//        类型推断
        Consumer<String> consumer2 = (s) -> {
            System.out.println(s);
        };
        consumer2.accept("听的人当真了，说的人当真了");
    }

    @Test
    public void test3() {
        // 3. 一个参数，小括号可以省略
        Consumer<String> consumer = new Consumer<>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        consumer.accept("谎言和誓言的区别是什么");

        Consumer<String> consumer2 = s -> {
            System.out.println(s);
        };
        consumer2.accept("听的人当真了，说的人当真了");
    }

    @Test
    public void test4() {
        // 4. 两个或以上参数，需要返回值
        Comparator<Integer> comparator = new Comparator<>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };
        Comparator<Integer> comparator2 = (o1, o2) -> {

            return o1.compareTo(o2);
        };
        System.out.println(comparator2.compare(1, 3));
    }


}


























