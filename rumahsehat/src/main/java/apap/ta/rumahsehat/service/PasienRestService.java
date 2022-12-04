package apap.ta.rumahsehat.service;

import apap.ta.rumahsehat.model.PasienModel;

public interface PasienRestService {
	PasienModel getPasienByUsername(String username);
	PasienModel createPasien(PasienModel pasien);
}
