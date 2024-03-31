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
import java.util.Optional;

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
    public void saveCredit(Credit credit) {
        creditRepository.save(credit);
    }

    @Override
    public Credit updateCredit(Credit credit, Long id) {
        Credit findCredit = creditRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found this credit: "+ id));
        if(credit.getProfilePath() == null){
            findCredit.setProfilePath(findCredit.getProfilePath());
        }else{
            findCredit.setProfilePath(credit.getProfilePath());
        }
        // Set all properties of findCredit
        findCredit.setIdTmdb(findCredit.getIdTmdb());
        findCredit.setAdult(credit.getAdult());
        findCredit.setGender(credit.getGender());
        findCredit.setName(credit.getName());
        findCredit.setPopularity(credit.getPopularity());
        return creditRepository.save(findCredit);
    }

    @Override
    public void deleteCredit(Long id) {
        Credit credit = creditRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found this credit: "+ id));
        creditRepository.delete(credit);
    }

    public void checkCreditIdTmdb(Long idTmdb){
        Optional<Credit> credit = creditRepository.findCreditByIdTmdb(idTmdb);
        if (credit.isPresent() && credit.get().getIdTmdb() != null){
            throw new IllegalArgumentException("Sorry this "+idTmdb+" already exist!");
        }
    }
}
