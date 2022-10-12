package tugas1_iris_2006484974.iRIS.service;

import tugas1_iris_2006484974.iRIS.model.IRSModel;
import tugas1_iris_2006484974.iRIS.model.MahasiswaModel;
import tugas1_iris_2006484974.iRIS.model.MataKuliahModel;
import tugas1_iris_2006484974.iRIS.repository.IRSDb;
import tugas1_iris_2006484974.iRIS.repository.MahasiswaDb;
import tugas1_iris_2006484974.iRIS.repository.MataKuliahDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

@Service
@Transactional
public class IRSServiceImpl implements IRSService {
    @Autowired
    IRSDb IRSDb;

    @Autowired
    MahasiswaDb mahasiswaDb;

    @Autowired
    MataKuliahDb mataKuliahDb;

    @Override
    public IRSModel getIRSById(Long idIRS) {
        Optional<IRSModel> irs = IRSDb.findById(idIRS);
        if (irs.isPresent()) {
            countJumlahSks(irs.get());
            return irs.get();
        }
        else return null;
    }

    @Override
    public String getSemesterName(IRSModel irs) {
        String inputSemester = irs.getSemester();
        String codeSemester = inputSemester.substring(0, 3).toUpperCase();
        String tahunAjaran =  "";
   
        if (codeSemester.equals("GAN")) {
            tahunAjaran = irs.getSemester().substring(9,11) + "/" + irs.getSemester().substring(14,16);
        }
        else if (codeSemester.equals("GEN")) {
            tahunAjaran = irs.getSemester().substring(8,10) + "/" + irs.getSemester().substring(13,15);
        }
        String smt = codeSemester + tahunAjaran;

        return smt;
    }

    @Override
    public boolean countJumlahSks(IRSModel irs) {
        List<MataKuliahModel> listMatkul = irs.getListMataKuliah();
        int jumlahSks = 0;
        for (MataKuliahModel x : listMatkul) {
            jumlahSks += x.getSks();
        }

        boolean failed = false;
        if (jumlahSks > 24) {
            failed = true;
        }
        else {
            irs.setJumlahSks(jumlahSks);
        }
        return failed;
    }

    @Override
    public String checkSemesterMatkul(IRSModel irs) {
        String namaMatkul = "";
        for (MataKuliahModel matkul : irs.getListMataKuliah()) {
            if (!(matkul.getSemester().substring(0,3).toUpperCase()).equals(irs.getSemester().substring(0, 3).toUpperCase())) {
                namaMatkul = matkul.getNamaMatkul();
            }
        }
        return namaMatkul;
    }

    @Override
    public String addIRS(IRSModel irs, MahasiswaModel npmMahasiswa) {
        String smt = getSemesterName(irs);

        String success = "success";
        Set<String> check = new HashSet<>();
        for (IRSModel irsMhs : npmMahasiswa.getListIRS()) {
            if (irsMhs.getSemester().equals(smt)) {
                check.add("exist");
            }
        }
       
        if (countJumlahSks(irs) == true) {
            check.add("24");
        }
       
        for (MataKuliahModel matkul : irs.getListMataKuliah()) {
            if (matkul.getKapasitasKelas() == matkul.getTotalMahasiswa()) {
                check.add("full");
            }

            if (!(matkul.getSemester().substring(0,3).toUpperCase()).equals(irs.getSemester().substring(0, 3).toUpperCase())) {
                check.add("wrong");
            }
        }

        if (check.contains("exist")) {
            success = "exist";
        }
        else if (check.contains("24")) {
            success = "24";
        }
        else if (check.contains("full")) {
            success = "full";
        }
        else if (check.contains("wrong")) {
            success = "wrong";
        }

        if (success.equals("success")) {
            irs.setSemester(smt);
            irs.setStatus("Belum Disetujui");
            irs.setNpmMahasiswa(npmMahasiswa);
    
            IRSDb.save(irs);
        }

        return success;
    }

    @Override
    public IRSModel updateIRS(IRSModel irs) {
        IRSDb.save(irs);
        return irs;
    }

    @Override
    public IRSModel deleteIRS(IRSModel irs) {
        IRSDb.delete(irs);
        return irs;
    }
}
