interface A{
    void m1();
}
interface B{
    void m2();
}
class C implements A, B{
    public void m1(){
        System.out.println("interface A method");
    }
    public void m2(){
        System.out.println("interface B method");
    }
}
class Main{
    public static void main(String[] args) {
        C obj = new C();

        obj.m1();
        obj.m2();
    }
}