
package Collection;

import java.util.*;

public class PriorityQueueClass {
    public static void main(String[] args) {

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

        // 1. Add elements
        pq.add(30);
        pq.add(10);
        pq.add(40);
        pq.add(20);

        System.out.println("Priority Queue: " + pq);

        // 2. Offer elements
        pq.offer(5);
        System.out.println("After offer: " + pq);

        // 3. Peek - view the highest priority element
        System.out.println("Peek: " + pq.peek());

        // 4. Element - view the head element
        System.out.println("Element: " + pq.element());

        // 5. Poll - remove the highest priority element
        System.out.println("Poll: " + pq.poll());
        System.out.println("After poll: " + pq);

        // 6. Remove the head element
        System.out.println("Remove: " + pq.remove());
        System.out.println("After remove: " + pq);

        // 7. Check size
        System.out.println("Size: " + pq.size());

        // 8. Check whether an element exists
        System.out.println("Contains 30: " + pq.contains(30));

        // 9. Check whether queue is empty
        System.out.println("Is empty: " + pq.isEmpty());

        // 10. Add multiple elements
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(50);
        list.add(60);

        pq.addAll(list);
        System.out.println("After addAll: " + pq);

        // 11. Convert queue to array
        Object arr[] = pq.toArray();

        System.out.print("Array elements: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        // 12. Remove a particular value
        pq.remove(Integer.valueOf(40));
        System.out.println("After removing 40: " + pq);

        // 13. Iterate through queue
        System.out.println("Queue elements:");
        Iterator<Integer> it = pq.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }

        // 14. Process elements in priority order
        System.out.println("Removing in priority order:");

        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }

        // 15. Clear queue
        pq.clear();

        System.out.println("After clear: " + pq);
        System.out.println("Final size: " + pq.size());
        System.out.println("Is empty now: " + pq.isEmpty());
    }
}
