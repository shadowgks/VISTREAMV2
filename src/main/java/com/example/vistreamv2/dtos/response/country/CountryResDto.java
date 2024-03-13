package com.example.vistreamv2.dtos.response.country;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CountryResDto {
    private String iso;
    private String name;
}
