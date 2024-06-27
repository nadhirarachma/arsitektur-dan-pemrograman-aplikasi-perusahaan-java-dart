package tugas1_iris_2006484974.iRIS.service;

import tugas1_iris_2006484974.iRIS.model.MataKuliahModel;

import java.util.List;
import java.time.LocalTime;

public interface MataKuliahService {

    MataKuliahModel getMataKuliahById(Long idMataKuliah);

    List<MataKuliahModel> getMataKuliahBySemester(String semester);

    List<MataKuliahModel> getListMataKuliah();

    List<MataKuliahModel> getListMataKuliahOrderByNama();

    String generateCode(MataKuliahModel matkul, String namaMataKuliah, String semester, LocalTime waktu, int sks);

    void addMataKuliah(MataKuliahModel mataKuliah);

    MataKuliahModel updateMataKuliah(MataKuliahModel mataKuliah);

    MataKuliahModel deleteMataKuliah(MataKuliahModel mataKuliah);

    void countTotalMahasiswa(MataKuliahModel mataKuliah);
}

