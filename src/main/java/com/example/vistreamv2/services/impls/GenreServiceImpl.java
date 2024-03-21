package com.example.vistreamv2.services.impls;

import com.example.vistreamv2.models.entity.Genre;
import com.example.vistreamv2.repositories.GenreRepository;
import com.example.vistreamv2.services.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    @Override
    public List<Genre> findAllGenre() {
        return genreRepository.findAllByOrderByNameAsc();
    }

    @Override
    public Genre findGenreByName(String name) {
        return genreRepository.findGenreByName(name);
    }

    @Override
    public Genre createGenre(Genre genre) {
        return genreRepository.save(genre);
    }
}
