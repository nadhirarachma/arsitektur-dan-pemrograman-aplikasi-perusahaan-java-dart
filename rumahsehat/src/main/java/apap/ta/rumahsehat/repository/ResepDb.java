package apap.ta.rumahsehat.repository;
import apap.ta.rumahsehat.model.ResepModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResepDb extends JpaRepository<ResepModel, String> {
    
}
