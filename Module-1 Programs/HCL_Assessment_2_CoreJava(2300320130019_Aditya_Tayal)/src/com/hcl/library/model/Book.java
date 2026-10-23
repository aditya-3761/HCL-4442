package com.hcl.library.model;

public class Book extends LibraryItem {
    String author;

    public Book(String id, String title, String author) {
        super(id, title, ItemType.BOOK);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public int getLoanDays() {
        return 14;
    }

    public String toString() {
        return super.toString() + " | Author: " + author;
    }
}