import java.util.*;
import java.util.stream.*;

class Employee {
    int id;
    String name;
    double salary;

    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public String toString() {
        return id + " " + name + " " + salary;
    }
}

class Main {
    public static void main(String[] args) {
        ArrayList<Employee> list = new ArrayList<>();

        list.add(new Employee(101, "Aditya", 30000));
        list.add(new Employee(102, "Aman", 45000));
        list.add(new Employee(103, "Rahul", 25000));
        list.add(new Employee(104, "Vineet", 50000));

        System.out.println("All Employees:");
        list.forEach(System.out::println);

        System.out.println("Salary above 30000:");
        list.stream()
                .filter(e -> e.salary > 30000)
                .forEach(System.out::println);

        System.out.println("Employee ID above 102:");
        list.stream()
                .filter(e -> e.id > 102)
                .forEach(System.out::println);

        System.out.println("Names starting with A:");
        list.stream()
                .filter(e -> e.name.startsWith("A"))
                .forEach(System.out::println);
    }
}
