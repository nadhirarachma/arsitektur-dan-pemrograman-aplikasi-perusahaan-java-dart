package tugas1_iris_2006484974.iRIS.service;

import tugas1_iris_2006484974.iRIS.model.MataKuliahModel;
import tugas1_iris_2006484974.iRIS.model.IRSModel;
import tugas1_iris_2006484974.iRIS.repository.MataKuliahDb;
import tugas1_iris_2006484974.iRIS.repository.IRSDb;
import tugas1_iris_2006484974.iRIS.repository.DosenDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.List;
import java.util.Random;
import java.time.LocalTime;
import java.util.Set;
import java.util.HashSet;

@Service
@Transactional
public class MataKuliahServiceImpl implements MataKuliahService {
    @Autowired
    MataKuliahDb mataKuliahDb;

    @Autowired
    IRSDb IRSDb;

    @Autowired
    DosenDb dosenDb;

    @Override
    public MataKuliahModel getMataKuliahById(Long idMataKuliah) {
        Optional<MataKuliahModel> mataKuliah = mataKuliahDb.findById(idMataKuliah);

        if (mataKuliah.isPresent()) {
            countTotalMahasiswa(mataKuliah.get());
            return mataKuliah.get();
        }
        else return null;
    }

    @Override
    public List<MataKuliahModel> getMataKuliahBySemester(String semester) {
        List<MataKuliahModel> mataKuliah = mataKuliahDb.findBySemester(semester);
        return mataKuliah;
    }

    @Override
    public List<MataKuliahModel> getListMataKuliah() {
        List<MataKuliahModel> listMatkul = mataKuliahDb.findAll();

        for (MataKuliahModel matkul : listMatkul) {
            countTotalMahasiswa(matkul);
        }
        return listMatkul;
    }

    @Override
    public List<MataKuliahModel> getListMataKuliahOrderByNama() {
        List<MataKuliahModel> listMatkul = mataKuliahDb.findMatkulOrderByNama();

        for (MataKuliahModel matkul : listMatkul) {
            countTotalMahasiswa(matkul);
        }
        return listMatkul;
    }

    @Override
    public String generateCode(MataKuliahModel matkul, String namaMataKuliah, String semester, LocalTime waktuMulai, int sks) {
        String namaMatkul = namaMataKuliah.substring(0,3).toUpperCase();
        String semesterCapital = semester.substring(0,3).toUpperCase();
        String startTime = waktuMulai.toString().substring(0,2);

        int durasiKelas = sks * 50;
        LocalTime waktuSelesai = waktuMulai.plusMinutes(durasiKelas);
        String endTime = waktuSelesai.toString().substring(0,2);
        matkul.setWaktuSelesai(waktuSelesai);

        Random random = new Random();
        char randomChar1 = (char) (random.nextInt(26) + 'A');
        char randomChar2 = (char) (random.nextInt(26) + 'A');
        String randomCapital = randomChar1 + "" + randomChar2;

        String code = "MK" + namaMatkul + semesterCapital + startTime + endTime + randomCapital;
        return code;
    }

    @Override
    public void addMataKuliah(MataKuliahModel mataKuliah) {
        mataKuliahDb.save(mataKuliah);
    }

    @Override
    public MataKuliahModel updateMataKuliah(MataKuliahModel mataKuliah) {
        mataKuliahDb.save(mataKuliah);
        return mataKuliah;
    }

    @Override
    public MataKuliahModel deleteMataKuliah(MataKuliahModel mataKuliah) {
        mataKuliahDb.delete(mataKuliah);
        return mataKuliah;
    }

    @Override
    public void countTotalMahasiswa(MataKuliahModel mataKuliah) {
        List<IRSModel> listIRS = mataKuliah.getListIRS();
        Set<String> listMahasiswa = new HashSet<>();

        for (IRSModel irs : listIRS) {
            listMahasiswa.add(irs.getNpmMahasiswa().getNpm());
        }

        int totalMahasiswa = listMahasiswa.size();
        mataKuliah.setTotalMahasiswa(totalMahasiswa);
    }
}
