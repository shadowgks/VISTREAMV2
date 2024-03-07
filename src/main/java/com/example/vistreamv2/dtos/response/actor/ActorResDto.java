package com.example.vistreamv2.dtos.response.actor;

import com.example.vistreamv2.models.entity.Media;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActorResDto {
    private String fullName;
    private String picture;
    private LocalDate birthDate;
    private List<Media> mediaList;
}
