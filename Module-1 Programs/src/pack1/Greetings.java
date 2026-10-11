package pack1;

public class Greetings {

    private String name;

    // Setter
    public void setName(String name) {
        this.name = name;
    }

    // Getter
    public String getName() {
        return name;
    }

    public void sayHello() {
        System.out.println("Hello " + name + " from Pack1");
    }
}