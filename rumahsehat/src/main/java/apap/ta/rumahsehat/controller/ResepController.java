package apap.ta.rumahsehat.controller;

import apap.ta.rumahsehat.model.ResepModel;
import apap.ta.rumahsehat.model.ObatModel;
import apap.ta.rumahsehat.model.JumlahModel;
import apap.ta.rumahsehat.service.ResepService;
import apap.ta.rumahsehat.service.ObatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import apap.ta.rumahsehat.repository.ObatDb;
import apap.ta.rumahsehat.repository.ResepDb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ResepController {
    @Qualifier("resepServiceImpl")
    @Autowired
    private ResepService resepService;

    @Qualifier("obatServiceImpl")
    @Autowired
    private ObatService obatService;

    @Autowired
    private ResepDb resepDb;

    @GetMapping("/resep")
    public String listResep(Model model){
        List<ResepModel> listResep = resepService.getListResep();
        model.addAttribute("listResep", listResep);
        return "viewall-resep";
    }

    @GetMapping("/resep/add")
    public String addResepFormPage(Model model) {
        ResepModel resep = new ResepModel();
        
        List<ObatModel> listObat = obatService.getSortedListObat();
        System.out.println(listObat.get(0).getNamaObat());
        List<JumlahModel> listJumlahNew = new ArrayList<>();

        resep.setJumlah(listJumlahNew);
        resep.getJumlah().add(new JumlahModel());

        model.addAttribute("resep", resep);
        model.addAttribute("listObatExisting", listObat);
        return "form-add-resep";
    }

    @PostMapping("/resep/add")
    public String addResepSubmitPage(@ModelAttribute ResepModel resep, @ModelAttribute JumlahModel jumlah, Model model) {
        if (resep.getJumlah() == null) {
            resep.setJumlah(new ArrayList<>());
        }
        for (JumlahModel jml : resep.getJumlah()) {
            jml.setResep(resep);
        }
        resep.setIsDone(false);
        resepService.addResep(resep);
        model.addAttribute("id", resep.getId());
        return "add-resep";
    }

    @PostMapping(value="/resep/add", params = {"addRow"})
    private String addRowResepMultiple(
            @ModelAttribute ResepModel resep,
            Model model
    ){
        if (resep.getJumlah() == null || resep.getJumlah().size()==0){
            resep.setJumlah(new ArrayList<>());
        }

        resep.getJumlah().add(new JumlahModel());
        List<ObatModel> listObat = obatService.getSortedListObat();

        model.addAttribute("resep", resep);
        model.addAttribute("listObatExisting", listObat);
        return "form-add-resep";
    }

    @PostMapping(value="/resep/add", params = {"deleteRow"})
    private String deleteRowResepMultiple(
            @ModelAttribute ResepModel resep,
            @RequestParam("deleteRow") Integer row,
            Model model
    ){
        final Integer rowId = Integer.valueOf(row);
        resep.getJumlah().remove(rowId.intValue());

        List<ObatModel> listObat = obatService.getSortedListObat();

        model.addAttribute("resep", resep);
        model.addAttribute("listObatExisting", listObat);
        return "form-add-resep";
    }

}
