package com.example.vistreamv2.services.impls;

import com.example.vistreamv2.exception.custom.NotFoundMediaException;
import com.example.vistreamv2.models.entity.Media;
import com.example.vistreamv2.repositories.MediaRepository;
import com.example.vistreamv2.services.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {
    private final MediaRepository mediaRepository;
    @Override
    public Page<Media> findAllMedia(String searchTerm, Integer numPage, Integer sizePage) {
        return mediaRepository.findMediaByContaining(searchTerm, PageRequest.of(numPage, sizePage))
                .orElseThrow(() -> new NotFoundMediaException("Not Found Any Media"));
    }
}
