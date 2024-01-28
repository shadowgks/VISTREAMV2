package com.example.vistreamv2.services;

import com.example.vistreamv2.models.entity.Actor;

import java.util.List;

public interface ActorService {
    List<Actor> findAllActor();
    Actor findByName(String name);
    Actor createActor(Actor actor);
    void updateActor(Long id, Actor actor);
    void deleteActor(Long id);
}
