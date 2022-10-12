package tugas1_iris_2006484974.iRIS.service;

import tugas1_iris_2006484974.iRIS.model.DosenModel;
import tugas1_iris_2006484974.iRIS.repository.DosenDb;
import tugas1_iris_2006484974.iRIS.repository.MataKuliahDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.List;

@Service
@Transactional
public class DosenServiceImpl implements DosenService {
    @Autowired
    DosenDb dosenDb;

    @Autowired
    MataKuliahDb mataKuliahDb;

    @Override
    public DosenModel getDosenByNip(String nip) {
        Optional<DosenModel> dosen = dosenDb.findByNip(nip);
        if (dosen.isPresent()) {
            return dosen.get();
        }
        else return null;
    }

    @Override
    public List<DosenModel> getListDosen() {
        return dosenDb.findAll();
    }

    @Override
    public void addDosen(DosenModel dosen) {
        dosenDb.save(dosen);
    }

    @Override
    public DosenModel updateDosen(DosenModel dosen) {
        dosenDb.save(dosen);
        return dosen;
    }

    @Override
    public DosenModel deleteDosen(DosenModel dosen) {
        dosenDb.delete(dosen);
        return dosen;
    }
}
