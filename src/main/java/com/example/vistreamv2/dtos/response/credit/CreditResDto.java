package com.example.vistreamv2.dtos.response.credit;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditResDto {
    private Long idTmdb;
    private Boolean adult;
    private Integer gender;
    private String name;
    private Double popularity;
    private String profilePath;
}
