package apap.ta.rumahsehat.repository;

import apap.ta.rumahsehat.model.ObatModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ObatDb extends JpaRepository<ObatModel, String> {
}
