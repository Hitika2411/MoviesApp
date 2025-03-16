package CRUD.MovieApp;

import CRUD.MovieApp.main.Movie;
import CRUD.MovieApp.repository.MovieRepository;

import CRUD.MovieApp.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    private MovieService movieService;

    @BeforeEach
    void setUp() {
        // Initialize mocks and inject them into the service
        MockitoAnnotations.openMocks(this);
        movieService = new MovieService(movieRepository);
    }

    @Test
    void testGetAllMovies() {
        // Arrange: Mock data for the repository
        List<Movie> mockMovies = List.of(
                new Movie(1L, "The Good Dinosaur", "Cartoon", 8.2, 300, LocalDate.of(2024, 11, 29), "Hindi")
        );
        when(movieRepository.findAll()).thenReturn(mockMovies);

        // Act: Call the service method
        List<Movie> movies = movieService.getAllMovies();

        // Assert: Verify results
        assertEquals(1, movies.size());
        assertEquals("The Good Dinosaur", movies.get(0).getMovieName());
        assertEquals("Cartoon", movies.get(0).getGenre());
        assertEquals("Hindi", movies.get(0).getLanguage());
        verify(movieRepository, times(1)).findAll();
    }

}
