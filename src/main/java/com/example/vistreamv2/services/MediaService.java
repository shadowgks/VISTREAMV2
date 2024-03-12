package com.example.vistreamv2.services;

import com.example.vistreamv2.models.entity.Media;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MediaService {
    Page<Media> findAllMediaPageable(String typeMedia, String searchTerm, Pageable pageable);
    Boolean checkMediaIsFounded(Media media);
    Media saveMedia(Media media);
    Media updateMedia(Long id, Media media);
    Media deleteMedia(Long id);

}
