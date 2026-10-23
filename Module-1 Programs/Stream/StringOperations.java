import java.util.*;

class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList(
                "Aditya", "Aman", "Rahul", "Ananya",
                "Vineet", "Rishi", "Abhishek", "Neha"
        );

        System.out.println("All Names: " + names);

        System.out.println("Filter by Name:");
        names.stream()
                .filter(n -> n.equals("Aditya"))
                .forEach(System.out::println);

        System.out.println("Names starting with A:");
        names.stream()
                .filter(n -> n.startsWith("A"))
                .forEach(System.out::println);

        System.out.println("Names ending with a:");
        names.stream()
                .filter(n -> n.endsWith("a"))
                .forEach(System.out::println);

        System.out.println("Maximum Length 5:");
        names.stream()
                .filter(n -> n.length() <= 5)
                .forEach(System.out::println);

        System.out.println("Minimum Length 5:");
        names.stream()
                .filter(n -> n.length() >= 5)
                .forEach(System.out::println);

        System.out.println("Names containing 'an':");
        names.stream()
                .filter(n -> n.toLowerCase().contains("an"))
                .forEach(System.out::println);

        System.out.println("Names with Mishra:");
        names.stream()
                .map(n -> n + " Mishra")
                .forEach(System.out::println);

        System.out.println("Names starting with A + Mishra:");
        names.stream()
                .filter(n -> n.startsWith("A"))
                .map(n -> n + " Mishra")
                .forEach(System.out::println);

        List<Integer> numbers = Arrays.asList(2, 3, 4, 5, 6);

        System.out.println("All Numbers: " + numbers);

        System.out.println("Squares:");
        numbers.stream()
                .map(n -> n * n)
                .forEach(System.out::println);

        System.out.println("Squares greater than 10:");
        numbers.stream()
                .map(n -> n * n)
                .filter(n -> n > 10)
                .forEach(System.out::println);
    }
}