public class SingleEmployee{

    static final SingleEmployee obj = new SingleEmployee();

    private SingleEmployee(){

    }
    public static SingleEmployee makeInstance(){
        return obj;
    }
    public String getMsg(){
        return "hey Java Developer";
    }
}