package com.example.vistreamv2.services;

import com.example.vistreamv2.models.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MediaService {
    Page<Media> findAllMediaPageable(String typeMedia, String searchTerm, Pageable pageable);
    Media findMediaByShortLink(String shortLink);
    Media findMediaByIdTmdb(Long idTmdb);
    Set<Media> mediaAlsoLike(Set<Country> countries,
                             Set<Genre> genres,
                             Set<Production> productions);
    List<Media> findAllMediaRecommended(String column, String type, Integer limitData);
    Page<Media> findAllMediaByGenres(Genre genre, Pageable pageable);
    Boolean checkMediaIsFounded(Media media);
    Media saveMedia(Media media);
    Media updateMedia(Long id, Media media);
    Media deleteMedia(Long id);

}
