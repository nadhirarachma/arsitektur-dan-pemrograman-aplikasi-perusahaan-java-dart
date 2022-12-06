package apap.ta.rumahsehat.service;

import apap.ta.rumahsehat.model.ResepModel;

import java.util.List;

public interface ResepService {
    void addResep(ResepModel resep);
    //Method untuk mendapatkan daftar course yang telah tersimpan
    List<ResepModel> getListResep();
    ResepModel getResepById(Long id);
    ResepModel updateResep(ResepModel resep);
    void deleteResep(ResepModel resep);
}
