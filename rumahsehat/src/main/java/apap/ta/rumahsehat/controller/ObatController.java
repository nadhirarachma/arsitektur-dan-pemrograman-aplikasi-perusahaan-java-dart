package apap.ta.rumahsehat.controller;

import apap.ta.rumahsehat.model.ObatModel;
import apap.ta.rumahsehat.repository.ObatDb;
import apap.ta.rumahsehat.service.ObatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
