package com.example.vistreamv2.repositories;

import com.example.vistreamv2.models.entity.Videos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VideosRepository extends JpaRepository<Videos, Long> {
    Optional<Videos> findVideosByIdTmdb(String id);

}
