package tugas1_iris_2006484974.iRIS.service;

import tugas1_iris_2006484974.iRIS.model.DosenMataKuliahKey;
import tugas1_iris_2006484974.iRIS.model.DosenMataKuliahModel;
import tugas1_iris_2006484974.iRIS.model.DosenModel;
import tugas1_iris_2006484974.iRIS.repository.DosenMataKuliahDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DosenMataKuliahServiceImpl implements DosenMataKuliahService {
    @Autowired
    DosenMataKuliahDb dosenMataKuliahDb;

    @Override
    public List<DosenMataKuliahModel> getListDosenMataKuliah() {
        return dosenMataKuliahDb.findAll();
    }

    @Override
    public void addDosenMataKuliah(DosenMataKuliahModel dosenMatkul) {
        dosenMatkul.setKey(new DosenMataKuliahKey(dosenMatkul.getDosen().getNip(), dosenMatkul.getMataKuliah().getIdMataKuliah()));
        dosenMataKuliahDb.save(dosenMatkul);
    }

    @Override
    public List<DosenMataKuliahModel> getListMataKuliah(DosenModel dosen, String mataKuliah) {
        return dosenMataKuliahDb.findMataKuliahByDosen(dosen, mataKuliah);
    }
}