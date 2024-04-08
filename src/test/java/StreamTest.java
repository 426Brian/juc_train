import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * ClassName: StreamTest
 * Pacage: PACKAGE_NAME
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/4/8-10:39
 * Version: V1.0
 */
public class StreamTest {

    public static Stream<Character> stringToChar(String str) {
        ArrayList<Character> characters = new ArrayList<>();

        for (Character ch : str.toCharArray()) {
            characters.add(ch);
        }
        return characters.stream();
    }

    @Test
    public void test1() {
        int[] arr_int = new int[]{1, 2, 3};
        IntStream stream = Arrays.stream(arr_int);
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);

        Stream.iterate(0, t -> t + 2).limit(10).forEach(System.out::println);

        Stream.generate(Math::random).limit(15).forEach(System.out::println);
    }

    @Test
    public void test2() {
        List<Employee> list = EmployeeData.list();

        list.stream().filter(e -> e.getSalary() > 7000).forEach(System.out::println);

        list.stream().limit(3).forEach(System.out::println);

        list.stream().skip(3).forEach(System.out::println);

        list.add(new Employee(1010, "刘强东", 40, 8000));
        list.add(new Employee(1010, "刘强东", 40, 8000));
        list.add(new Employee(1010, "刘强东", 40, 8000));
        list.add(new Employee(1010, "刘强东", 40, 8000));
        System.out.println("**********************");
        list.stream().distinct().forEach(System.out::println);

    }

    @Test
    public void test3() {
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");
//        list.stream().map(str -> str.toUpperCase()).forEach(System.out::println);

        Stream<Stream<Character>> char_stream = list.stream().map(StreamTest::stringToChar);
        char_stream.forEach(stream_a -> {
            stream_a.forEach(System.out::println);
        });
    }

    @Test
    public void test4() {
        List<Employee> list = EmployeeData.list();
        Stream<String> stream_names = list.stream().map(Employee::getName);
        stream_names.filter(name -> name.length() > 3).forEach(System.out::println);
    }

    @Test
    public void test5() {
        ArrayList list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);

        ArrayList list2 = new ArrayList();
        list2.add(4);
        list2.add(5);
        list2.add(6);

//        list.add(list2)
//        list.addAll(list2);
        System.out.println(list);
    }

    @Test
    public void test6() {
        List<Employee> list = EmployeeData.list();
//        list.stream().sorted().forEach(System.out::println);
        list.stream().sorted((e1, e2) ->{
                    int value = Integer.compare(e1.getAge(), e2.getAge());
                    if(value !=0){
                        return value;
                    }else {
                        return Double.compare(e1.getSalary(),e2.getSalary());
                    }
                }

        ).forEach(System.out::println);
    }

    @Test
    public void test7() {
        /**
         * allMatch: 所有元素符合条件
         * anyMatch: 是否有元素符合条件
         * noneMatch: 是否没有元素符合条件
         */
        List<Employee> list = EmployeeData.list();
        // allMatch: 所有元素符合条件
//        list.stream().allMatch(employee -> employee.getSalary() > 5000.0);
        // 是否有元素符合条件
        System.out.println(list.stream().anyMatch(employee -> employee.getSalary() > 5000.0));

        list.stream().filter(emp -> emp.getSalary() == list.stream().map(employee -> employee.getSalary()).min(Double::compare).get()).forEach(System.out::println);

        System.out.println(list.stream().filter(emp -> emp.getSalary() > 5000.0).collect(Collectors.toList()));
    }

    @Test
    public void test8() {
        Employee employee = null;
//        System.out.println(Optional.of(employee));
        // 防止空指针异常
        System.out.println(Optional.ofNullable(employee).orElse(new Employee(1001, "马化腾", 34, 6000.38)));

    }
}












