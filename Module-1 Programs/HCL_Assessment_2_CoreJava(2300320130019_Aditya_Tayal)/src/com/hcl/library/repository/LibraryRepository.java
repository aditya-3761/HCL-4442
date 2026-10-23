
package com.hcl.library.repository;

import com.hcl.library.model.*;
import java.util.*;

public class LibraryRepository {
    private final Map<String, LibraryItem> items = new LinkedHashMap<>();
    private final Map<String, Member> members = new LinkedHashMap<>();
    private final Map<String, Loan> activeLoans = new LinkedHashMap<>();
    private final List<Loan> loanHistory = new ArrayList<>();
    private final Map<String, Queue<String>> waitingLists = new LinkedHashMap<>();

    public Map<String, LibraryItem> getItems() {
        return items;
    }

    public Map<String, Member> getMembers() {
        return members;
    }

    public Map<String, Loan> getActiveLoans() {
        return activeLoans;
    }

    public List<Loan> getLoanHistory() {
        return loanHistory;
    }

    public Map<String, Queue<String>> getWaitingLists() {
        return waitingLists;
    }
}
