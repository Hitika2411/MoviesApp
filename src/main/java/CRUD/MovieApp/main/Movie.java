package CRUD.MovieApp.main;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Table(name="movie")
public class Movie {
    @Id
    @SequenceGenerator(

            name = "Movie_Sequence",
            sequenceName = "Movie_Sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Movie_Sequence"
    )

    private Long movieId;

    @Setter
    @NotBlank(message = "Movie name must not be empty")
    @Size(max=100,message = "Movie name must not be exceed 100 characters")
    private String movieName;

    @Setter
    @NotBlank(message = "Movie genre must not be empty")
    @Size(max=50,message = "Movie genre must not be exceed 50 characters")
    private String genre;

    @Setter
    @DecimalMin(value = "0.0",message = "Rating must be at least 0")
    @DecimalMax(value = "10.0",message = "Rating must be at most 10.0")
    private Double rating;

    @Setter
    @Min(value=1,message = "Duration must be at least 1 minute")
    private Integer duration;

    @Setter
    @PastOrPresent(message = "Release Date cannot be of future")
    private LocalDate releaseDate;

    @Setter
    @Size(max=50, message = "Language must not exceed 50 characters")
    private String language;

    public Movie(long l, String moana2, String comedy, String english, int i, double v, Object o, boolean b){}

    public Movie(Long movieId, String movieName, String genre, Double rating, Integer duration, LocalDate releaseDate, String language) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.genre = genre;
        this.rating = rating;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.language = language;
    }

    public Movie() {
    }

    public Long getMovieId() {
        return movieId;
    }
    public void setMovie_id(Long movie_id) {
        this.movieId = movie_id;
    }
    public String getMovieName() {
        return movieName;
    }

    public String getGenre() {
        return genre;
    }

    public Double getRating() {
        return rating;
    }

    public Integer getDuration() {
        return duration;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public String getLanguage() {
        return language;
    }

    @Override
    public String toString() {
        return "Movie [movie id=" + movieId + ", movie name=" + movieName + ", genre=" + genre+
                ", rating=" + rating + ", duration=" + duration + "minutes , release date=" + releaseDate + ", language="+
                language+"]";
    }

}