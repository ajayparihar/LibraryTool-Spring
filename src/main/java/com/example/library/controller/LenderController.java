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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/lenders")
@Tag(name = "Lender Management", description = "APIs for managing lenders in the library system")
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

    @Operation(summary = "Get all lenders", description = "Retrieves a list of all lenders registered in the library system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of lenders",
                    content = @Content(mediaType = "application/json", 
                    schema = @Schema(implementation = Lender.class)))
    })
    @GetMapping
    public List<Lender> getAllLenders() {
        return lenderService.getAllLenders();
    }

    @Operation(summary = "Get a lender by ID", description = "Retrieves a specific lender using their ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lender found",
                    content = @Content(mediaType = "application/json", 
                    schema = @Schema(implementation = Lender.class))),
        @ApiResponse(responseCode = "404", description = "Lender not found",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public Lender getLenderById(
            @Parameter(description = "ID of the lender to retrieve") @PathVariable Long id) {
        return lenderService.getLenderById(id);
    }

    @Operation(summary = "Add a new lender", description = "Registers a new lender in the library system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lender successfully registered",
                    content = @Content(mediaType = "application/json", 
                    schema = @Schema(implementation = Lender.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input data",
                    content = @Content)
    })
    @PostMapping
    public Lender addLender(
            @Parameter(description = "Lender details", required = true) 
            @RequestBody Lender lender) {
        return lenderService.saveLender(lender);
    }

    @Operation(summary = "Delete a lender", description = "Removes a lender from the library system")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lender successfully deleted"),
        @ApiResponse(responseCode = "404", description = "Lender not found",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public void deleteLender(
            @Parameter(description = "ID of the lender to delete") @PathVariable Long id) {
        lenderService.deleteLender(id);
    }
}
