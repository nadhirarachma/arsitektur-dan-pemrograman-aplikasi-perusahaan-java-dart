package apap.tutorial.belajarbelajar.repository;

import apap.tutorial.belajarbelajar.model.CourseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface CourseDb extends JpaRepository<CourseModel, String> {
    // JPA
    Optional<CourseModel> findByCode(String code);

    @Query("SELECT c FROM CourseModel c WHERE c.code = :code")
    Optional<CourseModel> findByCodeUsingQuery(@Param("code") String code);

    @Query("FROM CourseModel c ORDER BY c.nameCourse")
    List<CourseModel> findByNameCourseUsingQuery();
}
