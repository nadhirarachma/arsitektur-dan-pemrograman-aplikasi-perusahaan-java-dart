package apap.ta.rumahsehat.service;

import apap.ta.rumahsehat.model.ApotekerModel;
import apap.ta.rumahsehat.repository.ApotekerDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApotekerServiceImpl implements ApotekerService {

    @Autowired
    private ApotekerDb apotekerDb;

    @Override
    public ApotekerModel addApoteker(ApotekerModel apoteker) {

        if (apoteker.getPassword().matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")) {
            String pass = encrypt(apoteker.getPassword());

            apoteker.setPassword(pass);
            apoteker.setRole("Apoteker");
            return apotekerDb.save(apoteker);
        }

        return new ApotekerModel();
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public ApotekerModel getApotekerByUsername(String username){
        ApotekerModel apoteker = apotekerDb.findByUsername(username);
        return apoteker;
    }

    @Override
    public List<ApotekerModel> getListApoteker() {
        return apotekerDb.findAll();
    }

    @Override
    public ApotekerModel getApotekerById(String uuid){
        ApotekerModel apoteker = apotekerDb.findByUuid(uuid);
        return apoteker;
    }
}
