package com.example.library.constant;

public final class LibraryConstants {
    private LibraryConstants() {
        // Private constructor to prevent instantiation
    }

    // API Endpoints
    public static final String BOOKS_ENDPOINT = "/books";
    public static final String LENDERS_ENDPOINT = "/lenders";

    // Error Messages
    public static final String BOOK_NOT_FOUND = "Book not found with id: ";
    public static final String LENDER_NOT_FOUND = "Lender not found with id: ";
    
    // Validation Messages
    public static final String NAME_NOT_BLANK = "Name cannot be blank";
    public static final String AUTHOR_NOT_BLANK = "Author cannot be blank";
    public static final String INVALID_ID = "Invalid ID provided";
} 