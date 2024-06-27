package tugas1_iris_2006484974.iRIS.repository;

import tugas1_iris_2006484974.iRIS.model.MahasiswaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MahasiswaDb extends JpaRepository<MahasiswaModel, String> {
    // JPA
    Optional<MahasiswaModel> findByNpm(String npm);
}

