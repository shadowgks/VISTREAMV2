package com.example.vistreamv2.services;

import com.example.vistreamv2.models.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface MediaService {
    Page<Media> findAllMediaPageable(String typeMedia, String searchTerm, Pageable pageable);
    Media findMediaByShortLink(String shortLink);

    Set<Media> mediaAlsoLike(Set<Country> countries,
                             Set<Genre> genres,
                             Set<Production> productions);
    List<Media> findAllMediaRecommended(String column, String type, Integer limitData);
    Page<Media> findAllMediaByGenresOrCountry(String genreOrCountry, Pageable pageable);
    Boolean checkMediaIsFounded(Media media);
//    Page<Media> filterMedia(String typeMedia,
//                            LocalDate releaseDate,
//                            List<Country> countries,
//                            List<Genre> genres,
//                            Pageable pageable);
    Media findMediaByIdTmdb(Long idTmdb);
    Media saveMedia(Media media);
    Media updateMedia(Long id, Media media);
    Media deleteMedia(Long id);

}
