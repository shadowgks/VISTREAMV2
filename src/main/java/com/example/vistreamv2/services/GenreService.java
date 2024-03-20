package com.example.vistreamv2.services;

import com.example.vistreamv2.models.entity.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAllGenre();
    Genre findGenreByName(String name);
    Genre createGenre(Genre genre);

}
