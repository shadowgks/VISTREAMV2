package com.example.vistreamv2.dtos.response.media.credit;

import com.example.vistreamv2.dtos.response.credit.CreditResDto;
import com.example.vistreamv2.models.entity.Credit;
import com.example.vistreamv2.models.entity.Media;
import com.example.vistreamv2.models.entity.embedded.MediaCreditEmbedded;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MediaCreditResDto {
    private CreditResDto credit;
    private String _creditIdTmdb;
    private Integer _order;
    private String _character;
    private String _knownForDepartment;
}
