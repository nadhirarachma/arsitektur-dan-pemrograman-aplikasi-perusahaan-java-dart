package apap.ta.rumahsehat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import apap.ta.rumahsehat.model.ApotekerModel;
import apap.ta.rumahsehat.service.ApotekerService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/apoteker")
public class ApotekerController {

    @Autowired
    private ApotekerService apotekerService;

    @GetMapping(value = "/add")
    private String addApotekerFormPage(Model model) {
        ApotekerModel apoteker = new ApotekerModel();
        model.addAttribute("apoteker", apoteker);
        return "form-add-apoteker";
    }

    @PostMapping(value = "/add") 
    private String addApotekerSubmit(@ModelAttribute ApotekerModel apoteker, Model model) {
       
        ApotekerModel newApoteker = apotekerService.addApoteker(apoteker);

        if (newApoteker.equals(apoteker)) {
            model.addAttribute("username", apoteker.getUsername());
            return "add-apoteker";
        }
        else if (newApoteker.getUsername().equals("exist")) {
            log.warn("Failed to create apoteker. User with same username or email already exist.");
            model.addAttribute("validasi", "User dengan username atau email yang sama telah terdapat pada sistem. Mohon input kembali.");
            return "form-add-apoteker";
        }
        else {
            log.warn("Failed to Create Apoteker. Wrong Password Format");
            model.addAttribute("validasi", "Password harus mengandung angka, huruf besar, huruf kecil, dan simbol, serta minimal memiliki 8 karakter. Mohon input kembali.");
            return "form-add-apoteker";
        }
    }
    
    @GetMapping("/viewall")
    public String listApoteker(Model model) {
        List<ApotekerModel> listApoteker = apotekerService.getListApoteker();
        model.addAttribute("listApoteker", listApoteker);
        
        return "viewall-apoteker";
    }
}