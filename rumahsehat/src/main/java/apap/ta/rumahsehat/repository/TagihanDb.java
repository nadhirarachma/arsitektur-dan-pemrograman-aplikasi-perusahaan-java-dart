package apap.ta.rumahsehat.repository;
import apap.ta.rumahsehat.model.TagihanModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TagihanDb extends JpaRepository<TagihanModel, Long> {
    
}
