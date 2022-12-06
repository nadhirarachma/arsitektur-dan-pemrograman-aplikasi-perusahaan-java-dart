package apap.ta.rumahsehat.service;

import org.springframework.stereotype.Service;
import apap.ta.rumahsehat.model.ResepModel;
import apap.ta.rumahsehat.repository.ResepDb;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ResepServiceImpl implements ResepService{
    @Autowired
    ResepDb resepDb;

    @Override
    public void addResep(ResepModel resep) {
        resepDb.save(resep);
    }

    @Override
    public List<ResepModel> getListResep() {
        return resepDb.findAll();
    }

    @Override
    public ResepModel getResepById(Long id) {
        Optional<ResepModel> resep = resepDb.findById(id);
        if(resep.isPresent()) {
            return resep.get();
        } else return null;
    }

    @Override
    public ResepModel updateResep(ResepModel resep) {
        resepDb.save(resep);
        return resep;
    }

    @Override
    public void deleteResep(ResepModel resep) {
        resepDb.delete(resep);
    }
}
