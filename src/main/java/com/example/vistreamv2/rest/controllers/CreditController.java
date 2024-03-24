package com.example.vistreamv2.rest.controllers;

import com.example.vistreamv2.dtos.response.credit.CreditResDto;
import com.example.vistreamv2.dtos.response.media.ShortMediaResDto;
import com.example.vistreamv2.mapper.CreditMapper;
import com.example.vistreamv2.models.entity.Credit;
import com.example.vistreamv2.models.entity.Media;
import com.example.vistreamv2.services.CreditService;
import com.example.vistreamv2.utils.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1.0.0/credit")
public class CreditController {
    private final CreditService creditService;
    private final CreditMapper creditMapper;

    @GetMapping
    public ResponseEntity<Response<Object>> getAllMedia(@RequestParam Optional<String> searchTerm,
                                                        @RequestParam Optional<Integer> numPage,
                                                        @RequestParam Optional<Integer> numSize){
        Map<String, Page<CreditResDto>> stringListMap = new HashMap<>();
        // initialize pageable default
        Pageable pageable = PageRequest.of(
                numPage.orElse(0),
                numSize.orElse(10)
        );
        // initialize request default
        Page<Credit> creditPage = creditService.findAllCreditPageable(
                searchTerm.orElse(""),
                pageable
        );
        // Mapped data
        List<CreditResDto> creditResDtos = creditPage.stream()
                .map(creditMapper::mapToDto)
                .toList();

        // Insert data in PageImpl class
        Page<CreditResDto> movieResDtoPage = new PageImpl<>(creditResDtos, pageable, creditPage.getTotalElements());
        // Set data pageble
        stringListMap.put("page", movieResDtoPage);
        return ResponseEntity.ok(Response.builder()
                .message("Success")
                .result(stringListMap)
                .build());
    }
}
