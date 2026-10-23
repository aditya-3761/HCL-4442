
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;

class ArrayListUpdate {

    // Method to update and return the list
    static ArrayList<Integer> updateList(
            ArrayList<Integer> list, int index, int value) {

        if (index >= 0 && index < list.size()) {
            list.set(index, value);
        } else {
            System.out.println("Invalid index!");
        }

        return list;
    }

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        // 1. Create an empty ArrayList
        ArrayList<Integer> list = new ArrayList<Integer>();

        System.out.println("Is list empty? " + list.isEmpty());

        // 2. Add 10 elements
        System.out.println("Enter 10 numbers:");

        for (int i = 0; i < 10; i++) {
            int num = sc.nextInt();
            list.add(num);
        }

        // 3. Display and check size
        System.out.println("Original list: " + list);
        System.out.println("Size: " + list.size());

        // 4. Get an element
        System.out.println("Element at index 0: " + list.get(0));

        // 5. Update an element and return the list
        System.out.println("Enter index to update (0-9):");
        int index = sc.nextInt();

        System.out.println("Enter new value:");
        int value = sc.nextInt();

        list = updateList(list, index, value);
        System.out.println("Updated list: " + list);

        // 6. Search using contains()
        System.out.println("Enter number to search:");
        int search = sc.nextInt();

        System.out.println("Contains number? " + list.contains(search));

        // 7. Find first and last occurrence
        System.out.println("First index: " + list.indexOf(search));
        System.out.println("Last index: " + list.lastIndexOf(search));

        // 8. Insert an element at a particular index
        list.add(0, 100);
        System.out.println("After add(index, value): " + list);

        // 9. Remove an element using its index
        list.remove(0);
        System.out.println("After remove(index): " + list);

        // 10. Remove an element using its value
        Integer number = Integer.valueOf(search);
        list.remove(number);
        System.out.println("After remove(value): " + list);

        // 11. Create another ArrayList
        ArrayList<Integer> extra = new ArrayList<Integer>();
        extra.add(200);
        extra.add(300);

        // 12. Add all elements of another list
        list.addAll(extra);
        System.out.println("After addAll(): " + list);

        // 13. Check whether all elements exist
        System.out.println("Contains all extra elements? "
                + list.containsAll(extra));

        // 14. Add all elements at a particular index
        list.addAll(0, extra);
        System.out.println("After addAll(index, collection): " + list);

        // 15. Remove all matching elements
        list.removeAll(extra);
        System.out.println("After removeAll(): " + list);

        // 16. Add elements again for retainAll()
        list.addAll(extra);

        // 17. Keep only elements present in extra
        list.retainAll(extra);
        System.out.println("After retainAll(): " + list);

        // Rebuild a list for the remaining examples
        list.clear();
        list.add(30);
        list.add(10);
        list.add(20);
        list.add(10);
        list.add(40);

        System.out.println("New list: " + list);

        // 18. Sort in ascending order
        Collections.sort(list);
        System.out.println("Sorted list: " + list);

        // 19. Reverse the current order
        Collections.reverse(list);
        System.out.println("Reversed list: " + list);

        // 20. Get a part of the list
        System.out.println("First two elements: " + list.subList(0, 2));

        // 21. Convert list to an Object array
        Object arr[] = list.toArray();

        System.out.print("Array: ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        // 22. Convert list to a typed Integer array
        Integer arr2[] = list.toArray(new Integer[0]);
        System.out.println("Integer array length: " + arr2.length);

        // 23. Iterate using Iterator
        Iterator<Integer> it = list.iterator();

        System.out.print("Using Iterator: ");
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();

        // 24. Iterate using ListIterator
        ListIterator<Integer> lit = list.listIterator();

        System.out.print("Using ListIterator: ");
        while (lit.hasNext()) {
            System.out.print(lit.next() + " ");
        }
        System.out.println();

        // 25. Compare two lists using equals()
        ArrayList<Integer> another = new ArrayList<Integer>();
        another.addAll(list);

        System.out.println("Lists equal? " + list.equals(another));

        // 26. Replace every element using replaceAll()
        list.replaceAll(n -> n * 2);
        System.out.println("After replaceAll(): " + list);

        // 27. Sort using sort()
        list.sort(null);
        System.out.println("After sort(): " + list);

        // 28. Remove elements using removeIf()
        list.removeIf(n -> n > 50);
        System.out.println("After removeIf(): " + list);

        // 29. Check size and emptiness
        System.out.println("Current size: " + list.size());
        System.out.println("Is list empty? " + list.isEmpty());

        // 30. Clear the list
        list.clear();

        System.out.println("After clear(): " + list);
        System.out.println("Final size: " + list.size());
        System.out.println("Is list empty now? " + list.isEmpty());

        sc.close();
    }
}
