package com.example.vistreamv2.repositories;

import com.example.vistreamv2.models.entity.Country;
import com.example.vistreamv2.models.entity.Genre;
import com.example.vistreamv2.models.entity.Media;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
    @Query("SELECT m FROM Media m " +
            "WHERE (:typeMedia IS NULL OR LOWER(m.typeMedia) = LOWER(:typeMedia)) " +
            "AND (LOWER(m.originalTitle) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR LOWER(m.originalLanguage) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR LOWER(m.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    Optional<Page<Media>> findMediaByTypeMediaContaining(
            @Param("searchTerm") String searchTerm,
            @Param("typeMedia") String typeMedia,
            Pageable pageable);
    Optional<Media> findMediaByOriginalTitleAndReleaseDate(String originalTitle, LocalDate releaseDate);
    Optional<Media> findMediaByShortLink(String shortLink);

    Optional<Set<Media>> findMediaByCountriesInAndGenresIn(Set<Country> countries, Set<Genre> genres);

    Optional<Media> findMediaByIdTmdb(Long idTmdb);
}
