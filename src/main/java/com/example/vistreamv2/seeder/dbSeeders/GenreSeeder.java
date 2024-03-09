package com.example.vistreamv2.seeder.dbSeeders;

import com.example.vistreamv2.models.entity.Genre;
import com.example.vistreamv2.models.entity.Media;
import com.example.vistreamv2.repositories.GenreRepository;
import com.example.vistreamv2.services.GenreService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class GenreSeeder {
    private final GenreService genreService;
    private final GenreRepository genreRepository;
    private static final String TMDB_API_KEY = "6012a2495a4fe600579a02c19b35cf28";
    private static final String TMDB_BASE_URL = "https://api.themoviedb.org/3";

    private final HttpClient httpClient;

    public GenreSeeder(GenreService genreService, GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
        this.genreService = genreService;
        this.httpClient = HttpClient.newHttpClient();
    }

//    public List<Media> fetchPopularMovies() throws IOException, InterruptedException {
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(TMDB_BASE_URL + "/movie/popular?api_key=" + TMDB_API_KEY))
//                .build();
//
//        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode rootNode = objectMapper.readTree(response.body());
//        JsonNode resultsNode = rootNode.get("results");
//
//        List<Media> movies = new ArrayList<>();
//        for (JsonNode movieNode : resultsNode) {
//            // Parse movie data and create Movie objects
//            // Example:
//            // String title = movieNode.get("title").asText();
//            // int id = movieNode.get("id").asInt();
//            // Movie movie = new Movie(id, title);
//            // movies.add(movie);
//        }
//
//        return movies;
//    }

    public Set<Genre> fetchPopularGenre() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(TMDB_BASE_URL + "/genre/movie/list?api_key=" + TMDB_API_KEY))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(response.body());
        JsonNode resultsNode = rootNode.get("genres");
        System.out.println(objectMapper);

        Set<Genre> genres = new HashSet<>();
        for (JsonNode genreNode : resultsNode) {

            Genre genre = Genre.builder()
                    .idTmdb(genreNode.get("id").asLong())
                    .name(genreNode.get("name").asText())
                    .build();
            genres.add(genre);


            // Parse movie data and create Movie objects
            // Example:
            // String title = movieNode.get("title").asText();
            // int id = movieNode.get("id").asInt();
            // Movie movie = new Movie(id, title);
            // movies.add(movie);
        }
        System.out.println(genres);
        genreRepository.saveAll(genres);
//        genreService.createGenre(genreNode);

        return genres;
    }
}

// Other methods for fetching different types of data from TMDB API
