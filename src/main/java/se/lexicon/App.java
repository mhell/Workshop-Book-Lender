package se.lexicon;

import se.lexicon.model.*;

public class App {
    public static void main(String[] args) {
        // Create a book instance
        Book book = new Book("Pride and Prejudice", "Jane Austin");
        // Display book information
        System.out.println(book);

        // Create a person instance
        Person person = new Person("Nisse", "Nilsson");
        // Display person information
        System.out.println(person);

        System.out.println("Loan a book:");

        // Loan a book to the person
        person.loanBook(book);
        // Display book information after borrowing a book
        System.out.println(book);
        // Display person information after borrowing a book
        System.out.println(person);

        System.out.println("Return a book:");

        // Return the borrowed book
        person.returnBook(book);
        // Display book information after borrowing a book
        System.out.println(book);
        // Display person information after returning the book
        System.out.println(person);
    }

}
