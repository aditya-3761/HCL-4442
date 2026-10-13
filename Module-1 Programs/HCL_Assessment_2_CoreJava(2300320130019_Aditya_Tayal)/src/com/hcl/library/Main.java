
package com.hcl.library;

import com.hcl.library.model.*;
import com.hcl.library.service.LibraryService;
import com.hcl.library.repository.LibraryRepository;
import com.hcl.library.exception.LibraryException;
import java.util.Scanner;
import java.io.IOException;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static LibraryService service =
            new LibraryService(new LibraryRepository());

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1.Add Item  2.Register Member");
            System.out.println("3.Show Items  4.Show Members");
            System.out.println("5.Issue Item  6.Return Item");
            System.out.println("7.Show Loans  8.Waiting List");
            System.out.println("9.Export Report  0.Exit");
            System.out.print("Enter choice: ");

            try {
                int ch = Integer.parseInt(sc.nextLine());

                switch (ch) {
                    case 1: addItem(); break;
                    case 2: registerMember(); break;
                    case 3:
                        for (LibraryItem i : service.getItems())
                            System.out.println(i);
                        break;
                    case 4:
                        for (Member m : service.getMembers())
                            System.out.println(m);
                        break;
                    case 5:
                        System.out.print("Item ID: ");
                        String itemId = sc.nextLine();
                        System.out.print("Member ID: ");
                        service.issueItem(itemId, sc.nextLine());
                        break;
                    case 6:
                        System.out.print("Item ID: ");
                        service.returnItem(sc.nextLine());
                        break;
                    case 7: service.showLoans(); break;
                    case 8:
                        System.out.print("Item ID: ");
                        service.showWaitingList(sc.nextLine());
                        break;
                    case 9:
                        service.exportReport("library-report.csv");
                        System.out.println("Report exported!");
                        break;
                    case 0:
                        System.out.println("Goodbye!");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (LibraryException | IOException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input!");
            }
        }
    }

    static void addItem() throws LibraryException {
        System.out.println("1.Book  2.Magazine  3.DVD");
        System.out.print("Type: ");
        int type = Integer.parseInt(sc.nextLine());

        System.out.print("ID: ");
        String id = sc.nextLine();
        System.out.print("Title: ");
        String title = sc.nextLine();

        LibraryItem item;

        switch (type) {
            case 1:
                System.out.print("Author: ");
                item = new Book(id, title, sc.nextLine());
                break;
            case 2:
                System.out.print("Issue number: ");
                item = new Magazine(id, title,
                        Integer.parseInt(sc.nextLine()));
                break;
            case 3:
                System.out.print("Duration in minutes: ");
                item = new DVD(id, title,
                        Integer.parseInt(sc.nextLine()));
                break;
            default:
                throw new IllegalArgumentException("Invalid item type");
        }

        service.addItem(item);
        System.out.println("Item added!");
    }

    static void registerMember() throws LibraryException {
        System.out.print("Member ID: ");
        String id = sc.nextLine();
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();

        service.registerMember(new Member(id, name, email));
        System.out.println("Member registered!");
    }
}
