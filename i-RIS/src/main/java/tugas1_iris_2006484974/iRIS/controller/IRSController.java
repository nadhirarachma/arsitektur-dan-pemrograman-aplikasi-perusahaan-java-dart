package tugas1_iris_2006484974.iRIS.controller;

import tugas1_iris_2006484974.iRIS.model.MahasiswaModel;
import tugas1_iris_2006484974.iRIS.model.IRSModel;
import tugas1_iris_2006484974.iRIS.model.MataKuliahModel;
import tugas1_iris_2006484974.iRIS.model.DosenMataKuliahModel;
import tugas1_iris_2006484974.iRIS.service.MahasiswaService;
import tugas1_iris_2006484974.iRIS.service.IRSService;
import tugas1_iris_2006484974.iRIS.service.MataKuliahService;
import tugas1_iris_2006484974.iRIS.service.DosenMataKuliahService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@Controller
public class IRSController {
    @Qualifier("mahasiswaServiceImpl")
    @Autowired
    private MahasiswaService mahasiswaService;

    @Qualifier("IRSServiceImpl")
    @Autowired
    private IRSService irsService;

    @Qualifier("mataKuliahServiceImpl")
    @Autowired
    private MataKuliahService mataKuliahService;

    @Qualifier("dosenMataKuliahServiceImpl")
    @Autowired
    private DosenMataKuliahService dosenMataKuliahService;

    @GetMapping("/mahasiswa/{npm}/{idIRS}")
    public String viewDetailIRS(@PathVariable String npm, @PathVariable String idIRS, Model model) {
        MahasiswaModel mahasiswa = mahasiswaService.getMahasiswaByNpm(npm);
        Long idIrs = Long.valueOf(idIRS);
        IRSModel irs = irsService.getIRSById(idIrs);
        List<MataKuliahModel> listMataKuliah = irs.getListMataKuliah();
       
        for (int i = 0; i < listMataKuliah.size(); i++) {
            for (int j = i+1; j < listMataKuliah.size(); j++) {

                if (listMataKuliah.get(i).getNamaMatkul().compareTo(listMataKuliah.get(j).getNamaMatkul()) > 0) {
                    MataKuliahModel temp = listMataKuliah.get(i);
                    listMataKuliah.set(i, listMataKuliah.get(j));
                    listMataKuliah.set(j, temp);
                }

                mataKuliahService.countTotalMahasiswa(listMataKuliah.get(i));
                mataKuliahService.countTotalMahasiswa(listMataKuliah.get(j));
            }
        }

        List<DosenMataKuliahModel> dosenMataKuliah = dosenMataKuliahService.getListDosenMataKuliah();
        List<List<DosenMataKuliahModel>> listRuangan = new ArrayList<>();
        
        for (int i = 0; i < listMataKuliah.size(); i++) {
            List<DosenMataKuliahModel> temp = new ArrayList<>();

            for (DosenMataKuliahModel dosenMatkul : dosenMataKuliah) {
                if (dosenMatkul.getMataKuliah().getIdMataKuliah().equals(listMataKuliah.get(i).getIdMataKuliah())) {
                    temp.add(dosenMatkul);
                }
            }
            listRuangan.add(temp);
        }
       
        model.addAttribute("mahasiswa", mahasiswa);
        model.addAttribute("irs", irs);
        model.addAttribute("listMataKuliah", listMataKuliah);
        model.addAttribute("listRuangan", listRuangan);
        model.addAttribute("npm", npm);
        
        return "view-irs";
    }

    @GetMapping("/mahasiswa/{npm}/create-irs")
    public String addIRSFormPage(@PathVariable String npm, Model model) {
        IRSModel irs = new IRSModel();
        List<MataKuliahModel> listMataKuliah = mataKuliahService.getListMataKuliah();
        
        model.addAttribute("irs", irs);
        model.addAttribute("listMataKuliah", listMataKuliah);
        model.addAttribute("npm", npm);

        return "form-add-irs";
    }

    @PostMapping("/mahasiswa/{npm}/create-irs")
    public String addIRSSubmitPage(@ModelAttribute IRSModel irs, @PathVariable String npm, Model model) {
        MahasiswaModel mahasiswa = mahasiswaService.getMahasiswaByNpm(npm);
        String namaMhs = mahasiswa.getNamaDepan() + " " + mahasiswa.getNamaBelakang();
        
        String successCreate = irsService.addIRS(irs, mahasiswa);

        if (successCreate.equals("exist")) {
            model.addAttribute("semester", irsService.getSemesterName(irs));
        }
        else if (successCreate.equals("wrong")) {
            String semester = "Ganjil";
            if (irsService.getSemesterName(irs).substring(0,3).equals("GAN")) {
                semester = "Genap";
            }
            
            model.addAttribute("semesterIrs", irsService.getSemesterName(irs));
            model.addAttribute("namaMatkul", irsService.checkSemesterMatkul(irs));
            model.addAttribute("semesterMatkul", semester);
        }
        else if (successCreate.equals("success")) {
            model.addAttribute("semester", irs.getSemester());
            model.addAttribute("namaMahasiswa", namaMhs);
        }
        
        model.addAttribute("status", successCreate);
        model.addAttribute("npm", npm);
        return "add-irs";
    }

    @GetMapping("/mahasiswa/{npm}/{idIRS}/update")
    public String updateIRSFormPage(@PathVariable String npm, @PathVariable String idIRS, Model model) {
        Long idIrs = Long.valueOf(idIRS);
        IRSModel irs = irsService.getIRSById(idIrs);
        List<MataKuliahModel> listMataKuliah = mataKuliahService.getListMataKuliah();
        
        model.addAttribute("irs", irs);
        model.addAttribute("listMataKuliah", listMataKuliah);
        model.addAttribute("npm", npm);

        return "form-update-irs";
    }

    @PostMapping("/mahasiswa/{npm}/{idIRS}/update")
    public String updateIRSSubmitPage(@ModelAttribute IRSModel irs, @PathVariable String npm, @PathVariable String idIRS, Model model) {
        String status = "success";

        if (irsService.countJumlahSks(irs) == true){
            status = "24";
        }
        else if (status.equals("success")) {
            IRSModel updatedIRS = irsService.updateIRS(irs);
            model.addAttribute("semester", updatedIRS.getSemester());
        }

        model.addAttribute("status", status);
        model.addAttribute("npm", npm);
        return "update-irs";
    }
}
