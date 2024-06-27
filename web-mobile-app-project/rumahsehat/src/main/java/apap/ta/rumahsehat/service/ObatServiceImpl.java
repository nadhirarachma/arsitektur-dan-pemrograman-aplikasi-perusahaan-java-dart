package apap.ta.rumahsehat.service;

import apap.ta.rumahsehat.model.ObatModel;
import apap.ta.rumahsehat.repository.ObatDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ObatServiceImpl implements ObatService{
    @Autowired
    ObatDb obatDb;

    @Override
    public ObatModel updateStokObat(ObatModel obat) {
        obatDb.save(obat);
        return obat;
    }

    @Override
    public List<ObatModel> getSortedListObat() {
        return obatDb.findAll(Sort.by(Sort.Direction.ASC, "idObat"));
    }

    @Override
    public ObatModel getObatbyId(String id) {
        Optional<ObatModel> obat = obatDb.findById(id);
        if (obat.isPresent()) {
            return obat.get();
        } else return null;
    }
}
