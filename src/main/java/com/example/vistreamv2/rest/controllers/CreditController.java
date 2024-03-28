package com.example.vistreamv2.rest.controllers;

import com.example.vistreamv2.dtos.requests.credit.CreditReqDto;
import com.example.vistreamv2.dtos.response.credit.CreditResDto;
import com.example.vistreamv2.dtos.response.media.ShortMediaResDto;
import com.example.vistreamv2.mapper.CreditMapper;
import com.example.vistreamv2.models.entity.Credit;
import com.example.vistreamv2.models.entity.Media;
import com.example.vistreamv2.services.CreditService;
import com.example.vistreamv2.services.S3Service;
import com.example.vistreamv2.utils.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1.0.0/credit")
public class CreditController {
    private final CreditService creditService;
    private final CreditMapper creditMapper;
    private final S3Service s3Service;


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
//    @PostMapping
//    public ResponseEntity<Response<String>> savedCredits( @RequestBody List<CreditReqDto> creditReqDto){
//        List<Credit> credits = new ArrayList<>();
//        creditReqDto.forEach(c -> {
//            Credit credit = creditMapper.mapToEntity(c);
//            credits.add(credit);
//        });
//        creditService.savedCredits(credits);
//        return new ResponseEntity<>(Response.<String>builder()
//                .message("Created Credits Successfully")
//                .build(),
//                HttpStatus.CREATED);
//    }
    @PostMapping
    public ResponseEntity<Response<String>> saveCredit(@ModelAttribute CreditReqDto creditReqDto){
        String imageUrl = s3Service.uploadFile(creditReqDto.getFile());
        creditReqDto.setProfilePath(imageUrl);
        creditService.saveCredit(creditMapper.mapToEntity(creditReqDto));
        return new ResponseEntity<>(Response.<String>builder()
                .message("Created Credit Successfully")
                .build(),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<String>> updateCredit(@ModelAttribute CreditReqDto creditReqDto,
                                                                @PathVariable("id") Long id){
        String imageUrl = s3Service.uploadFile(creditReqDto.getFile());
        creditReqDto.setProfilePath(imageUrl);
        creditService.updateCredit(creditMapper.mapToEntity(creditReqDto), id);
        return new ResponseEntity<>(Response.<String>builder()
                .message("Updated Credit Successfully")
                .build(),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<String>> deletedCredit(@PathVariable("id") Long id){
        creditService.deleteCredit(id);
        return new ResponseEntity<>(Response.<String>builder()
                .message("Deleted Credit Successfully")
                .build(),
                HttpStatus.OK);
    }
}
