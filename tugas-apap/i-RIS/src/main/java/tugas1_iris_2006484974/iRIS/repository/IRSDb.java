package tugas1_iris_2006484974.iRIS.repository;

import tugas1_iris_2006484974.iRIS.model.IRSModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRSDb extends JpaRepository<IRSModel, Long> {
    // JPA
    Optional<IRSModel> findById(Long idIRS);
}

