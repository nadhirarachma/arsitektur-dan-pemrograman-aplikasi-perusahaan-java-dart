package tugas1_iris_2006484974.iRIS.service;

import tugas1_iris_2006484974.iRIS.model.MahasiswaModel;
import java.util.List;

public interface MahasiswaService {

    MahasiswaModel getMahasiswaByNpm(String npm);

    List<MahasiswaModel> getListMahasiswa();

    List<MahasiswaModel> getListMahasiswaNonAktif();

    String addMahasiswa(MahasiswaModel mahasiswa);

    MahasiswaModel updateMahasiswa(MahasiswaModel mahasiswa);

    MahasiswaModel deleteMahasiswa(MahasiswaModel mahasiswa);
}

