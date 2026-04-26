package com.mridnal.service;

import com.mridnal.entity.Book;
import com.mridnal.exception.BookNotFoundException;
import com.mridnal.exception.InvalidInputException;
import com.mridnal.exception.LibraryOperationException;
import com.mridnal.util.InputValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.mridnal.service.InMemoryStore.inventory;

public class BookServiceImpl implements BookService{

    private void validateBookFields(String isbn, String title, String author, String year) {
        if (isbn == null || title == null || author == null) {
            throw new InvalidInputException("Fields cannot be null");
        }
        if (!InputValidator.isValidName(title)) {
            throw new InvalidInputException("Book title is invalid");
        }
        if (!InputValidator.isValidName(author)) {
            throw new InvalidInputException("Book author is invalid");
        }
        if(!InputValidator.isValidYear(year)) {
            throw new InvalidInputException("Publication year is invalid");
        }
    }

    @Override
    public void addBook(String isbn, String title, String author, String year) {
        validateBookFields(isbn, title, author, year);

        if (inventory.containsKey(isbn)) {
            throw new LibraryOperationException("Book with ISBN " + isbn + " already exists.");
        }

        Book book = new Book(isbn, title, author, year);
        inventory.put(isbn, book);
    }

    @Override
    public void removeBook(String isbn) {
        if (isbn == null || !inventory.containsKey(isbn)) {
            throw new BookNotFoundException("Book not found for ISBN: " + isbn);
        }
        try{
            inventory.remove(isbn);
        } catch (LibraryOperationException e) {
            throw e;
        }
    }

    @Override
    public void updateBook(Book book) {
        if (book == null || !inventory.containsKey(book.getIsbn())) {
            throw new BookNotFoundException("Cannot update: Book does not exist.");
        }

     validateBookFields(book.getIsbn(), book.getTitle(), book.getAuthor(), book.getPublicationYear());
        inventory.put(book.getIsbn(), book);
    }

}
