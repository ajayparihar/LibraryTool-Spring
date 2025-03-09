/**
 * Utility class for mapping between Book entity and DTOs.
 * Provides methods to convert between different representations of Book data
 * and update existing entities with DTO data.
 */
package com.example.library.util;

import com.example.library.dto.BookRequestDTO;
import com.example.library.dto.BookResponseDTO;
import com.example.library.entity.Book;

public class BookMapper {
    
    /**
     * Converts a BookRequestDTO to a Book entity
     * @param dto The BookRequestDTO containing book data
     * @return A new Book entity populated with data from the DTO
     */
    public static Book toEntity(BookRequestDTO dto) {
        Book book = new Book();
        book.setName(dto.getName());
        book.setAuthor(dto.getAuthor());
        book.setGenre(dto.getGenre());
        book.setLanguage(dto.getLanguage());
        return book;
    }

    /**
     * Converts a Book entity to a BookResponseDTO
     * @param book The Book entity to convert
     * @return A new BookResponseDTO containing data from the entity
     */
    public static BookResponseDTO toDTO(Book book) {
        BookResponseDTO dto = new BookResponseDTO();
        dto.setBookId(book.getBookId());
        dto.setName(book.getName());
        dto.setAuthor(book.getAuthor());
        dto.setGenre(book.getGenre());
        dto.setLanguage(book.getLanguage());
        return dto;
    }

    /**
     * Updates an existing Book entity with data from a BookRequestDTO
     * @param book The Book entity to update
     * @param dto The BookRequestDTO containing the new data
     */
    public static void updateEntityFromDTO(Book book, BookRequestDTO dto) {
        book.setName(dto.getName());
        book.setAuthor(dto.getAuthor());
        book.setGenre(dto.getGenre());
        book.setLanguage(dto.getLanguage());
    }
} 