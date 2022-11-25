package apap.ta.rumahsehat.service;

import apap.ta.rumahsehat.model.PasienModel;

import java.util.List;

public interface PasienService {
    PasienModel getPasienByUsername(String username);
    List<PasienModel> getListPasien();
}