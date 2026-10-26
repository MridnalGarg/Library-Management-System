package com.mridnal.main;

import com.mridnal.entity.Book;
import com.mridnal.exception.BookNotFoundException;
import com.mridnal.service.*;

import java.util.List;
import java.util.Scanner;

import static com.mridnal.service.InMemoryStore.logger;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final PatronService patronService = new PatronServiceImpl();
    private static final BookService bookService = new BookServiceImpl();
    private static final SearchBookService searchBook = new SearchBookServiceImpl();
    private static final LendingService lendingService = new LendingServiceImpl();

    public static void main(String[] args) {
        System.out.println("Welcome to Library Management System");

        while(true) {
            displayMainMenu();
            System.out.print("Enter your choice -> ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1:
                    handleBookMenu();
                    break;
                case 2:
                     handlePatronMenu();
                    break;
                case 3:
                    showAvailableBooks();
                    scanner.nextLine();
                    break;
                case 4:
                    handleSearchABook();
                    break;
                case 5:
                    handleLendABook();
                    break;
                case 6:
                    returnABook();
                    break;
                case 7:
                    showBorrowHistory();
                    break;
                case 0:
                    scanner.close();
                    System.out.println("Closing the program...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void holdScreen(){
        System.out.println("Press any key to continue...");
        scanner.nextLine();
    }

    public static void displayMainMenu(){
        System.out.println("---------------- Main Menu ----------------");
        System.out.println("1. Manage Book");
        System.out.println("2. Manage Patron");
        System.out.println("3. View available Books in Library");
        System.out.println("4. Search a book");
        System.out.println("5. Lend a book");
        System.out.println("6. Return a book");
        System.out.println("7. View Borrow History");
        System.out.println("0. Exit");
    }

    public static void displayBookMenu(){
        System.out.println("---------------- Manage Books ----------------");
        System.out.println("1. Add Book");
        System.out.println("2. Edit Book Details");
        System.out.println("3. Remove Book");
        System.out.println("0. Go Back");
    }

    public static void displayBookEditMenu(Book book){
        System.out.println("---------------- Edit Books Details ----------------");
        System.out.println("1. Update Title");
        System.out.println("2. Update Author");
        System.out.println("3. Update Publication Year");
        System.out.println("4. Update Available Status");
        System.out.println("0. Go Back");
    }

    public static void displayPatronMenu(){
        System.out.println("--------------- Manage Patrons ----------------");
        System.out.println("1. Add Patron");
        System.out.println("2. Edit Patron Details");
        System.out.println("3. Remove Patron");
        System.out.println("0. Go Back");
    }

    public static void handlePatronMenu(){
        boolean back = false;
        while(!back){
            displayPatronMenu();
            System.out.println("Enter choice -> ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    addPatron();
                    break;
                case 2:
                    editPatron();
                    break;
                case 3:
                    removePatron();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    logger.info("You have entered an invalid choice.");
            }
        }
    }

    public static void addPatron(){
        System.out.println("Enter Patron Name: ");
        String name = scanner.nextLine();
        System.out.println("Enter Patron Email: ");
        String email = scanner.nextLine();
        patronService.createPatron(name, email);
    }

    public static void editPatron(){
        System.out.println("Enter Patron Id: ");
        String patronId =  scanner.nextLine();
        System.out.println("Enter Patron Name: ");
        String name = scanner.nextLine();
        System.out.println("Enter Patron Email: ");
        String email = scanner.nextLine();

        patronService.updatePatron(patronId, name, email);
    }

    public static void removePatron(){
        System.out.println("Enter Patron Id: ");
        String patronId = scanner.nextLine();
        patronService.removePatron(patronId);
    }

    public static void displayBookDetails(Book book){
        System.out.println("---------------------------------------------");
        System.out.println("ISBN: " + book.getIsbn());
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Publication Year: " + book.getPublicationYear());
        System.out.println("---------------------------------------------");

    }

    public static void handleBookMenu() {
        boolean back = false;
        while (!back) {
            displayBookMenu();
            System.out.print("Enter choice -> ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addBookFlow();
                    break;
                case 2:
                    editBookFlow();
                    break;
                case 3:
                    removeBookFlow();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    logger.info("You have entered an invalid choice.");
                    holdScreen();
            }
        }
    }

    private static void addBookFlow() {
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        System.out.print("Enter Year: ");
        String year = scanner.nextLine();

        try{
            bookService.addBook(isbn, title, author, year);
            logger.info(">>> Success: Book added successfully!");
        } catch (Exception e) {
            logger.info(">>> Error: " + e.getMessage());
        }
        holdScreen();
    }

    private static void removeBookFlow() {
        System.out.print("Enter ISBN of book to remove: ");
        String isbn = scanner.nextLine();
        try {
            bookService.removeBook(isbn);
            System.out.println("Book removed.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void editBookFlow() {
        System.out.print("Enter ISBN of the book to edit: ");
        String isbn = scanner.nextLine();

        try {
            Book book = searchBook.findBookByIsbn(isbn);

            boolean back = false;
            while (!back) {
                displayBookEditMenu(book);
                System.out.print("What would you like to update? -> ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter new Title: ");
                        book.setTitle(scanner.nextLine());
                        System.out.println("Title updated!");
                        break;
                    case 2:
                        System.out.print("Enter new Author: ");
                        book.setAuthor(scanner.nextLine());
                        System.out.println("Author updated!");
                        break;
                    case 3:
                        System.out.print("Enter new Publication Year: ");
                        book.setPublicationYear(scanner.nextLine());
                        scanner.nextLine();
                        System.out.println("Year updated!");
                        break;
                    case 4:
                        System.out.print("Is available? (true/false): ");
                        book.setAvailable(scanner.nextBoolean());
                        scanner.nextLine();
                        System.out.println("Status updated!");
                        break;
                    case 0:
                        back = true;
                        break;
                    default:
                        logger.info("You have entered an invalid choice.");
                }

                bookService.updateBook(book);
            }
        } catch (BookNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        holdScreen();
    }

    public static void showAvailableBooks(){
        try{
            List<Book> books = searchBook.findAllBooksAvailable();
            System.out.println("\nAvailable Books:");
            for(Book book : books){
                System.out.println("ISBN: " + book.getIsbn());
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Publication Year: " + book.getPublicationYear());
                System.out.println();
            }
        } catch (Exception e) {
            logger.warning(">>> Error: " + e.getMessage());
        }
    }

    public static void showLendMenu(){
        System.out.println("1. Search a book.");
        System.out.println("2. Lend a book");
        System.out.println("0. back");

    }

    public static void handleLendABook(){
        boolean back = false;

        while(!back){
            showLendMenu();
            System.out.println("Enter choice -> ");
            int choice = scanner.nextInt();

            switch (choice){
                case 1:
                    handleSearchABook();
                    break;
                case 2:
                    lendABook();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    logger.info("You have entered an invalid choice.");
            }
        }
    }

    public static void lendABook(){
        System.out.println("Enter ISBN of book to lend: ");
        String isbn = scanner.nextLine();
        System.out.println("Enter Patron ID: ");
        String patronId = scanner.nextLine();

        lendingService.checkoutBook(patronId, isbn);
    }

    public static void returnABook(){
        System.out.println("Enter ISBN of book to return: ");
        String isbn = scanner.nextLine();
        System.out.println("Enter Patron ID: ");
        String patronId = scanner.nextLine();

        lendingService.returnBook(patronId, isbn);
    }

    public static void showBorrowHistory(){
        System.out.println("Enter Patron ID: ");
        String patronId = scanner.nextLine();

        lendingService.showBorrowHistory(patronId);
    }

    public static void handleSearchABook(){
        System.out.println("Search by: ");
        System.out.println("1. ISBN");
        System.out.println("2. Title");
        int choice =  scanner.nextInt();
        scanner.nextLine();

        if(choice == 1) {
            searchBookByIsbn();
        }   else if(choice == 2) {
            searchBookByTitle();
        } else {
            logger.info("You have entered an invalid choice.");
        }
        holdScreen();
    }

    public static void searchBookByIsbn(){
        System.out.println("Enter ISBN of book to search: ");
        String isbn = scanner.nextLine();
        try{
           Book book = searchBook.findBookByIsbn(isbn);
           displayBookDetails(book);
        } catch (Exception e) {
            logger.warning(">>> Error: " + e.getMessage());
        }
    }

    public static void searchBookByTitle(){
        System.out.println("Enter Title of book to search: ");
        String title = scanner.nextLine();
        try{
            List<Book> books = searchBook.findBooksByTitle(title);
            for(Book book : books){
                displayBookDetails(book);
            }
        } catch (Exception e) {
            logger.warning(">>> Error: " + e.getMessage());
        }
    }
}