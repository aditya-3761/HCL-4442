
package Collection;
import java.util.*;
public class StackClass {
    public static void main(String[] args) {
        ArrayDeque<Integer> stack = new ArrayDeque<Integer>();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        System.out.println(stack);
        System.out.println("Top element: " + stack.peek());
        System.out.println("Removed element: " + stack.pop());
        System.out.println("Stack after pop: " + stack);
        System.out.println("Stack size: " + stack.size());
        System.out.println("Is stack empty? " + stack.isEmpty());
    }
}
