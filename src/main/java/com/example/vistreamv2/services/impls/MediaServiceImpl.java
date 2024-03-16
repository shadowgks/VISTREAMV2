package com.example.vistreamv2.services.impls;

import com.example.vistreamv2.exception.custom.NotFoundMediaException;
import com.example.vistreamv2.models.entity.Country;
import com.example.vistreamv2.models.entity.Genre;
import com.example.vistreamv2.models.entity.Media;
import com.example.vistreamv2.repositories.MediaRepository;
import com.example.vistreamv2.services.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {
    private final MediaRepository mediaRepository;
    public Page<Media> findAllMediaPageable(String typeMedia, String searchTerm, Pageable pageable) {
        return mediaRepository.findMediaByTypeMediaContaining(searchTerm, typeMedia, pageable)
                .orElseThrow(() -> new NotFoundMediaException("Not found any media"));
    }

    @Override
    public Media findMediaByShortLink(String shortLink) {
        return mediaRepository.findMediaByShortLink(shortLink)
                .orElseThrow(() -> new NotFoundMediaException("Not this media: "+shortLink));
    }

    @Override
    public Media findMediaByIdTmdb(Long idTmdb) {
        return mediaRepository.findMediaByIdTmdb(idTmdb)
                .orElseThrow(() -> new NotFoundMediaException("Not this media: "+idTmdb));
    }

    @Override
    public Set<Media> mediaAlsoLike(Set<Country> countries, Set<Genre> genres) {
        return mediaRepository.findMediaByCountriesInAndGenresIn(countries, genres)
                .orElseThrow(() -> new NotFoundMediaException("This media does not have any content similar to the other media!"));
    }

//    @Override
//    public void test(String s, SetString c, String g) {
//        mediaRepository.findMediaByShortLinkAndCountriesLikeAndGenres(s, c, g)
//                .orElseThrow(() -> new NotFoundMediaException("Not this media: "+s));
//    }


    @Override
    public Boolean checkMediaIsFounded(Media media) {
        Optional<Media> checkMedia = mediaRepository.findMediaByOriginalTitleAndReleaseDate(
                media.getOriginalTitle(),
                media.getReleaseDate());
        if(checkMedia.isPresent()){
            throw new IllegalArgumentException("This media already exist!");
        }
        return true;
    }

    @Override
    public Media saveMedia(Media media) {
        Media saveMedia = mediaRepository.save(media);
        if(Boolean.TRUE.equals(checkMediaIsFounded(media))){
            return mediaRepository.save(media);
        }
        return saveMedia;
    }

    @Override
    public Media updateMedia(Long id, Media media) {
        return null;
    }

    @Override
    public Media deleteMedia(Long id) {
        return null;
    }

}
