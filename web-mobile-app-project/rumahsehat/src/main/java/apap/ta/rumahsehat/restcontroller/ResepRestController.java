package apap.ta.rumahsehat.restcontroller;


import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import apap.ta.rumahsehat.model.ResepModel;
import apap.ta.rumahsehat.payload.JumlahDTO;
import apap.ta.rumahsehat.service.ResepService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1")
public class ResepRestController {
    @Autowired
    private ResepService resepService;

    //    Retrieve Detail Resep
    @GetMapping("/resep/view/{id}")
    public ResepModel retrieveResep(@PathVariable("id") Long id){
        try {
            return resepService.getResepById(id);
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(
                 HttpStatus.NOT_FOUND, "ID Resep " + id + " not found"
            );
        }
    }

    @GetMapping(value = "/resep")
    public List<ResepModel> retrieveListResep() {
        return resepService.getListResep();
    }

    @GetMapping(value = "/resep/jumlah/{id}")
    public Map<String, JumlahDTO> retrieveListJumlah(@PathVariable("id") Long id) {
        return resepService.getListJumlah(id);
    }

}