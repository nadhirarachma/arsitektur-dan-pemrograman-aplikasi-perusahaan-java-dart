package apap.ta.rumahsehat.controller;

import apap.ta.rumahsehat.model.ObatModel;
import apap.ta.rumahsehat.repository.ObatDb;
import apap.ta.rumahsehat.service.ObatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ObatController {
    @Qualifier("obatServiceImpl")
    @Autowired
    private ObatService obatService;

    @Autowired
    private ObatDb obatDb;

    @GetMapping("/obat")
    public String listObat(Model model){
        List<ObatModel> listObat= obatService.getSortedListObat();
        model.addAttribute("listObat", listObat);
        return "viewall-obat";
    }
    
    @GetMapping("/obat/{idObat}/update_stok")
    public String updateObatFormPage(@PathVariable String idObat, Model model) {
        ObatModel obat = obatService.getObatbyId(idObat);
        model.addAttribute("obat", obat);
        return "form-update-stok-obat";
    }

    @PostMapping(value="/obat/{idObat}/update_stok",params = {"save"})
    public String updateObatSubmitPage(@ModelAttribute ObatModel obat, Model model){
        ObatModel updatedObat = obatService.updateStokObat(obat);

        model.addAttribute("id", updatedObat.getIdObat());
        return "update-stok-obat";
    }
}
