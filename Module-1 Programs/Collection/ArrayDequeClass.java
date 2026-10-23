
package Collection;
import java.util.*;
public class ArrayDequeClass {
    public static void main(String[] args) {
        ArrayDeque<Integer> dq = new ArrayDeque<Integer>();
        dq.offer(1);
        dq.offer(2);
        dq.offer(3);
        dq.offer(4);
        System.out.println(dq);
    }
}
