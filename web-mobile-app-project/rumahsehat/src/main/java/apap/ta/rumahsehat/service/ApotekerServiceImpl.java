package apap.ta.rumahsehat.service;

import apap.ta.rumahsehat.model.ApotekerModel;
import apap.ta.rumahsehat.repository.ApotekerDb;
import apap.ta.rumahsehat.repository.DokterDb;
import apap.ta.rumahsehat.repository.PasienDb;
import apap.ta.rumahsehat.repository.UserDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApotekerServiceImpl implements ApotekerService {

    @Autowired
    private ApotekerDb apotekerDb;

    @Autowired
    private DokterDb dokterDb;

    @Autowired
    private PasienDb pasienDb;

    @Autowired
    private UserDb userDb;

    @Override
    public ApotekerModel addApoteker(ApotekerModel apoteker) {

        String uname = apoteker.getUsername();
        String email = apoteker.getEmail();
        if (apotekerDb.findByUsername(uname) == null && dokterDb.findByUsername(uname) == null && pasienDb.findByUsername(uname) == null && userDb.findByUsername(uname) == null
        && apotekerDb.findByEmail(email) == null && dokterDb.findByEmail(email) == null && pasienDb.findByEmail(email) == null && userDb.findByEmail(email) == null) {
            if (apoteker.getPassword().matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")) {
                String pass = encrypt(apoteker.getPassword());
    
                apoteker.setPassword(pass);
                apoteker.setRole("Apoteker");
                return apotekerDb.save(apoteker);
            }
            else {
                var apotekerPassUnmatched = new ApotekerModel();
                apotekerPassUnmatched.setUsername("unmatched");
                return apotekerPassUnmatched;
            }
        }
        else {
            var apotekerExist = new ApotekerModel();
            apotekerExist.setUsername("exist");
            return apotekerExist;
        }
    }

    @Override
    public String encrypt(String password) {
        var passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    @Override
    public ApotekerModel getApotekerByUsername(String username){
        return apotekerDb.findByUsername(username);
    }

    @Override
    public List<ApotekerModel> getListApoteker() {
        return apotekerDb.findAll();
    }

    @Override
    public ApotekerModel getApotekerById(String uuid){
        return apotekerDb.findByUuid(uuid);
    }
}
