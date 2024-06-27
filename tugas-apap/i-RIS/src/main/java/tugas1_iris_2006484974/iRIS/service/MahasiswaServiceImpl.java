package tugas1_iris_2006484974.iRIS.service;

import tugas1_iris_2006484974.iRIS.model.MahasiswaModel;
import tugas1_iris_2006484974.iRIS.repository.MahasiswaDb;
import tugas1_iris_2006484974.iRIS.repository.IRSDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

@Service
@Transactional
public class MahasiswaServiceImpl implements MahasiswaService {
    @Autowired
    MahasiswaDb mahasiswaDb;

    @Autowired
    IRSDb IRSDb;

    @Override
    public MahasiswaModel getMahasiswaByNpm(String npm) {
        Optional<MahasiswaModel> mahasiswa = mahasiswaDb.findByNpm(npm);
        if (mahasiswa.isPresent()) {
            return mahasiswa.get();
        }
        else return null;
    }

    @Override
    public List<MahasiswaModel> getListMahasiswa() {
        return mahasiswaDb.findAll();
    }

    @Override
    public List<MahasiswaModel> getListMahasiswaNonAktif() {
        List<MahasiswaModel> mahasiswa = mahasiswaDb.findAll();
        List<MahasiswaModel> mahasiswaDeleted = new ArrayList<>();

        for (MahasiswaModel mhs : mahasiswa) {
            if (mhs.getStatusMahasiswa() != 0) {
                mahasiswaDeleted.add(mhs);
            }
        }   
        mahasiswa.removeAll(mahasiswaDeleted);     
        return mahasiswa;
    }

    @Override
    public String addMahasiswa(MahasiswaModel mahasiswa) {
        String success = "success";

        Set<String> check = new HashSet<>();
        List<MahasiswaModel> listMahasiswa = getListMahasiswa();
        for (MahasiswaModel mhs : listMahasiswa) {
            if (mahasiswa.getNpm().equals(mhs.getNpm())) {
                check.add("npmExist");
            }
            
            if (mahasiswa.getEmail().equals(mhs.getEmail())) {
                check.add("emailExist");
            }
        }

        if (check.contains("npmExist")) {
            success = "npmExist";
        }
        else if (check.contains("emailExist")) {
            success = "emailExist";
        }

        if (success.equals("success")) {
            mahasiswaDb.save(mahasiswa);
        }

        return success;
    }

    @Override
    public MahasiswaModel updateMahasiswa(MahasiswaModel mahasiswa) {
        mahasiswaDb.save(mahasiswa);
        return mahasiswa;
    }

    @Override
    public MahasiswaModel deleteMahasiswa(MahasiswaModel mahasiswa) {
        mahasiswaDb.delete(mahasiswa);
        return mahasiswa;
    }
}
