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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(LibraryConstants.BOOKS_ENDPOINT)
@Tag(name = "Book Management", description = "APIs for managing books in the library system")
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

    @Operation(summary = "Get all books", description = "Retrieves a list of all books available in the library")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of books",
                    content = @Content(mediaType = "application/json", 
                    schema = @Schema(implementation = BookResponseDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @Operation(summary = "Get a book by ID", description = "Retrieves a specific book using its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Book found",
                    content = @Content(mediaType = "application/json", 
                    schema = @Schema(implementation = BookResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(
            @Parameter(description = "ID of the book to retrieve") @PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @Operation(summary = "Add a new book", description = "Creates a new book in the library system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Book successfully created",
                    content = @Content(mediaType = "application/json", 
                    schema = @Schema(implementation = BookResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<BookResponseDTO> addBook(
            @Parameter(description = "Book details", required = true) 
            @RequestBody BookRequestDTO bookRequest) {
        return ResponseEntity.ok(bookService.saveBook(bookRequest));
    }

    @Operation(summary = "Update a book", description = "Updates an existing book's information")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Book successfully updated",
                    content = @Content(mediaType = "application/json", 
                    schema = @Schema(implementation = BookResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content),
        @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(
            @Parameter(description = "ID of the book to update") @PathVariable Long id,
            @Parameter(description = "Updated book details", required = true) 
            @RequestBody BookRequestDTO bookRequest) {
        return ResponseEntity.ok(bookService.updateBook(id, bookRequest));
    }

    @Operation(summary = "Delete a book", description = "Removes a book from the library system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Book successfully deleted"),
        @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(
            @Parameter(description = "ID of the book to delete") @PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
