package com.example.vistreamv2.services.impls;

import com.example.vistreamv2.models.entity.Credit;
import com.example.vistreamv2.models.entity.Media;
import com.example.vistreamv2.repositories.CreditRepository;
import com.example.vistreamv2.services.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService {
    private final CreditRepository creditRepository;

    @Override
    public Page<Credit> findAllCreditPageable(String searchTerm, Pageable pageable) {
        return creditRepository.findMediaBySearch(searchTerm, pageable)
                .orElseThrow(() -> new IllegalArgumentException("Not found credits"));
    }
}
