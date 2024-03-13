package com.example.vistreamv2.dtos.response.production;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductionResDto {
    private Long idTmdb;
    private String name;
    private String logoPath;
    private String originCountry;
}
