package com.example.vistreamv2.services.impls;

import com.example.vistreamv2.exception.custom.NotFoundMediaException;
import com.example.vistreamv2.models.entity.Actor;
import com.example.vistreamv2.models.entity.AppUser;
import com.example.vistreamv2.repositories.ActorRepository;
import com.example.vistreamv2.services.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService {
    private final ActorRepository actorRepository;

    @Override
    public Page<Actor> finAllActorPageable(String searchTerm, Integer numPage, Integer sizePage) {
        return actorRepository.findActorByContaining(searchTerm, PageRequest.of(numPage, sizePage))
                .orElseThrow(() -> new NotFoundMediaException("Not Found Any Media"));
    }

    @Override
    public List<Actor> findAllActor() {
        return actorRepository.findAll();
    }

    @Override
    public Actor findByName(String name) {
        return actorRepository.findByFullName(name)
                .orElseThrow(() -> new IllegalArgumentException("Not found any actor "+name));
    }

    @Override
    public Actor createActor(Actor actor) {
        return actorRepository.save(actor);
    }

    @Override
    public Actor updateActor(Long id, Actor actor) {
        actorRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Not found this actor with this id: "+id));
        Actor actorEdit = Actor.builder()
                .id(id)
                .fullName(actor.getFullName())
                .picture(actor.getPicture())
                .birthDate(actor.getBirthDate())
                .build();
        return actorRepository.save(actorEdit);
    }

    @Override
    public void deleteActor(Long id) {
        Actor actor = actorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not found any user"));
        actorRepository.delete(actor);
    }
}
