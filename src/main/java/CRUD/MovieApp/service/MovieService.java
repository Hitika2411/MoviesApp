package CRUD.MovieApp.service;

import CRUD.MovieApp.main.Movie;
import CRUD.MovieApp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.persistence.criteria.Predicate;



@Service
public class MovieService {
    private final MovieRepository movieRepository;
    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }
    public Movie addMovie(Movie movie){
        return movieRepository.save(movie);
    }

    public void updateMovie(Long movieId, Map<String, Object> updates) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        if (updates.containsKey("movieName")) {
            movie.setMovieName((String) updates.get("movieName"));
        }
        if (updates.containsKey("genre")) {
            movie.setGenre((String) updates.get("genre"));
        }
        if (updates.containsKey("language")) {
            movie.setLanguage((String) updates.get("language"));
        }

        if (updates.containsKey("duration")) {
            movie.setDuration(Integer.parseInt(updates.get("duration").toString()));
        }
        if(updates.containsKey("rating")){
            movie.setRating(Double.parseDouble(updates.get("rating").toString()));
        }

        // Save updated movie to the repository
        movieRepository.save(movie);
    }

    public List<Movie> searchMovies(Long movieId, String movieName, Integer duration, String genre, Double rating, LocalDate releaseDate, String language) {
        return movieRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(movieId != null){
                predicates.add(criteriaBuilder.equal(root.get("movieId"), movieId));
            }
            if (movieName != null) {
                predicates.add(criteriaBuilder.equal(root.get("movieName"), movieName));
            }
            if (duration != null) {
                predicates.add(criteriaBuilder.equal(root.get("duration"), duration));
            }
            if (genre != null) {
                predicates.add(criteriaBuilder.equal(root.get("genre"), genre));
            }
            if (rating != null) {
                predicates.add(criteriaBuilder.equal(root.get("rating"), rating));
            }
            if (releaseDate != null) {
                predicates.add(criteriaBuilder.equal(root.get("releaseDate"), releaseDate));
            }
            if (language != null) {
                predicates.add(criteriaBuilder.equal(root.get("language"), language));
            }

            // Combine all predicates with and
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }

    //Get Active Records
    public List<Movie> getActiveMovies() {
        return movieRepository.findActiveMovies();
    }

}