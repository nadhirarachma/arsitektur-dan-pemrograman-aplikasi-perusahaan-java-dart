package apap.ta.rumahsehat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import apap.ta.rumahsehat.model.UserModel;

@Repository
public interface UserDb extends JpaRepository<UserModel, Long>{
    UserModel findByUsername(String username);
    UserModel findByEmail(String email);
}
