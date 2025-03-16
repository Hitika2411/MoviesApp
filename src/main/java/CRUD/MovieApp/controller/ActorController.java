package CRUD.MovieApp.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import CRUD.MovieApp.main.Actor;
import CRUD.MovieApp.service.ActorService;

@RestController
@RequestMapping

public class ActorController {

    private static final Logger logger = LoggerFactory.getLogger(ActorController.class);

    private final ActorService actorService;
    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }


    @GetMapping("/actor")
    public List<Actor> getAllActors(){
        logger.info("Getting all Actors");
        List<Actor> actors = actorService.getAllActors();
        logger.debug("Fetched {} actors",actors);
         return actorService.getAllActors();
    }

    @PostMapping("/actor")
    public ResponseEntity<Actor> addActor(@RequestBody Actor actor){
        logger.info("Adding Actor {}", actor);
        Actor newActor=actorService.addActor(actor);
        logger.info("Actor added successfully with Id:{}",newActor.getActorId());
        return new ResponseEntity<>(newActor, HttpStatus.CREATED);
    }

    @PutMapping("/actor/{actorId}")
    public ResponseEntity<String> updateActor(@PathVariable Long actorId, @RequestParam Map<String,Object> updates){
        logger.info("Updating Actor with ID:{} with updates: {}", actorId,updates);
        try{
            actorService.updateActor(actorId,updates);
            logger.info("Actor updated successfully with Id:{}",actorId);
            return ResponseEntity.ok().body("Actor updated successfully");
        }
        catch (RuntimeException exp){
            logger.error("Error updating actor with ID {}: {}",actorId,exp.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exp.getMessage());
        }
    }

    @GetMapping("/actor/search")
    public ResponseEntity<List<Actor>> searchActor(
            @RequestParam(required = false) Long actorId,
            @RequestParam(required = false) String actorName,
            @RequestParam(required = false) String gender) {

        logger.info("Searching actors with name: {} and gender: {}", actorName,gender);
        List<Actor> actors = actorService.searchActors(actorId,actorName, gender);
        logger.info("Found {} actors", actors.size());
        return ResponseEntity.ok(actors);
    }


    //Get Active Records
    @GetMapping("/actor/movie")
    public ResponseEntity<List<Actor>> getActiveActors(){
        logger.info("Getting active actors");
        return ResponseEntity.ok(actorService.getActiveActors());
    }

}