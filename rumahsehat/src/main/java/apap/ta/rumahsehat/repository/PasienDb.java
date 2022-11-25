<<<<<<< Updated upstream
package apap.ta.rumahsehat.repository;

public class PasienDb {
    
}
=======
package apap.ta.rumahsehat.repository;


import apap.ta.rumahsehat.model.PasienModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasienDb extends JpaRepository<PasienModel, String> {
    PasienModel findByUsername(String pasien);
    
}
>>>>>>> Stashed changes
