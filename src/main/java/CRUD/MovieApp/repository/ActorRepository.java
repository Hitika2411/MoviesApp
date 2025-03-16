package CRUD.MovieApp.repository;

import CRUD.MovieApp.main.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActorRepository extends JpaRepository<Actor,Long>, JpaSpecificationExecutor<Actor> {
    @Query("Select a from Actor a Join MovieAndActor ma on a.actorId=ma.actor.actorId where ma.recordStatus='A'")
    List<Actor> findActiveActors();


}
