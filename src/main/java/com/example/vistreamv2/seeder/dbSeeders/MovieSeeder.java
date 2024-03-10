package com.example.vistreamv2.seeder.dbSeeders;

import com.example.vistreamv2.models.entity.*;
import com.example.vistreamv2.repositories.*;
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
    private final GenreRepository genreRepository;
    private final CountryRepository countryRepository;
    private final ProductionRepository productionRepository;
    private final HttpClient httpClient;
    private final VideosRepository videosRepository;

    public MovieSeeder(MediaRepository mediaRepository,
                       ProductionRepository productionRepository,
                       CountryRepository countryRepository,
                       GenreRepository genreRepository,
                       VideosRepository videosRepository) {
        this.productionRepository = productionRepository;
        this.genreRepository = genreRepository;
        this.countryRepository = countryRepository;
        this.mediaRepository = mediaRepository;
        this.httpClient = HttpClient.newHttpClient();
        this.videosRepository = videosRepository;
    }

    public Set<Long> fetchIdTmdbMedia() throws IOException, InterruptedException{
        long countPage = 1;
        long totalPages = 500;

        //for stock id movies
        Set<Long> idTmdbList = new HashSet<>();

        do {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(TMDB_BASE_URL_V3 + "/discover/movie?include_adult=true&include_video=true&page="+countPage+"&sort_by=popularity.desc&api_key=" + TMDB_API_KEY))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(response.body());
            JsonNode resultsNode = rootNode.get("results");
            JsonNode totalPagesNode = rootNode.get("total_pages");

            //stock id movies
            for (JsonNode idTmdbNode : resultsNode) {
                fetchMediaByIdTmdb(idTmdbNode.get("id").asLong());
            }


            System.out.println(countPage++);
        }while (countPage <= totalPages);

        System.out.println(idTmdbList);
        return idTmdbList;
    }

    public Set<Media> fetchMediaByIdTmdb(Long idTmdb) throws IOException, InterruptedException{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(TMDB_BASE_URL_V3 + "/movie/"+idTmdb+"?append_to_response=videos&api_key=" + TMDB_API_KEY))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(response.body());
        JsonNode genresNode = rootNode.get("genres");
        JsonNode productionNode = rootNode.get("production_companies");
        JsonNode countriesNode = rootNode.get("production_countries");
        JsonNode videosNode = rootNode.get("videos").get("results");
        JsonNode spokenLanguagesNode = rootNode.get("spoken_languages");

        // Store data Genres
        Set<Genre> genres = new HashSet<>();
//        genreRepository.findGenreByIdTmdb(genresNode.get("id").asLong()).get();
        for (JsonNode item : genresNode) {
            Genre genre = Genre.builder()
                    .idTmdb(item.get("id").asLong())
                    .name(item.get("name").asText())
                    .build();
            genres.add(genre);
        }
        genreRepository.saveAll(genres);

        // Store data Productions
        Set<Production> productions = new HashSet<>();
        for (JsonNode item : productionNode) {
            Production production = Production.builder()
                    .idTmdb(item.get("id").asLong())
                    .logoPath(item.get("logo_path").asText())
                    .originCountry(item.get("origin_country").asText())
                    .name(item.get("name").asText())
                    .build();
            productions.add(production);
        }
        productionRepository.saveAll(productions);

        // Store data Countries
        Set<Country> countries = new HashSet<>();
        for (JsonNode item : countriesNode) {
            Country country = Country.builder()
                    .iso(item.get("iso").asText())
                    .nativeName(item.get("name").asText())
                    .build();
            countries.add(country);
        }
        countryRepository.saveAll(countries);

        // Store data Videos
        Set<Videos> videos = new HashSet<>();
        for (JsonNode item : videosNode) {
            Videos video = Videos.builder()
                    .idTmdb(item.get("name").asLong())
                    .build();
            videos.add(video);
        }
        videosRepository.saveAll(countries);
        System.out.println("d");
        Set<Media> movies = new HashSet<>();
        for (JsonNode movieNode : rootNode) {
            // Parse movie data and create Movie objects
            Media media = Media.builder()
                    .idTmdb(idTmdb)
                    .build();

            //store list genre
            movies.add(media);
        }
        mediaRepository.saveAll(movies);
        return new HashSet<>();
    }

}
