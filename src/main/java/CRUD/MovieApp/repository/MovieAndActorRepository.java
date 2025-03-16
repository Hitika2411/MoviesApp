package CRUD.MovieApp.repository;

import CRUD.MovieApp.main.MovieAndActor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieAndActorRepository extends JpaRepository<MovieAndActor, Long> {

    @Query("SELECT ma FROM MovieAndActor ma WHERE ma.recordStatus = :status")
    List<MovieAndActor> findByRecordStatus(@Param("status") String status);

    @Modifying
    @Query("Update MovieAndActor ma SET ma.recordStatus=:status WHERE ma.id=:id")
    int updateRecordStatus(@Param("id") Long id, @Param("status") String status);

}
