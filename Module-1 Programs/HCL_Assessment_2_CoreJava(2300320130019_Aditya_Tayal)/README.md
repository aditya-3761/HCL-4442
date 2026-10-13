# Core Java Library Management System

Console-based college library application using Core Java only (no external dependencies).

## Features
- Add Books, Magazines and DVDs
- Register members
- Issue and return items
- Calculate late fees (₹5 per overdue day)
- Maintain a FIFO waiting list for borrowed items
- Export a CSV library report
- Input validation and custom exceptions
- OOP, inheritance, abstraction, encapsulation, collections, enums, `LocalDate`, and file handling

## Requirements
- JDK 8 or later (uses `java.time`, available since Java 8)
- IntelliJ IDEA, Eclipse, or terminal

## Run from the project root
Compile:
```bash
javac -d out src/com/hcl/library/Main.java src/com/hcl/library/model/*.java src/com/hcl/library/exception/*.java src/com/hcl/library/repository/*.java src/com/hcl/library/service/*.java
```

Run:
```bash
java -cp out com.hcl.library.Main
```

In IntelliJ, open the `Assignment` folder, mark `src` as Sources Root if needed, then run `Main.java`.

## Default rules
- Book loan period: 14 days
- Magazine loan period: 7 days
- DVD loan period: 7 days
- Late fee: ₹5 per overdue day
- One active loan per item; waiting list is first-in, first-out (FIFO).
- Returning an item automatically offers it to the next waiting member. That member must issue it from the menu; the item is marked available until issued. (The waiting member is announced.)
- Report is exported to `library-report.csv` in the current working directory.

## Project structure
```text
Assignment/
├── README.md
├── .gitignore
└── src/com/hcl/library/
    ├── Main.java
    ├── model/
    ├── exception/
    ├── repository/
    └── service/
```
