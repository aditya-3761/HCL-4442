import java.util.*;

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
        list.add(new Employee(103, "Aditya", 30000));
        list.add(new Employee(101, "Vineet", 40000));
        list.add(new Employee(102, "Aman", 35000));
        list.add(new Employee(104, "Rishi", 27000));

        Iterator<Employee> it = list.iterator();
        while (it.hasNext())
            System.out.println(it.next());

        list.get(0).name = "Rishi";
        it = list.iterator();
        while (it.hasNext()) {
            if (it.next().id == 104) {
                it.remove();
                break;
            }
        }

        ListIterator<Employee> li = list.listIterator();
        while (li.hasNext())
            System.out.println(li.next());
        while (li.hasPrevious())
            System.out.println(li.previous());

        list.sort(Comparator.comparingInt((Employee e) -> e.id));
        System.out.println("Ascending: " + list);

        list.sort(Comparator.comparingInt((Employee e) -> e.id));
        System.out.println("Descending: " + list);
    }
}