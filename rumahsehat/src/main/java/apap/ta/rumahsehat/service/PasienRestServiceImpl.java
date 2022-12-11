package apap.ta.rumahsehat.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import apap.ta.rumahsehat.model.PasienModel;
import apap.ta.rumahsehat.repository.ApotekerDb;
import apap.ta.rumahsehat.repository.DokterDb;
import apap.ta.rumahsehat.repository.PasienDb;
import apap.ta.rumahsehat.repository.UserDb;

@Service
@Transactional
public class PasienRestServiceImpl implements PasienRestService {
    
    @Autowired
    private ApotekerDb apotekerDb;

    @Autowired
    private DokterDb dokterDb;

    @Autowired
    private PasienDb pasienDb;

    @Autowired
    private UserDb userDb;

	@Override
	public PasienModel getPasienByUsername(String username) {
		return pasienDb.findByUsername(username);
	}

    @Override
    public PasienModel addPasien(PasienModel pasien)  {

        String uname = pasien.getUsername();
        String email = pasien.getEmail();
        if (apotekerDb.findByUsername(uname) == null && dokterDb.findByUsername(uname) == null && pasienDb.findByUsername(uname) == null && userDb.findByUsername(uname) == null
        && apotekerDb.findByEmail(email) == null && dokterDb.findByEmail(email) == null && pasienDb.findByEmail(email) == null && userDb.findByEmail(email) == null) {
            
            String pass = encrypt(pasien.getPassword());

            pasien.setPassword(pass);
            pasien.setRole("Pasien");
            return pasienDb.save(pasien);
        }
        else {
            PasienModel pasienExist = new PasienModel();
            pasienExist.setUsername("exist");
            return pasienExist;
        }
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }
}
