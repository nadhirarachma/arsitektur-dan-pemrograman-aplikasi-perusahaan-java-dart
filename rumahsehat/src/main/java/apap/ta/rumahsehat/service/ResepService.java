package apap.ta.rumahsehat.service;

import apap.ta.rumahsehat.model.ResepModel;
import apap.ta.rumahsehat.payload.JumlahDTO;

import java.util.List;
import java.util.Map;

public interface ResepService {
    void addResep(ResepModel resep);
    //Method untuk mendapatkan daftar course yang telah tersimpan
    List<ResepModel> getListResep();
    ResepModel getResepById(Long id);
    ResepModel updateResep(ResepModel resep);
    void deleteResep(ResepModel resep);
    Map<String, JumlahDTO> getListJumlah(Long id);
}
