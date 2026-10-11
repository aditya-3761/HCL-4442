abstract class A{
    abstract void m1();
    abstract int m2();
}
class B extends A{
    void m1(){
        System.out.println("m1 method");
    }
    int m2() {
        return 10;
    }
}
class Main {
    public static void main(String[] args) {
        A obj = new B();

        obj.m1();
        System.out.println(obj.m2());
    }
}