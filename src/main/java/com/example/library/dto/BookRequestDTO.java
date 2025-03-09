/**
 * Data Transfer Object (DTO) for incoming book requests.
 * This class represents the data structure used when creating or updating a book.
 * It contains only the fields that can be modified by clients, excluding system-managed fields like ID.
 */
package com.example.library.dto;

public class BookRequestDTO {
    /**
     * The title/name of the book
     */
    private String name;

    /**
     * The author of the book
     */
    private String author;

    /**
     * The genre/category of the book (e.g., Fiction, Non-fiction, etc.)
     */
    private String genre;

    /**
     * The language in which the book is written
     */
    private String language;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
} 