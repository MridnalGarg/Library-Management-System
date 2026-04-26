package com.mridnal.service;

import com.mridnal.entity.Book;
import com.mridnal.exception.BookNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

import static com.mridnal.service.InMemoryStore.inventory;

public class SearchBookServiceImpl implements SearchBookService {


    @Override
    public List<Book> findBooksByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Search title cannot be empty");
        }

        List<Book> results = inventory.values().stream()
                .filter(b -> b.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            throw new BookNotFoundException("No books found containing: " + title);
        }
        return results;
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        Book book = inventory.get(isbn);
        if (book == null) {
            throw new BookNotFoundException("Book with ISBN " + isbn + " was not found.");
        }
        return book;
    }

    @Override
    public List<Book> findAllBooksAvailable(){
        if (inventory.isEmpty()) {
            throw new BookNotFoundException("No books available");
        }
        return inventory.values().stream().filter(Book::isAvailable).collect(Collectors.toList());
    }
}
