/**
 * Constants class for the Library Management System.
 * Contains all constant values used throughout the application including
 * API endpoints, error messages, and validation messages.
 * This class cannot be instantiated as it only serves as a container for constants.
 */
package com.example.library.constant;

public final class LibraryConstants {
    /**
     * Private constructor to prevent instantiation of this utility class
     */
    private LibraryConstants() {
        // Private constructor to prevent instantiation
    }

    /**
     * API Endpoints
     * Base URL paths for different REST endpoints in the application
     */
    public static final String BOOKS_ENDPOINT = "/books";
    public static final String LENDERS_ENDPOINT = "/lenders";

    /**
     * Error Messages
     * Standard error messages used for resource not found scenarios
     */
    public static final String BOOK_NOT_FOUND = "Book not found with id: ";
    public static final String LENDER_NOT_FOUND = "Lender not found with id: ";
    
    /**
     * Validation Messages
     * Messages used for input validation across the application
     */
    public static final String NAME_NOT_BLANK = "Name cannot be blank";
    public static final String AUTHOR_NOT_BLANK = "Author cannot be blank";
    public static final String INVALID_ID = "Invalid ID provided";
} 