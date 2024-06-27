package tugas1_iris_2006484974.iRIS.repository;

import tugas1_iris_2006484974.iRIS.model.DosenMataKuliahModel;
import tugas1_iris_2006484974.iRIS.model.DosenModel;
import tugas1_iris_2006484974.iRIS.model.DosenMataKuliahKey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface DosenMataKuliahDb extends JpaRepository<DosenMataKuliahModel, DosenMataKuliahKey> {
    // JPA
    // Optional<DosenMataKuliahModel> findByNip(DosenMataKuliahKey key);

    @Query("FROM DosenMataKuliahModel d WHERE d.dosen = :dosen AND d.mataKuliah.semester = :mataKuliah")
    List<DosenMataKuliahModel> findMataKuliahByDosen(@Param("dosen") DosenModel dosen, @Param("mataKuliah") String mataKuliah);
}

