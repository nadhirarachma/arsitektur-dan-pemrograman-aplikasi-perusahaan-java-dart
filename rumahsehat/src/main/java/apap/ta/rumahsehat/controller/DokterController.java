package apap.ta.rumahsehat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import apap.ta.rumahsehat.model.DokterModel;
import apap.ta.rumahsehat.service.DokterService;

@Controller
@RequestMapping("/dokter")
public class DokterController {

    @Autowired
    private DokterService dokterService;

    @GetMapping(value = "/add")
    private String addDokterFormPage(Model model) {
        DokterModel dokter = new DokterModel();
        model.addAttribute("dokter", dokter);
        return "form-add-dokter";
    }

    @PostMapping(value = "/add") 
    private String addDokterSubmit(@ModelAttribute DokterModel dokter, Model model) {
    
        dokter.setIsSso(false);

        DokterModel newDokter = dokterService.addDokter(dokter);

        if (newDokter.equals(dokter)) {
            model.addAttribute("dokter", dokter);
            return "redirect:/";
        }
        else {
            model.addAttribute("validasi", "Password harus mengandung angka, huruf besar, huruf kecil, dan simbol, serta minimal memiliki 8 karakter. Mohon input kembali.");
            return "form-add-dokter";
        }
    }
    
    @GetMapping("/viewall")
    public String listDokter(Model model) {
        List<DokterModel> listDokter = dokterService.getListDokter();
        model.addAttribute("listDokter", listDokter);
        
        return "viewall-dokter";
    }
}
