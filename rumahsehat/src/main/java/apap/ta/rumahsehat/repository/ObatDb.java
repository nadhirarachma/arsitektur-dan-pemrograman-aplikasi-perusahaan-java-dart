package apap.ta.rumahsehat.repository;

import apap.ta.rumahsehat.model.ObatModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ObatDb extends JpaRepository<ObatModel, String> {
}
