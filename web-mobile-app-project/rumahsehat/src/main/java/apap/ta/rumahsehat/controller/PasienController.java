package apap.ta.rumahsehat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import apap.ta.rumahsehat.model.PasienModel;
import apap.ta.rumahsehat.service.PasienService;

@Controller
@RequestMapping("/pasien")
public class PasienController {

    @Autowired
    private PasienService pasienService;
    
    @GetMapping("/viewall")
    public String listPasien(Model model) {
        List<PasienModel> listPasien = pasienService.getListPasien();
        model.addAttribute("listPasien", listPasien);
        
        return "viewall-pasien";
    }
}
