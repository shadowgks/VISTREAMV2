package com.example.vistreamv2.services;

import com.example.vistreamv2.models.entity.Media;
import org.springframework.data.domain.Page;

public interface MediaService {
    Page<Media> findAllMedia(String searchTerm, Integer numPage, Integer size);
}
