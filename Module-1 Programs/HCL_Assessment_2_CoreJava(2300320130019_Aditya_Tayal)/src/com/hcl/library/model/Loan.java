
package com.hcl.library.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Loan {
    private LibraryItem item;
    private Member member;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private double lateFee;

    public Loan(LibraryItem item, Member member,
                LocalDate issueDate, LocalDate dueDate) {
        this.item = item;
        this.member = member;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
    }

    public LibraryItem getItem() {
        return item;
    }

    public Member getMember() {
        return member;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public double getLateFee() {
        return lateFee;
    }

    public boolean isActive() {
        return returnDate == null;
    }

    public double close(LocalDate date, double feePerDay) {
        if (returnDate == null) {
            returnDate = date;

            long days = ChronoUnit.DAYS.between(dueDate, date);

            if (days > 0) {
                lateFee = days * feePerDay;
            }
        }

        return lateFee;
    }
}
