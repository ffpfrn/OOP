package MiniProjects;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

class BookNotAvailableException extends Exception {
    public BookNotAvailableException(String message) {
        super(message);
    }
}

class InvalidReturnException extends Exception {
    public InvalidReturnException(String message) {
        super(message);
    }
}

class Book {
    private String id;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void setIssued(boolean issued) {
        isIssued = issued;
    }

    @Override
    public String toString() {
        return "Book ID: " + id + ", Title: " + title + ", Author: " + author + ", Issued: " + (isIssued ? "Yes" : "No");
    }
}

class Member {
    private String memberId;
    private String name;
    private List<String> borrowedBookIds;

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.borrowedBookIds = new ArrayList<>();
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getBorrowedBookIds() {
        return borrowedBookIds;
    }

    public void setBorrowedBookIds(List<String> borrowedBookIds) {
        this.borrowedBookIds = borrowedBookIds;
    }

    public void borrowBook(String bookId) {
        borrowedBookIds.add(bookId);
    }

    public void returnBook(String bookId) {
        borrowedBookIds.remove(bookId);
    }

    @Override
    public String toString() {
        return "Member ID: " + memberId + ", Name: " + name + ", Borrowed Books: " + borrowedBookIds;
    }
}

class Library {
    private HashMap<String, Book> inventory;
    private HashMap<String, Member> members;

    public Library() {
        this.inventory = new HashMap<>();
        this.members = new HashMap<>();
    }

    public void addBook(Book book) {
        inventory.put(book.getId(), book);
        logOperation("Added book: " + book.getTitle());
    }

    public void addMember(Member member) {
        members.put(member.getMemberId(), member);
        logOperation("Added member: " + member.getName());
    }

    public void issueBook(String bookId, String memberId) throws BookNotAvailableException {
        Book book = inventory.get(bookId);
        Member member = members.get(memberId);

        if (book == null) throw new BookNotAvailableException("Book not found in inventory.");
        if (book.isIssued()) throw new BookNotAvailableException("Book is already issued.");
        if (member == null) throw new BookNotAvailableException("Member not found.");

        book.setIssued(true);
        member.borrowBook(bookId);
        logOperation("Issued book: " + book.getTitle() + " to member: " + member.getName());
    }

    public void returnBook(String bookId, String memberId, int daysLate) throws InvalidReturnException {
        Book book = inventory.get(bookId);
        Member member = members.get(memberId);

        if (book == null) throw new InvalidReturnException("Book not found in inventory.");
        if (!book.isIssued()) throw new InvalidReturnException("Book is not issued.");
        if (member == null) throw new InvalidReturnException("Member not found.");
        if (!member.getBorrowedBookIds().contains(bookId)) throw new InvalidReturnException("This member did not borrow this book.");

        book.setIssued(false);
        member.returnBook(bookId);
        double fine = daysLate * 2.0;
        logOperation("Returned book: " + book.getTitle() + " by member: " + member.getName() + ". Fine: ₹" + fine);
    }

    public void logOperation(String message) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("library_log.txt", true))) {
            writer.println(new Date() + ": " + message);
        } catch (IOException e) {
            System.out.println("Error logging operation: " + e.getMessage());
        }
    }

    public void showInventory() {
        if (inventory.isEmpty()) {
            System.out.println("No books in inventory.");
        } else {
            for (Book book : inventory.values()) {
                System.out.println(book);
            }
        }
    }
}

public class LibraryManagementSystem{
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add a book");
            System.out.println("2. Add a member");
            System.out.println("3. Issue a book");
            System.out.println("4. Return a book");
            System.out.println("5. Show inventory");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter book ID: ");
                    String bookId = scanner.nextLine();
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    Book book = new Book(bookId, title, author);
                    library.addBook(book);
                    System.out.println("Book added successfully.");
                    break;

                case 2:
                    System.out.print("Enter member ID: ");
                    String memberId = scanner.nextLine();
                    System.out.print("Enter member name: ");
                    String name = scanner.nextLine();
                    Member member = new Member(memberId, name);
                    library.addMember(member);
                    System.out.println("Member added successfully.");
                    break;

                case 3:
                    System.out.print("Enter book ID to issue: ");
                    String issueBookId = scanner.nextLine();
                    System.out.print("Enter member ID: ");
                    String issueMemberId = scanner.nextLine();
                    try {
                        library.issueBook(issueBookId, issueMemberId);
                        System.out.println("Book issued successfully.");
                    } catch (BookNotAvailableException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Enter book ID to return: ");
                    String returnBookId = scanner.nextLine();
                    System.out.print("Enter member ID: ");
                    String returnMemberId = scanner.nextLine();
                    System.out.print("Enter days late: ");
                    int daysLate = scanner.nextInt();
                    scanner.nextLine();
                    try {
                        library.returnBook(returnBookId, returnMemberId, daysLate);
                        System.out.println("Book returned successfully.");
                    } catch (InvalidReturnException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 5:
                    library.showInventory();
                    break;

                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
