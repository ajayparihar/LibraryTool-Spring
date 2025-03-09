/**
 * Entity class representing a Lender in the library system.
 * This class maps to the 'lenders' table in the database and tracks
 * information about who has borrowed books and their borrowing period.
 */
package com.example.library.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "lenders")
public class Lender {

    /**
     * Unique identifier for the lender record.
     * Auto-generated using database identity strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the person who borrowed the book
     */
    private String name;

    /**
     * The date when the book was borrowed
     */
    private LocalDate borrowDate;

    /**
     * The expected or actual date of return for the book
     */
    private LocalDate returnDate;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getBorrowDate() { return borrowDate; }
    public void setBorrowDate(LocalDate borrowDate) { this.borrowDate = borrowDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
}
