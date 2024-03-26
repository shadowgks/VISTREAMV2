package com.example.vistreamv2.services.impls;

import com.example.vistreamv2.models.entity.Credit;
import com.example.vistreamv2.models.entity.Media;
import com.example.vistreamv2.repositories.CreditRepository;
import com.example.vistreamv2.services.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditService {
    private final CreditRepository creditRepository;

    @Override
    public Page<Credit> findAllCreditPageable(String searchTerm, Pageable pageable) {
        return creditRepository.findMediaBySearch(searchTerm, pageable)
                .orElseThrow(() -> new IllegalArgumentException("Not found credits"));
    }

    @Override
    public void savedCredits(List<Credit> credits) {
        credits.forEach(c -> checkCreditIdTmdb(c.getIdTmdb()));
        creditRepository.saveAll(credits);
    }

    @Override
    public Credit updateCredit(Credit credit, Long id) {
        Credit findCredit = creditRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found this credit: "+ id));
        // Set all properties of findCredit
        findCredit.setIdTmdb(findCredit.getIdTmdb());
        findCredit.setAdult(credit.getAdult());
        findCredit.setGender(credit.getGender());
        findCredit.setName(credit.getName());
        findCredit.setPopularity(credit.getPopularity());
        findCredit.setProfilePath(credit.getProfilePath());
        return creditRepository.save(findCredit);
    }

    @Override
    public void deleteCredit(Long id) {
        Credit credit = creditRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found this credit: "+ id));
        creditRepository.delete(credit);
    }

    public void checkCreditIdTmdb(Long idTmdb){
        creditRepository.findCreditByIdTmdb(idTmdb)
                .orElseThrow(() -> new IllegalArgumentException("This id "+ idTmdb +" Tmdb already exist"));
    }
}
