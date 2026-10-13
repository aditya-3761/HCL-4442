import java.io.*;
class InvalidAgeException extends Exception {
    InvalidAgeException(String msg) {
        super(msg);
    }
}
class ExceptionHandling {
    static void checkAge(int age) throws InvalidAgeException {
        if (age < 18)
            throw new InvalidAgeException("Age must be 18 or above");
        System.out.println("Eligible to vote");
    }
    static void checkNumber() throws IOException {
        throw new IOException("File reading error");
    }
    public static void main(String[] args) {
        try {
            int a = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic: " + e.getMessage());
        }

        try {
            int[] arr = {10, 20, 30};
            System.out.println(arr[5]);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid array index");
        }
        catch (Exception e) {
            System.out.println("General exception");
        }

        try {
            int n = Integer.parseInt("abc");
        }
        catch (NumberFormatException e) {
            System.out.println("Invalid number format");
        }

        try {
            System.out.println("Inside try");
        }
        catch (Exception e) {
            System.out.println("Exception occurred");
        }
        finally {
            System.out.println("Finally always executes");
        }

        try {
            try {
                int n = 10 / 0;
            }
            catch (ArithmeticException e) {
                System.out.println("Nested exception handled");
            }
        }
        catch (Exception e) {
            System.out.println("Outer exception handled");
        }

        try {
            checkAge(16);
        }
        catch (InvalidAgeException e) {
            System.out.println("Custom exception: " + e.getMessage());
        }

        try {
            checkNumber();
        }
        catch (IOException e) {
            System.out.println("Checked exception: " + e.getMessage());
        }

        try (BufferedReader br =
                     new BufferedReader(new StringReader("Hello Java"))) {
            System.out.println(br.readLine());
        }
        catch (IOException e) {
            System.out.println("Reading error");
        }

        try {
            String s = null;
            System.out.println(s.length());
        }
        catch (NullPointerException e) {
            System.out.println("Null value accessed");
        }

        System.out.println("Program continues normally");
    }
}
