/**
 * Entity class representing a Book in the library system.
 * This class maps to the 'books' table in the database and contains
 * basic information about books including their ID, name, author, genre, and language.
 */
package com.example.library.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book implements Serializable {

    /**
     * Unique identifier for the book.
     * Auto-generated using database identity strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

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
    public Long getBookId() { return bookId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
}
