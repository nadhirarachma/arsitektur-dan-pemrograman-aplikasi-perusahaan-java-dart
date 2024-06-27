package apap.ta.rumahsehat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import apap.ta.rumahsehat.model.ApotekerModel;

@Repository
public interface ApotekerDb extends JpaRepository<ApotekerModel, Long>{
    ApotekerModel findByUsername(String username);
    ApotekerModel findByUuid(String uuid);
    ApotekerModel findByEmail(String email);
}
