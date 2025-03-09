/**
 * Service class that handles the business logic for Book operations.
 * This class provides methods for CRUD operations on books and serves as an intermediary
 * between the controller layer and the repository layer.
 */
package com.example.library.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.library.dto.BookRequestDTO;
import com.example.library.dto.BookResponseDTO;
import com.example.library.entity.Book;
import com.example.library.exception.ResourceNotFoundException;
import com.example.library.repository.BookRepository;
import com.example.library.util.BookMapper;

@Service
public class BookService {

    /**
     * Repository for handling book data persistence
     */
    private final BookRepository bookRepository;

    /**
     * Constructor injection of BookRepository
     * @param bookRepository The repository for book operations
     */
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Retrieves all books from the database
     * @return List of BookResponseDTO containing all books
     */
    public List<BookResponseDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a specific book by its ID
     * @param id The ID of the book to retrieve
     * @return BookResponseDTO containing the book details
     * @throws ResourceNotFoundException if the book is not found
     */
    public BookResponseDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));
        return BookMapper.toDTO(book);
    }

    /**
     * Saves a new book to the database
     * @param bookRequest DTO containing the book details to save
     * @return BookResponseDTO containing the saved book details
     */
    public BookResponseDTO saveBook(BookRequestDTO bookRequest) {
        Book book = BookMapper.toEntity(bookRequest);
        Book savedBook = bookRepository.save(book);
        return BookMapper.toDTO(savedBook);
    }

    /**
     * Updates an existing book in the database
     * @param id The ID of the book to update
     * @param bookRequest DTO containing the updated book details
     * @return BookResponseDTO containing the updated book details
     * @throws ResourceNotFoundException if the book is not found
     */
    public BookResponseDTO updateBook(Long id, BookRequestDTO bookRequest) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));
        
        BookMapper.updateEntityFromDTO(book, bookRequest);
        Book updatedBook = bookRepository.save(book);
        return BookMapper.toDTO(updatedBook);
    }

    /**
     * Deletes a book from the database
     * @param id The ID of the book to delete
     * @throws ResourceNotFoundException if the book is not found
     */
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book", "id", id);
        }
        bookRepository.deleteById(id);
    }
}
