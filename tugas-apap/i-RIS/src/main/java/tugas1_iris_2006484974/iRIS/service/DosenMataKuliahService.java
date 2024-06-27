package tugas1_iris_2006484974.iRIS.service;

import tugas1_iris_2006484974.iRIS.model.DosenMataKuliahModel;
import tugas1_iris_2006484974.iRIS.model.DosenModel;

import java.util.List;

public interface DosenMataKuliahService {
    List<DosenMataKuliahModel> getListDosenMataKuliah();

    void addDosenMataKuliah(DosenMataKuliahModel dosenMatkul);

    List<DosenMataKuliahModel> getListMataKuliah(DosenModel dosen, String mataKuliah);
}

