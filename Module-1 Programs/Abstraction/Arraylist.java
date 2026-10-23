
import java.util.ArrayList;
import java.util.Scanner;

class ArrayListUpdate {
    static ArrayList<Integer> updateList(ArrayList<Integer> list, int index, int value) {
        list.set(index, value);
        return list;
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<Integer>();
        System.out.println("Enter 10 elements:");
        for (int i = 0; i < 10; i++) {
            int num = sc.nextInt();
            list.add(num);
        }
        System.out.println("Original ArrayList: " + list);
        System.out.println("Enter index to update (0-9):");
        int index = sc.nextInt();

        System.out.println("Enter new value:");
        int value = sc.nextInt();

        if (index >= 0 && index < list.size()) {
            list = updateList(list, index, value);
            System.out.println("Updated ArrayList: " + list);
        } else {
            System.out.println("Invalid index!");
        }
        sc.close();
    }
}
