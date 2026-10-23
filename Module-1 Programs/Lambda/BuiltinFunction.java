import java.util.function.*;

class Main {
    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        BiFunction<Integer, Integer, Integer> sub = (a, b) -> a - b;
        BiFunction<Integer, Integer, Integer> mul = (a, b) -> a * b;
        BiFunction<Integer, Integer, Integer> div = (a, b) -> a / b;

        System.out.println("Addition: " + add.apply(10, 5));
        System.out.println("Subtraction: " + sub.apply(10, 5));
        System.out.println("Multiplication: " + mul.apply(10, 5));
        System.out.println("Division: " + div.apply(10, 5));

        Predicate<Integer> even = n -> n % 2 == 0;
        Predicate<Integer> odd = n -> n % 2 != 0;
        System.out.println("Even: " + even.test(10));
        System.out.println("Odd: " + odd.test(7));

        Supplier<Integer> supply = () -> 100;
        System.out.println("Supplier: " + supply.get());

        Function<Integer, String> result = n -> "Number: " + n;
        System.out.println(result.apply(50));

        Consumer<String> print = s -> System.out.println("Consumer: " + s);
        print.accept("Hello Java");
    }
}