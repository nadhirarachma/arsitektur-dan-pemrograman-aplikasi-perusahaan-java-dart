package tugas1_iris_2006484974.iRIS.repository;

import tugas1_iris_2006484974.iRIS.model.MataKuliahModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;

@Repository
public interface MataKuliahDb extends JpaRepository<MataKuliahModel, Long> {
    // JPA
    Optional<MataKuliahModel> findById(Long idMataKuliah);

    @Query("FROM MataKuliahModel m WHERE m.semester = :semester")
    List<MataKuliahModel> findBySemester(@Param("semester") String semester);

    @Query("FROM MataKuliahModel m ORDER BY m.namaMatkul")
    List<MataKuliahModel> findMatkulOrderByNama();
}

