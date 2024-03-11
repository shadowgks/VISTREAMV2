package com.example.vistreamv2.repositories;

import com.example.vistreamv2.models.entity.MediaCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaCreditRepository extends JpaRepository<MediaCredit, Long> {
}
