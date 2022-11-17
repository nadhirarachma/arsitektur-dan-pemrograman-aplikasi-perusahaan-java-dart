package apap.ta.rumahsehat.service;

import apap.ta.rumahsehat.model.DokterModel;

import java.util.List;

public interface DokterService {
    DokterModel addDokter(DokterModel dokter);
    public String encrypt(String password);
    DokterModel getDokterByUsername(String username);
    List<DokterModel> getListDokter();
}