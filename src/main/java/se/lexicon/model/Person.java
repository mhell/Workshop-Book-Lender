package se.lexicon.model;

import java.util.Arrays;

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
        Book[] newBorrowed = new Book[borrowed.length];
        // copy all elements of borrowed except this book
        for (int i=0, j=0; i < borrowed.length; i++) {
            // copy all elements from borrowed, except if element.id == book.id
            if (!borrowed[i].getId().equals(book.getId())) {
                newBorrowed[j] = borrowed[i];
                j++;
            }
        }
        // if successfully found and removed the book...
        if (newBorrowed[newBorrowed.length-1] == null) {
            // remove borrower from the book (sets it to available)
            book.setBorrower(null);
            // change length of newBorrowed -1
            newBorrowed = Arrays.copyOf(newBorrowed, newBorrowed.length-1);
            // reassign the borrowed array
            borrowed = newBorrowed;
        // else throw an exception (the book was never borrowed)
        } else {
            throw new RuntimeException("Book was not borrowed");
        }
    }

    public String getPersonInformation() {
        return String.format("Person ID: %d, First name: %s, last name: %s, borrowed: %s",
                getId(), getFirstName(), getLastName(),
                getBorrowed().length == 0 ? "none" : Arrays.toString(getBorrowed()));
    }
}