package apap.ta.rumahsehat.repository;
import apap.ta.rumahsehat.model.ResepModel;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResepDb extends JpaRepository<ResepModel, Long> {
    Optional<ResepModel> findById(Long id);
    List<ResepModel> findAllByOrderById();
}
