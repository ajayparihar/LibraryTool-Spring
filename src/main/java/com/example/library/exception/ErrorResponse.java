/**
 * Standard error response class for the API.
 * This class defines the structure of error responses sent to clients
 * when an exception occurs during request processing.
 */
package com.example.library.exception;

import java.util.Date;

public class ErrorResponse {
    /**
     * The timestamp when the error occurred
     */
    private Date timestamp;

    /**
     * The main error message
     */
    private String message;

    /**
     * Additional details about the error
     */
    private String details;

    /**
     * Constructs a new ErrorResponse with all required fields
     * @param timestamp When the error occurred
     * @param message The main error message
     * @param details Additional error details
     */
    public ErrorResponse(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    // Getters
    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
} 