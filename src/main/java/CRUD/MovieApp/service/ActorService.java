package CRUD.MovieApp.service;

import CRUD.MovieApp.main.Actor;
import CRUD.MovieApp.repository.ActorRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ActorService {
    private final ActorRepository actorRepository;
    @Autowired
    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }
    public List<Actor> getAllActors(){
        return actorRepository.findAll();
    }
    public Actor addActor(Actor movie){
        return actorRepository.save(movie);
    }


    public void updateActor(Long actorId, Map<String, Object> updates){
        Actor actor = actorRepository.findById(actorId)
                .orElseThrow(() -> new RuntimeException("Actor not found"));
        // Update fields dynamically
        if (updates.containsKey("name")) {
            actor.setName((String) updates.get("name"));
        }
        if (updates.containsKey("gender")) {
            actor.setGender((String) updates.get("gender"));
        }
        // Save updated movie to the repository
        actorRepository.save(actor);
    }

    public List<Actor> searchActors(Long actorId, String actorName, String gender) {
        return actorRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(actorId != null){
                predicates.add(criteriaBuilder.equal(root.get("actorId"), actorId));
            }

            if (actorName != null) {
                predicates.add(criteriaBuilder.equal(root.get("name"), actorName));
            }

            if (gender != null) {
                predicates.add(criteriaBuilder.equal(root.get("gender"), gender));
            }

            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        });
    }

    public List<Actor> getActiveActors() {
        return actorRepository.findActiveActors();
    }

}