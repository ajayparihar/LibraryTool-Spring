/**
 * REST Controller for handling Book-related HTTP requests.
 * Provides endpoints for CRUD operations on books in the library system.
 * All endpoints are prefixed with the base path defined in LibraryConstants.BOOKS_ENDPOINT
 */
package com.example.library.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.constant.LibraryConstants;
import com.example.library.dto.BookRequestDTO;
import com.example.library.dto.BookResponseDTO;
import com.example.library.service.BookService;

@RestController
@RequestMapping(LibraryConstants.BOOKS_ENDPOINT)
public class BookController {

    /**
     * Service layer dependency for handling book operations
     */
    private final BookService bookService;

    /**
     * Constructor injection of BookService
     * @param bookService The service for book operations
     */
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Retrieves all books from the library
     * @return ResponseEntity containing a list of all books
     * HTTP Status: 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    /**
     * Retrieves a specific book by its ID
     * @param id The ID of the book to retrieve
     * @return ResponseEntity containing the requested book
     * HTTP Status: 200 (OK) if found, 404 (Not Found) if book doesn't exist
     */
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    /**
     * Adds a new book to the library
     * @param bookRequest DTO containing the book details
     * @return ResponseEntity containing the newly created book
     * HTTP Status: 200 (OK)
     */
    @PostMapping
    public ResponseEntity<BookResponseDTO> addBook(@RequestBody BookRequestDTO bookRequest) {
        return ResponseEntity.ok(bookService.saveBook(bookRequest));
    }

    /**
     * Updates an existing book in the library
     * @param id The ID of the book to update
     * @param bookRequest DTO containing the updated book details
     * @return ResponseEntity containing the updated book
     * HTTP Status: 200 (OK) if updated, 404 (Not Found) if book doesn't exist
     */
    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable Long id, @RequestBody BookRequestDTO bookRequest) {
        return ResponseEntity.ok(bookService.updateBook(id, bookRequest));
    }

    /**
     * Deletes a book from the library
     * @param id The ID of the book to delete
     * @return ResponseEntity with no content
     * HTTP Status: 204 (No Content) if deleted, 404 (Not Found) if book doesn't exist
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
