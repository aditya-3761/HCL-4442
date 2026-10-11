package Collection;

import java.util.*;

public class Maps {
    public static void main(String[] args) {

        Map<Integer, String> mp = new HashMap<Integer, String>();

        // Add key-value pairs
        mp.put(1, "Sumit");
        mp.put(2, "Sujit");
        mp.put(3, "Abhi");
        mp.put(4, "Sam");
        mp.put(5, "Lee");

        // Print map
        System.out.println("Map: " + mp);

        // Get value using key
        System.out.println("Value at key 1: " + mp.get(1));

        // Check key
        System.out.println("Contains key 2: " + mp.containsKey(2));

        // Check value
        System.out.println("Contains value Sam: " + mp.containsValue("Sam"));

        // Print size
        System.out.println("Size: " + mp.size());

        // Update value
        mp.put(1, "Aditya");
        System.out.println("After update: " + mp);

        // Add only if key is absent
        mp.putIfAbsent(6, "Rahul");
        System.out.println("After putIfAbsent: " + mp);

        // Get value or default message
        System.out.println("Key 7: " + mp.getOrDefault(7, "Not Found"));

        // Replace value
        mp.replace(2, "Rohit");
        System.out.println("After replace: " + mp);

        // Remove key
        mp.remove(5);
        System.out.println("After remove: " + mp);

        // Print all keys
        System.out.println("Keys: " + mp.keySet());

        // Print all values
        System.out.println("Values: " + mp.values());

        // Print all key-value pairs
        System.out.println("Entries: " + mp.entrySet());

        // Iterate through map
        System.out.println("Printing key-value pairs:");

        for (Map.Entry<Integer, String> entry : mp.entrySet()) {
            System.out.println(
                    entry.getKey() + " = " + entry.getValue()
            );
        }

        // Copy entries from another map
        Map<Integer, String> mp2 = new HashMap<Integer, String>();
        mp2.put(7, "Neha");
        mp2.put(8, "Vikas");

        mp.putAll(mp2);
        System.out.println("After putAll: " + mp);

        // Check if map is empty
        System.out.println("Is empty? " + mp.isEmpty());

        // Remove all entries
        mp.clear();

        System.out.println("After clear: " + mp);
        System.out.println("Final size: " + mp.size());
        System.out.println("Is empty now? " + mp.isEmpty());
    }
}