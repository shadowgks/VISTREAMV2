package com.example.vistreamv2.services.impls;

import com.example.vistreamv2.models.entity.Actor;
import com.example.vistreamv2.repositories.ActorRepository;
import com.example.vistreamv2.services.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService {
    private final ActorRepository actorRepository;

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
        actorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not found any user"));
        return actorRepository.save(actor);
    }

    @Override
    public void deleteActor(Long id) {
        Actor actor = actorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Not found any user"));
        actorRepository.delete(actor);
    }
}
