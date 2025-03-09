/**
 * Custom exception class for handling resource not found scenarios.
 * This exception is thrown when a requested resource (e.g., Book, Lender) cannot be found in the system.
 * When thrown, it automatically results in an HTTP 404 (Not Found) response.
 */
package com.example.library.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    
    /**
     * Constructs a new ResourceNotFoundException with a custom message
     * @param message The detailed error message
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new ResourceNotFoundException with a formatted message
     * @param resourceName The type of resource that was not found (e.g., "Book", "Lender")
     * @param fieldName The field used in the search (e.g., "id", "name")
     * @param fieldValue The value of the field that was searched for
     */
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
    }
} 