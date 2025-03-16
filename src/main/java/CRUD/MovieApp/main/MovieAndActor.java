package CRUD.MovieApp.main;

import jakarta.persistence.*;

@Entity
@Table(name = "movie_and_actor")
public class MovieAndActor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "actor_id", nullable = false)
    private Actor actor;

    private String review;

    @Column(name = "record_status", nullable = false)
    private String recordStatus;

    // Constructors
    public MovieAndActor() {
    }

    public MovieAndActor(Movie movie, Actor actor, String review, String recordStatus) {
        this.movie = movie;
        this.actor = actor;
        this.review = review;
        this.recordStatus = recordStatus;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    // toString Method for Debugging
    @Override
    public String toString() {
        return "MovieAndActor{" +
                "id=" + id +
                ", movie=" + (movie != null ? movie.getMovieName() : null) +
                ", actor=" + (actor != null ? actor.getName() : null) +
                ", review='" + review + '\'' +
                ", recordStatus='" + recordStatus + '\'' +
                '}';
    }
}