package apap.tutorial.belajarbelajar.service;

import apap.tutorial.belajarbelajar.model.PengajarModel;
import apap.tutorial.belajarbelajar.repository.PengajarDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class PengajarServiceImpl implements PengajarService {
    @Autowired
    PengajarDb pengajarDb;

    @Override
    public void addPengajar(PengajarModel pengajar) {
        pengajarDb.save(pengajar);
    }

    @Override
    public PengajarModel updatePengajar(PengajarModel pengajar) {
        pengajarDb.save(pengajar);
        return pengajar;
    }

    @Override 
    public PengajarModel getPengajarByNoPengajarQuery(Long noPengajar) {
        Optional<PengajarModel> pengajar = pengajarDb.findPengajarByNoPengajar(noPengajar);
        if (pengajar.isPresent()) {
            return pengajar.get();
        }
        else return null;
    }

    @Override
    public PengajarModel deletePengajar(PengajarModel pengajar) {
        pengajarDb.delete(pengajar);
        return pengajar;
    }
}
