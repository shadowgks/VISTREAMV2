package com.example.vistreamv2.rest.controllers;

import com.example.vistreamv2.dtos.requests.actor.ActorReqDto;
import com.example.vistreamv2.dtos.response.actor.ActorResDto;
import com.example.vistreamv2.mapper.ActorMapper;
import com.example.vistreamv2.models.entity.Actor;
import com.example.vistreamv2.services.ActorService;
import com.example.vistreamv2.utils.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1.0.0/actor")
public class ActorController {
    private final ActorService actorService;

    @GetMapping
    public ResponseEntity<Response<List<ActorResDto>>> getAllActor(){
        List<Actor> actorList = actorService.findAllActor();
        return new ResponseEntity<>(Response.<List<ActorResDto>>builder()
                .result(actorList.stream()
                        .map(ActorMapper::mapToDto)
                        .toList())
                .message("Success")
                .build(),
                HttpStatus.OK);
    }
    @GetMapping("/{nameActor}")
    public ResponseEntity<Response<ActorResDto>> getByName(@Valid @PathVariable("nameActor") String name){
        Actor actor = actorService.findByName(name);
        return new ResponseEntity<>(Response.<ActorResDto>builder()
                .result(ActorMapper.mapToDto(actor))
                .message("Success")
                .build(),
                HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Response<ActorResDto>> createActor(@Valid
                                                                 @RequestBody ActorReqDto reqDto){
        Actor actors = actorService.createActor(ActorMapper.mapToEntity(reqDto));
        return new ResponseEntity<>(Response.<ActorResDto>builder()
                .result(ActorMapper.mapToDto(actors))
                .message("Create Actor Successfully")
                .build(),
                HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Response<ActorResDto>> updateActor(@Valid @PathVariable("id") Long id,
                                                                 @RequestBody ActorReqDto reqDto){
        Actor actor = actorService.updateActor(id, ActorMapper.mapToEntity(reqDto));
        return new ResponseEntity<>(Response.<ActorResDto>builder()
                .result(ActorMapper.mapToDto(actor))
                .message("Updated Actor Successfully")
                .build(),
                HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response<ActorResDto>> deleteActor(@Valid @PathVariable("id") Long id){
        actorService.deleteActor(id);
        return new ResponseEntity<>(Response.<ActorResDto>builder()
                .message("Deleted Actor Successfully")
                .build(),
                HttpStatus.NO_CONTENT);
    }
}
