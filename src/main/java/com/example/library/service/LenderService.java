/**
 * Service class that handles the business logic for Lender operations.
 * This class provides methods for managing book lending operations and serves as an intermediary
 * between the controller layer and the repository layer.
 */
package com.example.library.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.library.entity.Lender;
import com.example.library.repository.LenderRepository;

@Service
public class LenderService {

    /**
     * Repository for handling lender data persistence
     */
    private final LenderRepository lenderRepository;

    /**
     * Constructor injection of LenderRepository
     * @param lenderRepository The repository for lender operations
     */
    public LenderService(LenderRepository lenderRepository) {
        this.lenderRepository = lenderRepository;
    }

    /**
     * Retrieves all lenders from the database
     * @return List of all Lender entities
     */
    public List<Lender> getAllLenders() {
        return lenderRepository.findAll();
    }

    /**
     * Retrieves a specific lender by their ID
     * @param id The ID of the lender to retrieve
     * @return The Lender entity if found, null otherwise
     */
    public Lender getLenderById(Long id) {
        return lenderRepository.findById(id).orElse(null);
    }

    /**
     * Saves a new lender or updates an existing one in the database
     * @param lender The Lender entity to save
     * @return The saved Lender entity
     */
    public Lender saveLender(Lender lender) {
        return lenderRepository.save(lender);
    }

    /**
     * Deletes a lender from the database
     * @param id The ID of the lender to delete
     */
    public void deleteLender(Long id) {
        lenderRepository.deleteById(id);
    }
}
