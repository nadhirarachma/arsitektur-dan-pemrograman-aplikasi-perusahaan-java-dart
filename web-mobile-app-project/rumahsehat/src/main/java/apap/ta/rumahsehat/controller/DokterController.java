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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/dokter")
public class DokterController {

    @Autowired
    private DokterService dokterService;

    String formAddDokter = "form-add-dokter";

    @GetMapping(value = "/add")
    public String addDokterFormPage(Model model) {
        var dokter = new DokterModel();
        model.addAttribute("dokter", dokter);
        return formAddDokter;
    }

    @PostMapping(value = "/add") 
    public String addDokterSubmit(@ModelAttribute DokterModel dokter, Model model) {
    
        DokterModel newDokter = dokterService.addDokter(dokter);

        if (newDokter.equals(dokter)) {
            model.addAttribute("username", dokter.getUsername());
            return "add-dokter";
        }
        else if (newDokter.getUsername().equals("exist")) {
            log.warn("Failed to create dokter. User with same username or email already exist.");
            model.addAttribute("validasi", "User dengan username atau email yang sama telah terdapat pada sistem. Mohon input kembali.");
            return formAddDokter;
        }
        else {
            log.warn("Failed to Create Dokter. Wrong Password Format");
            model.addAttribute("validasi", "Password harus mengandung angka, huruf besar, huruf kecil, dan simbol, serta minimal memiliki 8 karakter. Mohon input kembali.");
            return formAddDokter;
        }
    }
    
    @GetMapping("/viewall")
    public String listDokter(Model model) {
        List<DokterModel> listDokter = dokterService.getListDokter();
        model.addAttribute("listDokter", listDokter);
        
        return "viewall-dokter";
    }
}