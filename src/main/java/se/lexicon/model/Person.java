package se.lexicon.model;

import java.rmi.server.UID;
import java.util.List;
import java.util.UUID;

public class Person {
    private static int sequencer = 0;
    private final int id;
    private String firstName;
    private String lastName;
    private Book[] books = new Book[0];

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

    public Book[] getBooks() {
        return books;
    }

    private void setBooks(Book[] books) {
        this.books = books;
    }

    public void loanBook() {

    }

    public String getPersonInformation() {
        return "";
    }
}