package com.example.vistreamv2.repositories;

import com.example.vistreamv2.models.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
    @Query("SELECT m FROM Media m " +
            "WHERE ((:typeMedia IS NULL OR LOWER(m.typeMedia) = LOWER(:typeMedia)) " +
            "OR (:typeMedia IS NULL AND m.typeMedia IS NULL)) " +
            "AND (LOWER(m.originalTitle) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR LOWER(m.originalLanguage) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR LOWER(m.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    Optional<Page<Media>> findMediaByTypeMediaContaining(
            @Param("searchTerm") String searchTerm,
            @Param("typeMedia") String typeMedia,
            Pageable pageable);
    Optional<Media> findMediaByOriginalTitleAndReleaseDate(String originalTitle, LocalDate releaseDate);
    Optional<Media> findMediaByShortLink(String shortLink);
    Optional<Set<Media>> findMediaByCountriesInAndGenresInOrProductionsIn(Set<Country> countries, Set<Genre> genres, Set<Production> productions);
    @Query("SELECT m FROM Media m WHERE m.typeMedia = :typeMedia ORDER BY "
            + "CASE "
            + "WHEN :column = 'popularity' THEN m.popularity "
            + "WHEN :column = 'releaseDate' THEN m.releaseDate "
            + "ELSE m.popularity "
            + "END DESC LIMIT :limitData")
    Optional<List<Media>> findAllMediaByTypeMediaOrderOrderByAndLimit(String column, String typeMedia, Integer limitData);
    @Query("SELECT DISTINCT m FROM Media m JOIN m.genres g JOIN m.countries c WHERE g.name = :genreOrCountry OR c.name = :genreOrCountry")
    Optional<Page<Media>> findAllByGenreOrCountry(String genreOrCountry, Pageable pageable);
    Optional<Media> findMediaByIdTmdb(Long idTmdb);
//    @Query("SELECT m FROM Media m " +
//            "WHERE ((:typeMedia IS NULL OR LOWER(m.typeMedia) = LOWER(:typeMedia)) " +
//            "OR (:typeMedia IS NULL AND m.typeMedia IS NULL)) " +
//            "OR (:countries IS EMPTY OR EXISTS (SELECT c FROM m.countries c WHERE c IN :countries)) " +
//            "OR (:genres IS EMPTY OR EXISTS (SELECT g FROM m.genres g WHERE g IN :genres)) " +
//            "OR (m.releaseDate = :releaseDate)")
//@Query("SELECT m FROM Media m WHERE (:typeMedia IS NULL OR m.typeMedia = :typeMedia) " +
//        "AND (:releaseDate IS NULL OR m.releaseDate = :releaseDate) " +
//        "AND (COALESCE(:countries) IS NULL OR m.countries MEMBER OF :countries) " +
//        "AND (COALESCE(:genres) IS NULL OR m.genres MEMBER OF :genres)")
//Optional<Page<Media>> findMediaByTypeMediaOrReleaseDateOrCountriesOrGenresContaining(
//        @Param("typeMedia") String typeMedia,
//        @Param("releaseDate") LocalDate releaseDate,
//        @Param("countries") List<Country> countries,
//        @Param("genres") List<Genre> genres,
//        Pageable pageable);


}
