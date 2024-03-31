package com.example.vistreamv2.services;

import com.example.vistreamv2.exception.custom.NotFoundMediaException;
import com.example.vistreamv2.models.entity.Media;
import com.example.vistreamv2.repositories.MediaRepository;
import com.example.vistreamv2.services.impls.MediaServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
class MediaServiceImplTest {
    @Mock
    private MediaRepository mediaRepository;

    @InjectMocks
    private MediaServiceImpl mediaService;

    @Test
    void testFindAllMediaPageable() {
        // Arrange
        String typeMedia = "movie";
        String searchTerm = "example";
        Pageable pageable = Pageable.unpaged();
        Page<Media> expectedPage = Page.empty();
        when(mediaRepository.findMediaByTypeMediaContaining(searchTerm, typeMedia, pageable)).thenReturn(Optional.of(expectedPage));

        // Act
        Page<Media> resultPage = mediaService.findAllMediaPageable(typeMedia, searchTerm, pageable);

        // Assert
        assertEquals(expectedPage, resultPage, "Returned page should match expected page");
    }

    @Test
    void testFindMediaByShortLink() {
        // Arrange
        String shortLink = "example";
        Media expectedMedia = new Media();
        when(mediaRepository.findMediaByShortLink(shortLink)).thenReturn(Optional.of(expectedMedia));

        // Act
        Media resultMedia = mediaService.findMediaByShortLink(shortLink);

        // Assert
        assertEquals(expectedMedia, resultMedia, "Returned media should match expected media");
    }

    @Test
    void testFindMediaByIdTmdb_Exists() {
        Long idTmdb = 12345L;
        Media expectedMedia = new Media();
        expectedMedia.setId(1L);
        expectedMedia.setIdTmdb(idTmdb);

        when(mediaRepository.findMediaByIdTmdb(idTmdb)).thenReturn(Optional.of(expectedMedia));

        Media resultMedia = mediaService.findMediaByIdTmdb(idTmdb);

        assertEquals(expectedMedia, resultMedia);
    }

    @Test
    void testFindMediaByIdTmdb_NotExists() {
        Long idTmdb = 12345L;

        when(mediaRepository.findMediaByIdTmdb(idTmdb)).thenReturn(Optional.empty());

        NotFoundMediaException exception = assertThrows(NotFoundMediaException.class,
                () -> mediaService.findMediaByIdTmdb(idTmdb));

        assertEquals("Not this media: " + idTmdb, exception.getMessage());
    }


}