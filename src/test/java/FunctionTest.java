import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * ClassName: FunctionTest
 * Pacage: PACKAGE_NAME
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/4/8-9:49
 * Version: V1.0
 */

/**
 * 消费型接口 Consumer<T> void accept(T t)
 * 供给型接口 Supplier<T>  T get()
 * 供给型接口 Function<T, R> R apply(T,R)
 * 供给型接口 Predicate<T> boolean test(T,R)
 */

public class FunctionTest {
    @Test
    public void test1() {
        // 只包含一个抽象方法的接口叫函数式接口 例如 Runnable
        happyTime(500, (money) ->
                System.out.println("娱乐消遣消费" + money + "元"));
    }

    public void happyTime(double money, Consumer<Double> con) {
        con.accept(money);
    }

    @Test
    public void test2() {
        List<String> arr_str = Arrays.asList("东京", "西京", "南京", "北京");
        List<String> filter_str = filter(arr_str, (s) -> s.contains("京"));
        System.out.println(filter_str);
    }

    public List<String> filter(List<String> list, Predicate<String> predicate) {
        ArrayList<String> arr_str = new ArrayList<>();
        for (String s : arr_str) {
            if (predicate.test(s)) {
                arr_str.add(s);
            }
        }
        return list;
    }

    @Test
    public void test3() {
        // 方法引用
        PrintStream ps = System.out;
        Consumer<String> consumer = ps::println;
        consumer.accept("北京");
    }

    @Test
    public void test4() {
        // 方法引用
        Supplier<String> supplier = ()-> String.valueOf("a");
        System.out.println(supplier.get());
    }
    @Test
    public void test5() {
        // 方法引用
        BiPredicate<String, String> biPredicate = (s1, s2)-> s1.equals(s2);
        System.out.println(biPredicate.test("a","b"));
    }
    @Test
    public void test6() {
        // 构造器引用
        Consumer<String> consumer = String::new;
        consumer.accept("a");

    }
}





























