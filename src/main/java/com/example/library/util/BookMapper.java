package com.example.library.util;

import com.example.library.dto.BookRequestDTO;
import com.example.library.dto.BookResponseDTO;
import com.example.library.entity.Book;

public class BookMapper {
    
    public static Book toEntity(BookRequestDTO dto) {
        Book book = new Book();
        book.setName(dto.getName());
        book.setAuthor(dto.getAuthor());
        book.setGenre(dto.getGenre());
        book.setLanguage(dto.getLanguage());
        return book;
    }

    public static BookResponseDTO toDTO(Book book) {
        BookResponseDTO dto = new BookResponseDTO();
        dto.setBookId(book.getBookId());
        dto.setName(book.getName());
        dto.setAuthor(book.getAuthor());
        dto.setGenre(book.getGenre());
        dto.setLanguage(book.getLanguage());
        return dto;
    }

    public static void updateEntityFromDTO(Book book, BookRequestDTO dto) {
        book.setName(dto.getName());
        book.setAuthor(dto.getAuthor());
        book.setGenre(dto.getGenre());
        book.setLanguage(dto.getLanguage());
    }
} 