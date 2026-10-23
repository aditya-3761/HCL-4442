package pack2;

import pack1.Greetings;

public class Main {

    public static void main(String[] args) {

        Greetings obj = new Greetings();

        // Setter
        obj.setName("Aditya");

        // Getter
        System.out.println("Name: " + obj.getName());

        // Method
        obj.sayHello();
    }
}