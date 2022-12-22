package apap.ta.rumahsehat.service;

import apap.ta.rumahsehat.model.PasienModel;
import apap.ta.rumahsehat.repository.PasienDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasienServiceImpl implements PasienService {

    @Autowired
    private PasienDb pasienDb;

    @Override
    public PasienModel getPasienByUsername(String username){
        PasienModel pasien = pasienDb.findByUsername(username);
        return pasien;
    }

    @Override
    public List<PasienModel> getListPasien() {
        return pasienDb.findAll();
    }
}
