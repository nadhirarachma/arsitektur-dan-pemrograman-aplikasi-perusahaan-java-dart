package apap.ta.rumahsehat.service;
import java.time.LocalDateTime;

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
}
