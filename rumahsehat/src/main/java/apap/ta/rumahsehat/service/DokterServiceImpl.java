package apap.ta.rumahsehat.service;

import apap.ta.rumahsehat.model.DokterModel;
import apap.ta.rumahsehat.repository.DokterDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DokterServiceImpl implements DokterService {

    @Autowired
    private DokterDb dokterDb;

    @Override
    public DokterModel addDokter(DokterModel dokter) {

        if (dokter.getPassword().matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")) {
            String pass = encrypt(dokter.getPassword());

            dokter.setPassword(pass);
            dokter.setRole("Dokter");
            return dokterDb.save(dokter);
        }

        return new DokterModel();
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public DokterModel getDokterByUsername(String Doktername){
        DokterModel Dokter = dokterDb.findByUsername(Doktername);
        return Dokter;
    }

    @Override
    public List<DokterModel> getListDokter() {
        return dokterDb.findAll();
    }
}