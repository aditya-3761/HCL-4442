public class Main{
    public static void main(String[] args){
        system.out.println("Hello");
        SingleEmployee obj1 = singleEmployee.makeInstance();
        SingleEmployee obj2 = singleEmployee.makeInstance();
        String msg = obj1.getMsg();
        System.out.println(msg);
        System.out.println(obj1.hashcode());
        System.out.println(obj1.hashcode());
    }
}