package apap.tutorial.belajarbelajar.repository;

import apap.tutorial.belajarbelajar.model.PenyelenggaraModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PenyelenggaraDb extends JpaRepository<PenyelenggaraModel, Long> {
    
}

