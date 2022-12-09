package apap.ta.rumahsehat.service;

import org.springframework.stereotype.Service;

import apap.ta.rumahsehat.model.JumlahModel;
import apap.ta.rumahsehat.model.ResepModel;
import apap.ta.rumahsehat.payload.JumlahDTO;
import apap.ta.rumahsehat.repository.ResepDb;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class ResepServiceImpl implements ResepService{
    @Autowired
    ResepDb resepDb;

    @Override
    public void addResep(ResepModel resep) {
        resepDb.save(resep);
    }

    @Override
    public List<ResepModel> getListResep() {
        return resepDb.findAll();
    }

    @Override
    public Map<String, JumlahDTO> getListJumlah(Long id) {
        Map<String, JumlahDTO> jumlahDTO = new HashMap<>();
        ResepModel resep = getResepById(id);
        int counter = 1;
        for (JumlahModel i : resep.getJumlah()) {
            JumlahDTO jml = new JumlahDTO();
            jml.setId(i.getId());
            jml.setKuantitas(i.getKuantitas());  
            jml.setNamaObat(i.getObat().getNamaObat());  
            jumlahDTO.put(Integer.toString(counter++), jml); 
        }
        return jumlahDTO;
    }

    @Override
    public ResepModel getResepById(Long id) {
        Optional<ResepModel> resep = resepDb.findById(id);
        if(resep.isPresent()) {
            return resep.get();
        } else return null;
    }

    @Override
    public ResepModel updateResep(ResepModel resep) {
        resepDb.save(resep);
        return resep;
    }

    @Override
    public void deleteResep(ResepModel resep) {
        resepDb.delete(resep);
    }
}
