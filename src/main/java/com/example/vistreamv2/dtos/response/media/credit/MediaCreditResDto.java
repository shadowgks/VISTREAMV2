package com.example.vistreamv2.dtos.response.media.credit;

import com.example.vistreamv2.dtos.response.credit.CreditResDto;
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
