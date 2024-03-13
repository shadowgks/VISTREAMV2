package com.example.vistreamv2.dtos.response.genre;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenreResDto {
    private Long idTmdb;
    private String name;
}
