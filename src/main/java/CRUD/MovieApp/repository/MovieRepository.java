package CRUD.MovieApp.repository;


import CRUD.MovieApp.main.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {
    //For Active Records
    @Query("Select m from Movie m JOIN MovieAndActor ma on m.movieId=ma.movie.movieId where ma.recordStatus='A'")
    List<Movie> findActiveMovies();
}