package CRUD.MovieApp.controller;

import CRUD.MovieApp.main.MovieAndActor;
import CRUD.MovieApp.service.MovieAndActorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/movie-and-actor")
public class MovieAndActorController {

    private static final Logger logger= LoggerFactory.getLogger(MovieAndActorController.class);

    private final MovieAndActorService service;

    @Autowired
    public MovieAndActorController(MovieAndActorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<MovieAndActor>> getAllRecords() {
        logger.info("Fetching All Records");
        return ResponseEntity.ok(service.getAllRecords());
    }

    @GetMapping("/active")
    public ResponseEntity<List<MovieAndActor>> getActiveRecords() {
        logger.info("Fetching All Active Records");
        return ResponseEntity.ok(service.getActiveRecords());
    }

    @GetMapping("/inactive")
    public ResponseEntity<List<MovieAndActor>> getInactiveRecords() {
        logger.info("Fetching All Inactive Records");
        return ResponseEntity.ok(service.getInactiveRecords());
    }

    @PostMapping
    public ResponseEntity<MovieAndActor> createRecord(@RequestBody MovieAndActor movieAndActor) {
        logger.info("Adding Record");
        return ResponseEntity.ok(service.saveRecord(movieAndActor));
    }

//    @PutMapping("/{id}/status")
//    public ResponseEntity<String> updateRecordStatus(
//            @PathVariable Long id, @RequestParam String status) {
//        logger.info("Updating Record Status with ID:"+id);
//        service.updateRecord(id, status);
//        return ResponseEntity.ok("Record status updated successfully");
//    }

    @PutMapping("/{id}/update")
    public ResponseEntity<String> updateRecord(
            @PathVariable Long id,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String review) {
        logger.info("Updating Record with ID: " + id);

        // Call service method to update status and/or review
        service.updateRecord(id, status, review);

        return ResponseEntity.ok("Record updated successfully");
    }

}