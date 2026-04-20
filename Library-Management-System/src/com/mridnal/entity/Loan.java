package com.mridnal.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public class Loan {

    private int loanId;
    private Book book;
    private Patron patron;
    private LocalDateTime checkoutDate;
    private LocalDateTime returnDate;

}
