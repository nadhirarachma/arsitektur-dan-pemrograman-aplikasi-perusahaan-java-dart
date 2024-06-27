package tugas1_iris_2006484974.iRIS.service;

import tugas1_iris_2006484974.iRIS.model.DosenModel;
import java.util.List;

public interface DosenService {

    DosenModel getDosenByNip(String nip);

    List<DosenModel> getListDosen();

    void addDosen(DosenModel dosen);

    DosenModel updateDosen(DosenModel dosen);

    DosenModel deleteDosen(DosenModel dosen);
}

