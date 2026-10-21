class Main {
    private static class SingleObject {
        void display() {
            System.out.println("Hello from SingleObject!");
        }
    }
    public static void main(String[] args) {

        SingleObject obj1 = new SingleObject();
        SingleObject obj2 = new SingleObject();
        SingleObject obj3 = obj1;
        SingleObject obj4 = new SingleObject();

        obj1.display();
        obj2.display();
        obj3.display();
        obj4.display();

        System.out.println(obj1 == obj2);
        System.out.println(obj1 == obj3);
        System.out.println(obj1 == obj4);
        System.out.println(obj2 == obj4);

        System.out.println(obj1.hashCode());
        System.out.println(obj2.hashCode());
        System.out.println(obj3.hashCode());
        System.out.println(obj4.hashCode());
    }
}
