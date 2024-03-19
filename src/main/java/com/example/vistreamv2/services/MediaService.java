package com.example.vistreamv2.services;

import com.example.vistreamv2.models.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.Set;

public interface MediaService {
    Page<Media> findAllMediaPageable(String typeMedia, String searchTerm, Pageable pageable);
    Media findMediaByShortLink(String shortLink);
    Media findMediaByIdTmdb(Long idTmdb);
    Set<Media> mediaAlsoLike(Set<Country> countries,
                             Set<Genre> genres,
                             Set<Production> productions);
    Set<Media> findAllMediaRecommended(String type);
    Boolean checkMediaIsFounded(Media media);
    Media saveMedia(Media media);
    Media updateMedia(Long id, Media media);
    Media deleteMedia(Long id);

}
