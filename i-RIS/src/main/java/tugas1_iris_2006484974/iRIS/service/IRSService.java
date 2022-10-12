package tugas1_iris_2006484974.iRIS.service;

import tugas1_iris_2006484974.iRIS.model.IRSModel;
import tugas1_iris_2006484974.iRIS.model.MahasiswaModel;

public interface IRSService {

    IRSModel getIRSById(Long idIRS);

    String getSemesterName(IRSModel irs);

    boolean countJumlahSks(IRSModel irs);

    String checkSemesterMatkul(IRSModel irs);

    String addIRS(IRSModel irs, MahasiswaModel npmMahasiswa);

    IRSModel updateIRS(IRSModel irs);

    IRSModel deleteIRS(IRSModel irs);
}

