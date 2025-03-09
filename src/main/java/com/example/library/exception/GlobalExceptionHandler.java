/**
 * Global exception handler for the application.
 * This class provides centralized exception handling across all controllers
 * and converts exceptions into standardized API responses.
 */
package com.example.library.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles ResourceNotFoundException
     * This method is called when a resource (Book, Lender) is not found
     * 
     * @param ex The ResourceNotFoundException that was thrown
     * @param request The web request during which the exception occurred
     * @return ResponseEntity containing error details with HTTP 404 status
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(
            ResourceNotFoundException ex, WebRequest request) {
        ErrorResponse errorDetails = new ErrorResponse(
            new Date(),
            ex.getMessage(),
            request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles all other unhandled exceptions
     * This is a catch-all handler for any exception that doesn't have a specific handler
     * 
     * @param ex The Exception that was thrown
     * @param request The web request during which the exception occurred
     * @return ResponseEntity containing error details with HTTP 500 status
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(
            Exception ex, WebRequest request) {
        ErrorResponse errorDetails = new ErrorResponse(
            new Date(),
            ex.getMessage(),
            request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
} 