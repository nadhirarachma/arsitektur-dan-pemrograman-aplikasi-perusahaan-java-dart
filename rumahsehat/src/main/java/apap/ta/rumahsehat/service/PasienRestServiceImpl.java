package apap.ta.rumahsehat.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import apap.ta.rumahsehat.model.PasienModel;
import apap.ta.rumahsehat.repository.PasienDb;

@Service
@Transactional
public class PasienRestServiceImpl implements PasienRestService {
    @Autowired
    private PasienDb pasienDb;

	@Override
	public PasienModel getPasienByUsername(String username) {
		return pasienDb.findByUsername(username);
	}

    @Override
    public PasienModel addPasien(PasienModel pasien)  {
        String pass = encrypt(pasien.getPassword());

        pasien.setPassword(pass);
        pasien.setRole("Pasien");

        return pasienDb.save(pasien);
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }
}
