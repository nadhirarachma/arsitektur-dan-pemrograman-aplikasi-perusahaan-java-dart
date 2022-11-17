package apap.ta.rumahsehat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import apap.ta.rumahsehat.model.DokterModel;

@Repository
public interface DokterDb extends JpaRepository<DokterModel, Long>{
    DokterModel findByUsername(String username);
}
