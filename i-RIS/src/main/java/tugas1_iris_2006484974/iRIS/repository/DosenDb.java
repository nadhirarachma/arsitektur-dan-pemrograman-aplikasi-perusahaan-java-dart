package tugas1_iris_2006484974.iRIS.repository;

import tugas1_iris_2006484974.iRIS.model.DosenModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DosenDb extends JpaRepository<DosenModel, String> {
    // JPA
    Optional<DosenModel> findByNip(String nip);
}

