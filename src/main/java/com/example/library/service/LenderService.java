package com.example.library.service;

import com.example.library.entity.Lender;
import com.example.library.repository.LenderRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LenderService {

    private final LenderRepository lenderRepository;

    public LenderService(LenderRepository lenderRepository) {
        this.lenderRepository = lenderRepository;
    }

    public List<Lender> getAllLenders() {
        return lenderRepository.findAll();
    }

    public Lender getLenderById(Long id) {
        return lenderRepository.findById(id).orElse(null);
    }

    public Lender saveLender(Lender lender) {
        return lenderRepository.save(lender);
    }

    public void deleteLender(Long id) {
        lenderRepository.deleteById(id);
    }
}
