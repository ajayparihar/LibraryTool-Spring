/**
 * REST Controller for handling Lender-related HTTP requests.
 * Provides endpoints for managing book lending operations in the library system.
 * All endpoints are prefixed with '/lenders'
 */
package com.example.library.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.entity.Lender;
import com.example.library.service.LenderService;

@RestController
@RequestMapping("/lenders")
public class LenderController {

    /**
     * Service layer dependency for handling lender operations
     */
    private final LenderService lenderService;

    /**
     * Constructor injection of LenderService
     * @param lenderService The service for lender operations
     */
    public LenderController(LenderService lenderService) {
        this.lenderService = lenderService;
    }

    /**
     * Retrieves all lenders from the library system
     * @return List of all lenders
     * HTTP Status: 200 (OK)
     */
    @GetMapping
    public List<Lender> getAllLenders() {
        return lenderService.getAllLenders();
    }

    /**
     * Retrieves a specific lender by their ID
     * @param id The ID of the lender to retrieve
     * @return The requested lender if found, null otherwise
     * HTTP Status: 200 (OK)
     */
    @GetMapping("/{id}")
    public Lender getLenderById(@PathVariable Long id) {
        return lenderService.getLenderById(id);
    }

    /**
     * Adds a new lender to the library system
     * @param lender The lender entity to be added
     * @return The saved lender entity
     * HTTP Status: 200 (OK)
     */
    @PostMapping
    public Lender addLender(@RequestBody Lender lender) {
        return lenderService.saveLender(lender);
    }

    /**
     * Deletes a lender from the library system
     * @param id The ID of the lender to delete
     * HTTP Status: 200 (OK)
     */
    @DeleteMapping("/{id}")
    public void deleteLender(@PathVariable Long id) {
        lenderService.deleteLender(id);
    }
}
