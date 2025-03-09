package com.example.library.controller;

import com.example.library.entity.Lender;
import com.example.library.service.LenderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lenders")
public class LenderController {

    private final LenderService lenderService;

    public LenderController(LenderService lenderService) {
        this.lenderService = lenderService;
    }

    @GetMapping
    public List<Lender> getAllLenders() {
        return lenderService.getAllLenders();
    }

    @GetMapping("/{id}")
    public Lender getLenderById(@PathVariable Long id) {
        return lenderService.getLenderById(id);
    }

    @PostMapping
    public Lender addLender(@RequestBody Lender lender) {
        return lenderService.saveLender(lender);
    }

    @DeleteMapping("/{id}")
    public void deleteLender(@PathVariable Long id) {
        lenderService.deleteLender(id);
    }
}
