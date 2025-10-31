package se.lexicon.model;

import java.util.Arrays;
import java.util.Comparator;

public class Person {
    private static int sequencer = 0;
    private final int id;
    private String firstName;
    private String lastName;
    private Book[] borrowed = new Book[0];

    public Person(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
        id = Person.getNextId();
    }

    private static int getNextId() {
        return sequencer++;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Book[] getBorrowed() {
        return borrowed;
    }

    private void setBorrowed(Book[] borrowed) {
        this.borrowed = borrowed;
    }

    public void loanBook(Book book) {
        // Check if book is available
        if (book.isAvailable()) {
            // Add person as the books borrower
            book.setBorrower(this);
            // Add book to persons list of borrowed books
            Book[] newBorrowed = Arrays.copyOf(borrowed, borrowed.length + 1);
            newBorrowed[newBorrowed.length-1] = book;
            setBorrowed(newBorrowed);
        } else {
            throw new RuntimeException("The book is not available");
        }
    }

    public void returnBook (Book book) {
        // search borrowed books for 'book'. Needs to sorted
        Arrays.sort(borrowed, Comparator.comparing(Book::getId));
        int foundPos = Arrays.binarySearch(borrowed, book, Comparator.comparing(Book::getId));

        // if not found, throw an exception (the book was never borrowed)
        if (foundPos < 0) {
            throw new RuntimeException("Book was not borrowed");
        } else {
            // remove borrower from the book (sets it to available)
            book.setBorrower(null);
            // create a new borrowed array with -1 length
            Book[] newBorrowed = Arrays.copyOf(borrowed, borrowed.length - 1);
            // if found at the end, just reassign borrowed to the new array
            if (foundPos == borrowed.length - 1) {
                setBorrowed(newBorrowed);
            } else {
                // remove found book from newBorrowed by merging with offset
                System.arraycopy(borrowed, foundPos + 1, newBorrowed, foundPos, borrowed.length - foundPos);
                // reassign borrowed
                setBorrowed(newBorrowed);
            }
        }
    }

    public String toString() {
        return getPersonInformation();
    }

    public String getPersonInformation() {
        return String.format("Person ID: %d, First name: %s, last name: %s, borrowed: %s",
                getId(), getFirstName(), getLastName(),
                getBorrowed().length == 0 ? "none" : Arrays.toString(getBorrowed()));
    }
}