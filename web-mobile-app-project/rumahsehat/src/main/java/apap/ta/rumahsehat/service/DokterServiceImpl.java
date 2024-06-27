package apap.ta.rumahsehat.service;

import apap.ta.rumahsehat.model.DokterModel;
import apap.ta.rumahsehat.repository.ApotekerDb;
import apap.ta.rumahsehat.repository.DokterDb;
import apap.ta.rumahsehat.repository.PasienDb;
import apap.ta.rumahsehat.repository.UserDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DokterServiceImpl implements DokterService {

    @Autowired
    private ApotekerDb apotekerDb;

    @Autowired
    private DokterDb dokterDb;

    @Autowired
    private PasienDb pasienDb;

    @Autowired
    private UserDb userDb;

    @Override
    public DokterModel addDokter(DokterModel dokter) {

        String uname = dokter.getUsername();
        String email = dokter.getEmail();
        if (apotekerDb.findByUsername(uname) == null && dokterDb.findByUsername(uname) == null && pasienDb.findByUsername(uname) == null && userDb.findByUsername(uname) == null
        && apotekerDb.findByEmail(email) == null && dokterDb.findByEmail(email) == null && pasienDb.findByEmail(email) == null && userDb.findByEmail(email) == null) {
            if (dokter.getPassword().matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")) {
                String pass = encrypt(dokter.getPassword());
    
                dokter.setPassword(pass);
                dokter.setRole("Dokter");
                return dokterDb.save(dokter);
            }
            else {
                var dokterPassUnmatched = new DokterModel();
                dokterPassUnmatched.setUsername("unmatched");
                return dokterPassUnmatched;
            }
        }
        else {
            var dokterExist = new DokterModel();
            dokterExist.setUsername("exist");
            return dokterExist;
        }
    }

    @Override
    public String encrypt(String password) {
        var passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    @Override
    public DokterModel getDokterByUsername(String username){
        return dokterDb.findByUsername(username);
    }

    @Override
    public List<DokterModel> getListDokter() {
        return dokterDb.findAll();
    }
}