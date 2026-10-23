
package com.hcl.library.model;

public class Magazine extends LibraryItem {
    private int issueNumber;

    public Magazine(String id, String title, int issueNumber) {
        super(id, title, ItemType.MAGAZINE);
        this.issueNumber = issueNumber;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public int getLoanDays() {
        return 7;
    }

    public String toString() {
        return super.toString() + " | Issue: " + issueNumber;
    }
}
