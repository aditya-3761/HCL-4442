
package com.hcl.library.service;

import com.hcl.library.model.*;
import com.hcl.library.repository.LibraryRepository;
import com.hcl.library.exception.LibraryException;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class LibraryService {
    LibraryRepository repo;
    double fee = 5;

    public LibraryService(LibraryRepository repo) {
        this.repo = repo;
    }

    public void addItem(LibraryItem item) throws LibraryException {
        if (repo.getItems().containsKey(item.getId()))
            throw new LibraryException("Item already exists.");
        repo.getItems().put(item.getId(), item);
    }

    public void registerMember(Member m) throws LibraryException {
        if (repo.getMembers().containsKey(m.getId()))
            throw new LibraryException("Member already exists.");
        repo.getMembers().put(m.getId(), m);
    }

    public Collection<LibraryItem> getItems() {
        return repo.getItems().values();
    }

    public Collection<Member> getMembers() {
        return repo.getMembers().values();
    }

    public void issueItem(String id, String mid) throws LibraryException {
        LibraryItem item = repo.getItems().get(id);
        Member member = repo.getMembers().get(mid);

        if (item == null || member == null)
            throw new LibraryException("Invalid item or member ID.");

        if (!item.isAvailable()) {
            Queue<String> q = repo.getWaitingLists().get(id);
            if (q == null) {
                q = new LinkedList<>();
                repo.getWaitingLists().put(id, q);
            }
            if (!q.contains(mid)) q.add(mid);
            throw new LibraryException("Item borrowed. Waiting list updated.");
        }

        item.setAvailable(false);
        Loan loan = new Loan(item, member, LocalDate.now(),
                LocalDate.now().plusDays(item.getLoanDays()));
        repo.getActiveLoans().put(id, loan);
        repo.getLoanHistory().add(loan);
        System.out.println("Issued! Due date: " + loan.getDueDate());
    }

    public double returnItem(String id) throws LibraryException {
        Loan loan = repo.getActiveLoans().remove(id);
        if (loan == null) throw new LibraryException("Item not borrowed.");

        double lateFee = loan.close(LocalDate.now(), fee);
        loan.getItem().setAvailable(true);
        System.out.println("Late fee: Rs. " + lateFee);

        Queue<String> q = repo.getWaitingLists().get(id);
        if (q != null && !q.isEmpty()) {
            Member m = repo.getMembers().get(q.poll());
            if (m != null)
                System.out.println("Next in queue: " + m.getName());
        }
        return lateFee;
    }

    public void showWaitingList(String id) throws LibraryException {
        if (!repo.getItems().containsKey(id))
            throw new LibraryException("Item not found.");

        Queue<String> q = repo.getWaitingLists().get(id);
        if (q == null || q.isEmpty()) {
            System.out.println("Waiting list empty.");
            return;
        }

        for (String mid : q)
            System.out.println(repo.getMembers().get(mid).getName());
    }

    public void showLoans() {
        for (Loan l : repo.getActiveLoans().values())
            System.out.println(l.getItem().getTitle() + " | "
                    + l.getMember().getName() + " | Due: " + l.getDueDate());
    }

    public void exportReport(String file) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(file))) {
            out.println("ID,Type,Title,Available");
            for (LibraryItem i : repo.getItems().values())
                out.println(i.getId() + "," + i.getType() + ","
                        + i.getTitle() + "," + i.isAvailable());
        }
        System.out.println("Report exported!");
    }
}
