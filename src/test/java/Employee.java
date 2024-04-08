import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * ClassName: Employee
 * Pacage: PACKAGE_NAME
 * Discription:
 *
 * @Author: Brian
 * @Create: 2024/4/8-10:53
 * Version: V1.0
 */

public class Employee implements Comparable {

    private int id;
    private String name;
    private int age;

    private double salary;

    public Employee(int id, String name, int age, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
    @Override
    public int compareTo(Object o) {
        boolean b = this.getId() > ((Employee) o).getId();
        if (b) return 1;
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (id != employee.id) return false;
        if (age != employee.age) return false;
        if (Double.compare(salary, employee.salary) != 0) return false;
        return Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + age;
        temp = Double.doubleToLongBits(salary);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }


}

class EmployeeData {
    public static List<Employee> list() {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1001, "马化腾", 34, 6000.38));
        employees.add(new Employee(1008, "扎克伯格", 35, 2500.32));
        employees.add(new Employee(1002, "马云", 12, 9876.12));
        employees.add(new Employee(1004, "雷军", 26, 7657.37));
        employees.add(new Employee(1005, "李彦宏", 65, 5555.32));
        employees.add(new Employee(1003, "刘强东", 33, 3000.82));
        employees.add(new Employee(1006, "比尔盖茨", 42, 9500.43));
        employees.add(new Employee(1007, "任正非", 26, 4333.32));
        return employees;
    }
}