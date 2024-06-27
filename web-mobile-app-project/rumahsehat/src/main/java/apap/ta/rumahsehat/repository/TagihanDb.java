package apap.ta.rumahsehat.repository;
import apap.ta.rumahsehat.model.TagihanModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagihanDb extends JpaRepository<TagihanModel, String> {
    Optional<TagihanModel> findByKode(String kode);
}
