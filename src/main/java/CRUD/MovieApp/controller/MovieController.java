package CRUD.MovieApp.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import CRUD.MovieApp.main.Movie;
import CRUD.MovieApp.service.MovieService;
import jakarta.validation.Valid;

@RestController
@RequestMapping
@Valid



public class MovieController {

    private static final Logger logger= LoggerFactory.getLogger(MovieController.class);

    private final MovieService movieService;
    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movie")
    public List<Movie> getAllMovies(){
        logger.info("Get All Movies called");
        List<Movie> movies = movieService.getAllMovies();
        logger.debug("getAllMovies returned {}",movies);
        return movies;
//        return movieService.getAllMovies();
    }


@PostMapping("/movie")
public ResponseEntity<String> addMovie(@Valid @RequestBody Movie movie, BindingResult result){
    if(result.hasErrors()){
        // Collecting all validation error messages
        String errorMessages = result.getAllErrors().stream()
                .map(error -> error.getDefaultMessage())  // Get message for both FieldError and ObjectError
                .collect(Collectors.joining(" \n"));

        logger.warn("Validation errors: {}", errorMessages);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages); // Return the error message as a String
    }
    logger.info("Add Movie called: {}", movie);
    Movie newMovie = movieService.addMovie(movie);
    logger.debug("Movie added successfully {}", newMovie);
    return new ResponseEntity<>(newMovie.toString(), HttpStatus.CREATED); // Normal case, returning the Movie
}

    //Update
    @PutMapping("/movie/{movieId}")
    public ResponseEntity<String> updateMovie(@Valid @PathVariable Long movieId,
                                              @RequestParam Map<String, Object> updates) {
        logger.info("Request received for Updating movie: {}",updates);
        try {
            movieService.updateMovie(movieId, updates);
            return ResponseEntity.ok().body("Movie updated successfully");
        }
        catch (RuntimeException exp) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exp.getMessage());
        }
    }

    //Search
    @GetMapping("/search")
    public ResponseEntity<List<Movie>> searchMovie(
            @RequestParam(required = false) Long movieId,
            @RequestParam(required = false) String movieName,
            @RequestParam(required = false) Integer duration,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) Double rating,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate releaseDate,
            @RequestParam(required = false) String language) {

        logger.info("Request received for Searching movie: {}",movieId);

        List<Movie> movies = movieService.searchMovies(movieId, movieName, duration, genre, rating, releaseDate, language);
        return ResponseEntity.ok(movies);
    }

    //Get Active Records
    @GetMapping("/movie/actor")
    public ResponseEntity<List<Movie>> getActiveActors(){
        logger.info("Get Active Actors called");
        List<Movie> activeMovies=movieService.getActiveMovies();
        logger.debug("Get Active Movies {}",activeMovies);
        return ResponseEntity.ok(movieService.getActiveMovies());
    }

}
