package com.mridnal.service;

import com.mridnal.entity.Book;
import com.mridnal.entity.LendingRecord;
import com.mridnal.entity.Patron;
import com.mridnal.enums.BookStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mridnal.service.InMemoryStore.logger;

public class LendingServiceImpl implements LendingService {

    private final List<LendingRecord> lendingRecords = new ArrayList<>();

    private PatronService patronService;
    private BookService bookService;
    private SearchBookService searchBookService;

    @Override
    public void checkoutBook(String patronId, String isbn) {

        Patron patron = patronService.getPatron(patronId);
        Book book = searchBookService.findBookByIsbn(isbn);

        if (!book.isAvailable()) {
            logger.warning("Checkout failed: '" + book.getTitle() + "' is already checked out.");
            return;
        }

        LendingRecord record = new LendingRecord(book, patron, LocalDateTime.now(), null);

        book.setAvailable(false);
        lendingRecords.add(record);

        logger.info("Loan ID " + record.getLoanId() + ": '" + book.getTitle() +
                "' checked out to " + patron.getName());
    }

    @Override
    public void returnBook(String patronId, String isbn) {
        boolean recordFound = false;

        for (LendingRecord record : lendingRecords) {

            boolean isCorrectPatron = record.getPatron().getPatronId().equals(patronId);
            boolean isCorrectBook = record.getBook().getIsbn().equals(isbn);
            boolean isActiveLoan = record.getReturnDate() == null;

            if (isCorrectPatron && isCorrectBook && isActiveLoan) {
                record.getBook().setAvailable(true);
                record.setReturnDate(LocalDateTime.now());

                logger.info("Success: '" + record.getBook().getTitle() +
                        "' returned by " + record.getPatron().getName());
                recordFound = true;
                break;
            }
        }

        if (!recordFound) {
            logger.warning("Return failed: No active loan record found for Patron ID "
                    + patronId + " and ISBN " + isbn);
        }
    }

    @Override
    public void showBorrowHistory(String patronId) {
        System.out.println("--- Borrowing History for Patron ID: " + patronId + " ---");
        boolean found = false;

        for (LendingRecord record : lendingRecords) {
            if (record.getPatron().getPatronId().equalsIgnoreCase(patronId)) {
                found = true;
                String status = (record.getReturnDate() == null)
                        ? BookStatus.BORROWED.toString()
                        : "Returned on " + record.getReturnDate();

                System.out.println("Loan ID: " +  record.getLoanId());
                System.out.println("Book: " +  record.getBook().getTitle());
                System.out.println("Checkout Date:  " +  record.getCheckoutDate());
                System.out.println("Return Date:  " +  record.getReturnDate());
                System.out.println("Status: " + status);
            }
        }

        if (!found) {
            System.out.println("No records found for this patron.");
        }
    }
}