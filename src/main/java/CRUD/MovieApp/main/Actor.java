package CRUD.MovieApp.main;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="actor")
public class Actor {
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

    private Long actorId;
    private String name;
    private String gender;
    public Actor() {}
    public Actor(Long actorId, String name, String gender) {
        this.actorId = actorId;
        this.name = name;
        this.gender = gender;
    }
    public Long getActorId() {
        return actorId;
    }
    public void setActorId(Long actorId) {
        this.actorId = actorId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    @Override
    public String toString() {
        return "Actor [actorId=" + actorId + ", name=" + name + ", gender=" + gender + "]";
    }
}