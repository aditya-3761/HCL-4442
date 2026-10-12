@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}

@FunctionalInterface
interface OddEven {
    boolean check(int n);
}

class Main {
    public static void main(String[] args) {
        Calculator add = (a, b) -> a + b;
        Calculator sub = (a, b) -> a - b;
        Calculator mul = (a, b) -> a * b;
        Calculator div = (a, b) -> a / b;
        Calculator mod = (a, b) -> a % b;

        System.out.println("Addition: " + add.calculate(10, 5));
        System.out.println("Subtraction: " + sub.calculate(10, 5));
        System.out.println("Multiplication: " + mul.calculate(10, 5));
        System.out.println("Division: " + div.calculate(10, 5));
        System.out.println("Modulus: " + mod.calculate(10, 5));

        OddEven oe = n -> n % 2 == 0;

        System.out.println("10 is even: " + oe.check(10));
        System.out.println("7 is even: " + oe.check(7));
    }
}