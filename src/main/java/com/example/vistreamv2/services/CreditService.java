package com.example.vistreamv2.services;

import com.example.vistreamv2.models.entity.Credit;
import com.example.vistreamv2.models.entity.Media;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CreditService {
    Page<Credit> findAllCreditPageable(String searchTerm, Pageable pageable);
    void savedCredits(List<Credit> credits);
    Credit updateCredit(Credit credit, Long id);
    void deleteCredit(Long id);
}
