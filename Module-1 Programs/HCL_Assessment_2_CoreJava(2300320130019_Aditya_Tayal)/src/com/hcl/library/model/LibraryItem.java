
package com.hcl.library.model;

public abstract class LibraryItem {
    private String id;
    private String title;
    private ItemType type;
    private boolean available = true;

    public LibraryItem(String id, String title, ItemType type) {
        this.id = id;
        this.title = title;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public ItemType getType() {
        return type;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public abstract int getLoanDays();

    public String toString() {
        return id + " | " + type + " | " + title + " | " +
                (available ? "Available" : "Borrowed");
    }
}
