package apap.ta.rumahsehat.controller;

import apap.ta.rumahsehat.model.ObatModel;
import apap.ta.rumahsehat.service.ObatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
public class ObatController {
    @Qualifier("obatServiceImpl")
    @Autowired
    private ObatService obatService;

    @GetMapping("/obat")
    public String listObat(Model model){
        List<ObatModel> listObat= obatService.getSortedListObat();
        model.addAttribute("listObat", listObat);
        log.info("Akses Semua Daftar Obat");
        return "viewall-obat";
    }
    
    @GetMapping("/obat/{idObat}/update_stok")
    public String updateObatFormPage(@PathVariable String idObat, Model model) {
        ObatModel obat = obatService.getObatbyId(idObat);
        model.addAttribute("obat", obat);
        log.info("Akses Update Obat");
        return "form-update-stok-obat";
    }

    @PostMapping(value="/obat/{idObat}/update_stok",params = {"save"})
    public String updateObatSubmitPage(@ModelAttribute ObatModel obat, Model model){
        ObatModel updatedObat = obatService.updateStokObat(obat);

        model.addAttribute("nama", updatedObat.getNamaObat());
        log.info("Akses Update Obat");
        return "update-stok-obat";
    }
}
