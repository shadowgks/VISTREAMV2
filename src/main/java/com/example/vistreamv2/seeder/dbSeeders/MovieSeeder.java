package com.example.vistreamv2.seeder.dbSeeders;

import com.example.vistreamv2.models.entity.Media;
import com.example.vistreamv2.repositories.CountryRepository;
import com.example.vistreamv2.repositories.MediaRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.Set;

@Component
public class MovieSeeder {
    @Value("${spring.tmdb.api.key}")
    private String TMDB_API_KEY;
    @Value("${spring.tmdb.api.link-v3}")
    private String TMDB_BASE_URL_V3;
    private final MediaRepository mediaRepository;
    private final HttpClient httpClient;

    public MovieSeeder(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
        this.httpClient = HttpClient.newHttpClient();
    }

    public Long fetchIdTmdbMedia() throws IOException, InterruptedException{
        return null;
    }

    public Set<Media> fetchMedia(Long idTmdb) throws IOException, InterruptedException{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(TMDB_BASE_URL_V3 + "https://api.themoviedb.org/3/movie/"+idTmdb+"?api_key=" + TMDB_API_KEY))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(response.body());


        Set<Media> movies = new HashSet<>();
        for (JsonNode genreNode : rootNode) {
            // Parse movie data and create Movie objects
            Media media = Media.builder()
                    .build();

            //store list genre
            movies.add(media);
        }
        mediaRepository.saveAll(movies);
        return new HashSet<>();
    }

}
