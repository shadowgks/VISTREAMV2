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
        Response<List<ActorResDto>> response = new Response<>();
        List<Actor> actorList = actorService.findAllActor();
        response.setResult(actorList.stream()
                .map(ActorMapper::mapToDto)
                .toList());
        response.setMessage("Done");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{nameActor}")
    public ResponseEntity<Response<ActorResDto>> getByName(@Valid
                                                               @PathVariable("nameActor") String name){
        Response<ActorResDto> response = new Response<>();
        Actor actor = actorService.findByName(name);
        response.setResult(ActorMapper.mapToDto(actor));
        response.setMessage("Founded");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Response<ActorResDto>> createActor(@Valid
                                                                 @RequestBody ActorReqDto reqDto){
        Response<ActorResDto> response = new Response<>();
        Actor actors = actorService.createActor(ActorMapper.mapToEntity(reqDto));
        response.setResult(ActorMapper.mapToDto(actors));
        response.setMessage("Create Actor Successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Response<ActorResDto>> updateActor(@Valid @PathVariable("id") Long id,
                                                                 @RequestBody ActorReqDto reqDto){
        Response<ActorResDto> response = new Response<>();
        Actor actor = actorService.updateActor(id, ActorMapper.mapToEntity(reqDto));
        response.setResult(ActorMapper.mapToDto(actor));
        response.setMessage("Updated Actor Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response<ActorResDto>> deleteActor(@Valid @PathVariable("id") Long id){
        Response<ActorResDto> response = new Response<>();
        actorService.deleteActor(id);
        response.setMessage("Deleted Actor Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
