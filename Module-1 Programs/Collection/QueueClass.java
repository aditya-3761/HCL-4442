package Collection;
import java.util.*;
public class QueueClass{
    public static void main(String[] args){
        Queue<Integer> q=new LinkedList<>();
        q.offer( 1);
        q.offer( 2);
        q.offer( 3);
        q.offer( 4);
        System.out.println(q);
    }
}