package tugas1_iris_2006484974.iRIS.controller;

import tugas1_iris_2006484974.iRIS.model.MahasiswaModel;
import tugas1_iris_2006484974.iRIS.model.IRSModel;
import tugas1_iris_2006484974.iRIS.model.ListMahasiswaModel;
import tugas1_iris_2006484974.iRIS.service.MahasiswaService;
import tugas1_iris_2006484974.iRIS.service.IRSService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@Controller
public class MahasiswaController {
    @Qualifier("mahasiswaServiceImpl")
    @Autowired
    private MahasiswaService mahasiswaService;

    @Qualifier("IRSServiceImpl")
    @Autowired
    private IRSService IRSService;

    @GetMapping("/mahasiswa")
    public String listMahasiswa(Model model) {
        List<MahasiswaModel> listMahasiswa = mahasiswaService.getListMahasiswa();
        model.addAttribute("listMahasiswa", listMahasiswa);
        
        return "viewall-mahasiswa";
    }

    @GetMapping("/mahasiswa/{npm}")
    public String viewDetailMahasiswa(@PathVariable String npm, Model model) {
        MahasiswaModel mahasiswa = mahasiswaService.getMahasiswaByNpm(npm);
        List<IRSModel> listIRS = mahasiswa.getListIRS();

        model.addAttribute("mahasiswa", mahasiswa);
        model.addAttribute("listIRS", listIRS);
        
        return "view-mahasiswa";
    }

    @GetMapping("/mahasiswa/create-mahasiswa")
    public String addMahasiswaFormPage(Model model) {
        MahasiswaModel mahasiswa = new MahasiswaModel();
        model.addAttribute("mahasiswa", mahasiswa);

        return "form-add-mahasiswa";
    }

    @PostMapping("/mahasiswa/create-mahasiswa")
    public String addMahasiswaSubmitPage(@ModelAttribute MahasiswaModel mahasiswa, Model model) {
        String successCreate = mahasiswaService.addMahasiswa(mahasiswa);
        
        if (successCreate.equals("npmExist")) {
            model.addAttribute("npm", mahasiswa.getNpm());
        }
        else if (successCreate.equals("emailExist")) {
            model.addAttribute("email", mahasiswa.getEmail());
        }
        else if (successCreate.equals("success")) {
            model.addAttribute("npm", mahasiswa.getNpm());
        }
        
        model.addAttribute("status", successCreate);
        
        return "add-mahasiswa";
    }

    @GetMapping("/mahasiswa/{npm}/update")
    public String updateMahasiswaFormPage(@PathVariable String npm, Model model) {
        MahasiswaModel mahasiswa = mahasiswaService.getMahasiswaByNpm(npm);
        model.addAttribute("mahasiswa", mahasiswa);

        return "form-update-mahasiswa";
    }

    @PostMapping("/mahasiswa/update")
    public String updateMahasiswaSubmitPage(@ModelAttribute MahasiswaModel mahasiswa, Model model) {
        MahasiswaModel updatedMahasiswa = mahasiswaService.updateMahasiswa(mahasiswa);
        model.addAttribute("npm", updatedMahasiswa.getNpm());

        return "update-mahasiswa";
    }

    @GetMapping("/mahasiswa/delete")
    public String deleteMahasiswaFormPage(Model model) {
        List<MahasiswaModel> listMahasiswa = mahasiswaService.getListMahasiswaNonAktif();
        model.addAttribute("listMahasiswa", listMahasiswa);

        ListMahasiswaModel mahasiswa = new ListMahasiswaModel();
        model.addAttribute("mahasiswa", mahasiswa);

        return "form-delete-mahasiswa";
    }

    @PostMapping("/mahasiswa/delete")
    public String deleteMahasiswaSubmitPage(@ModelAttribute ListMahasiswaModel mahasiswa, Model model) {
        List<String> listNpm = new ArrayList<>();
        for (MahasiswaModel mhs : mahasiswa.getListMahasiswa()) {
            listNpm.add(mhs.getNpm());
            mahasiswaService.deleteMahasiswa(mhs);
        }
        model.addAttribute("listNpm", listNpm);
        
        return "delete-mahasiswa";
    }
}
