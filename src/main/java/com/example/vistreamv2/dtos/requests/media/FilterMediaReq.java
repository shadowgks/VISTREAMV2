package com.example.vistreamv2.dtos.requests.media;

import com.example.vistreamv2.models.entity.Country;
import com.example.vistreamv2.models.entity.Genre;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilterMediaReq {
    @NotNull(message = "Type media must not be null")
    String typeMedia;
    @NotNull(message = "Released date media must not be null")
    LocalDate releasedDate;
    List<Genre> genres;
    List<Country> countries;
}
