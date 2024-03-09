package com.example.vistreamv2.seeder.dbSeeders;

import com.example.vistreamv2.services.GenreService;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenreSeeder {
    private final GenreService genreService;

    OkHttpClient client = new OkHttpClient();

//    Request request = new Request.Builder()
//            .url("https://api.themoviedb.org/3/genre/movie/list?language=en")
//            .get()
//            .addHeader("accept", "application/json")
//            .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2MDEyYTI0OTVhNGZlNjAwNTc5YTAyYzE5YjM1Y2YyOCIsInN1YiI6IjY1YTQxOWMyMWYwMjc1MDEyZTA4YzJmYiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.CjWwAuCQnmgmkllMP38dloIyb-LVOoFUQ1zrfv8vPoI")
//            .build();
//
//    Response response = client.newCall(request).execute();
}
