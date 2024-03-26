package com.example.vistreamv2.dtos.requests.credit;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter
@Builder
public class CreditReqDto {
    private Long idTmdb;
    private Boolean adult;
    private Integer gender;
    @NotNull(message = "name must not be null")
    private String name;
    private Double popularity;
    private String profilePath;
}
