package com.example.vistreamv2.services;

import com.example.vistreamv2.models.entity.Media;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface MediaService {
    Page<Media> findAllMediaPageable(String typeMedia, String searchTerm, Integer numPage, Integer size);
    Boolean checkMediaIsFounded(Media media);
    Media saveMedia(Media media);
    Media updateMedia(Long id, Media media);
    Media deleteMedia(Long id);

}
