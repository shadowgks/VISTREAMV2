package com.example.vistreamv2.dtos.requests.credit;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
@Builder
public class CreditReqDto {
    private Long idTmdb;
    @NotEmpty(message = "name must not be empty")
    @NotNull(message = "name must not be null")
    private String name;
    @NotNull(message = "adult must not be null")
    private Boolean adult;
    @NotNull(message = "gender must not be null")
    @Min(value = 1, message = "gender must be greater than or equal to 1")
    @Max(value = 2, message = "gender must be less than or equal to 2")
    private Integer gender;
    private MultipartFile file;
    @Min(value = 0, message = "gender must be greater than or equal to 0")
    @NotNull(message = "popularity must not be null")
    private Double popularity;
    private String profilePath;
}
