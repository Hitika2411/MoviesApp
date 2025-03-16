package CRUD.MovieApp.service;

import CRUD.MovieApp.main.MovieAndActor;
import CRUD.MovieApp.repository.MovieAndActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieAndActorService {

    private final MovieAndActorRepository repository;

    @Autowired
    public MovieAndActorService(MovieAndActorRepository repository) {
        this.repository = repository;
    }

    public List<MovieAndActor> getAllRecords() {
        return repository.findAll();
    }

    public List<MovieAndActor> getActiveRecords() {
        return repository.findByRecordStatus("A");
    }

    public List<MovieAndActor> getInactiveRecords() {
        return repository.findByRecordStatus("I");
    }

    public Optional<MovieAndActor> getRecordById(Long id) {
        return repository.findById(id);
    }

    public MovieAndActor saveRecord(MovieAndActor movieAndActor) {
        return repository.save(movieAndActor);
    }

//    public void updateRecordStatus(Long id, String newStatus) {
//        MovieAndActor record = repository.findById(id).orElseThrow(() ->
//                new RuntimeException("Record not found with id: " + id));
//        record.setRecordStatus(newStatus);
//        repository.save(record);
//    }
public void updateRecord(Long id, String newStatus, String newReview) {
    MovieAndActor record = repository.findById(id).orElseThrow(() ->
            new RuntimeException("Record not found with id: " + id));

    // Update status if newStatus is not null
    if (newStatus != null) {
        record.setRecordStatus(newStatus);
    }

    // Update review if newReview is not null
    if (newReview != null) {
        record.setReview(newReview);
    }

    // Save the updated record
    repository.save(record);
}


}
