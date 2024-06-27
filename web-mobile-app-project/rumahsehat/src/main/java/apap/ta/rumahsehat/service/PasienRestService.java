package apap.ta.rumahsehat.service;

import apap.ta.rumahsehat.model.PasienModel;

public interface PasienRestService {
	PasienModel getPasienByUsername(String username);
    PasienModel addPasien(PasienModel pasien);
    public String encrypt(String password);
}
