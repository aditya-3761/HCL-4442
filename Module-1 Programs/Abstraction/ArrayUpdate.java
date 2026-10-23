
import java.util.Scanner;
class ArrayUpdate {
    static int[] updateArray(int arr[], int index, int value) {
        arr[index] = value;
        return arr;
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int arr[] = new int[10];
        System.out.println("Enter 10 elements:");
        for (int i = 0; i < 10; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println("Enter index to update:");
        int index = sc.nextInt();

        System.out.println("Enter new value:");
        int value = sc.nextInt();

        arr = updateArray(arr, index, value);

        System.out.println("Updated array:");

        for (int i = 0; i < 10; i++) {
            System.out.print(arr[i] + " ");
        }
        sc.close();
    }
}
