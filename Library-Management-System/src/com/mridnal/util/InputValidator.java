package com.mridnal.util;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class InputValidator {

    /**
     * Checks if the string is not null and not just whitespace.
     */
    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    /**
     * Validates email using a basic regex pattern.
     */
    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    public static boolean isValidYear(String yearStr) {
        if (yearStr == null || yearStr.trim().isEmpty()) {
            return false;
        }

        try {
            int year = Integer.parseInt(yearStr.trim());
            int currentYear = java.time.Year.now().getValue();

            return year >= 1450 && year <= currentYear;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Specifically for ISBN-13 or ISBN-10 (Optional but helpful).
     */
    public static boolean isValidIsbn(String isbn) {
        if (isbn == null) return false;
        // Simple check: removes dashes and checks length
        String cleanIsbn = isbn.replace("-", "");
        return cleanIsbn.length() == 10 || cleanIsbn.length() == 13;
    }
}
