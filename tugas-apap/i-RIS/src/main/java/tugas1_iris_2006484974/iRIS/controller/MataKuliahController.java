package tugas1_iris_2006484974.iRIS.controller;

import tugas1_iris_2006484974.iRIS.model.MataKuliahModel;
import tugas1_iris_2006484974.iRIS.model.DosenMataKuliahModel;
import tugas1_iris_2006484974.iRIS.model.DosenModel;
import tugas1_iris_2006484974.iRIS.service.MataKuliahService;
import tugas1_iris_2006484974.iRIS.service.IRSService;
import tugas1_iris_2006484974.iRIS.service.DosenService;
import tugas1_iris_2006484974.iRIS.service.DosenMataKuliahService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@Controller
public class MataKuliahController {
    @Qualifier("mataKuliahServiceImpl")
    @Autowired
    private MataKuliahService mataKuliahService;

    @Qualifier("IRSServiceImpl")
    @Autowired
    private IRSService IRSService;

    @Qualifier("dosenServiceImpl")
    @Autowired
    private DosenService dosenService;

    @Qualifier("dosenMataKuliahServiceImpl")
    @Autowired
    private DosenMataKuliahService dosenMataKuliahService;

    @GetMapping("/mata-kuliah")
    public String listMataKuliah(Model model) {
        List<MataKuliahModel> listMataKuliah = mataKuliahService.getListMataKuliah();
        model.addAttribute("listMataKuliah", listMataKuliah);
       
        return "viewall-mata-kuliah";
    }

    @GetMapping("/mata-kuliah/{idMataKuliah}")
    public String viewDetailMataKuliah(@PathVariable String idMataKuliah, Model model) {
        Long id = Long.valueOf(idMataKuliah);
        MataKuliahModel mataKuliah = mataKuliahService.getMataKuliahById(id);
    
        model.addAttribute("mataKuliah", mataKuliah);
        
        return "view-mata-kuliah";
    }

    @GetMapping("/mata-kuliah/create-matakuliah")
    public String addMataKuliahFormPage(Model model) {
        MataKuliahModel mataKuliah = new MataKuliahModel();

        List<DosenModel> listPengajar = dosenService.getListDosen();
        List<DosenMataKuliahModel> dosenMataKuliah = new ArrayList<>();

        mataKuliah.setDosenMataKuliah(dosenMataKuliah);
        mataKuliah.getDosenMataKuliah().add(new DosenMataKuliahModel());

        model.addAttribute("mataKuliah", mataKuliah);
        model.addAttribute("listPengajarExisting", listPengajar);

        return "form-add-mata-kuliah";
    }

    @PostMapping(value = "/mata-kuliah/create-matakuliah", params = {"save"})
    public String addMataKuliahSubmitPage(@ModelAttribute MataKuliahModel mataKuliah, Model model) {
        String code = mataKuliahService.generateCode(mataKuliah, mataKuliah.getNamaMatkul(), mataKuliah.getSemester(), mataKuliah.getWaktuMulai(), mataKuliah.getSks());
        mataKuliah.setCode(code);
        mataKuliahService.addMataKuliah(mataKuliah);

        List<DosenMataKuliahModel> listPengajar = mataKuliah.getDosenMataKuliah();
        if (mataKuliah.getDosenMataKuliah() == null) {
            mataKuliah.setDosenMataKuliah(new ArrayList<>());
        }

        for (DosenMataKuliahModel pengajar : listPengajar) {
            pengajar.setMataKuliah(mataKuliah);
            dosenMataKuliahService.addDosenMataKuliah(pengajar);
        }

        mataKuliah.setDosenMataKuliah(listPengajar);

        model.addAttribute("code", code);
        
        return "add-mata-kuliah";
    }

    @GetMapping("/mata-kuliah/{idMataKuliah}/delete")
    public String deleteMataKuliah(@PathVariable String idMataKuliah, Model model) {
        Long id = Long.valueOf(idMataKuliah);
        MataKuliahModel mataKuliah = mataKuliahService.getMataKuliahById(id);

        String success = "success";
        if (mataKuliah.getTotalMahasiswa() != 0) {
            success = "existInIrs";
        }
        
        if (success.equals("success")) {
            mataKuliahService.deleteMataKuliah(mataKuliah);
        }

        model.addAttribute("status", success);
        model.addAttribute("code", mataKuliah.getCode());

        return "delete-mata-kuliah";
    }

    @GetMapping("filter")
    public String filterMataKuliahFormPage(Model model) {
        MataKuliahModel mataKuliah = new MataKuliahModel();
        List<DosenModel> dosenMataKuliah = dosenService.getListDosen();

        model.addAttribute("mataKuliah", mataKuliah);
        model.addAttribute("dosenMataKuliah", dosenMataKuliah);

        return "form-filter-mata-kuliah";
    }

    @GetMapping("filter/mata-kuliah")
    public String filterMataKuliahSubmitPage(@RequestParam(value = "nip") String nip, @RequestParam(value = "semester") String semester, Model model) {
        String smt = "0";
        if (semester.toLowerCase().startsWith("ga")) {
            smt = "Ganjil";
        }
        else if (semester.toLowerCase().startsWith("ge")) {
            smt = "Genap";
        }

        List<MataKuliahModel> mataKuliah = mataKuliahService.getMataKuliahBySemester(smt);
        DosenModel dosen = dosenService.getDosenByNip(nip);

        List<DosenMataKuliahModel> listMataKuliah = dosenMataKuliahService.getListMataKuliah(dosen, smt);
        List<DosenModel> dosenMataKuliah = dosenService.getListDosen();

        model.addAttribute("dosenMataKuliah", dosenMataKuliah);
        model.addAttribute("mataKuliah", mataKuliah);
        model.addAttribute("listMataKuliah", listMataKuliah);
        model.addAttribute("semester", semester);
        
        return "filter-mata-kuliah";
    }

    @PostMapping(value = "/mata-kuliah/create-matakuliah", params = {"addRowPengajar"})
    private String addRowPengajarMultiple(@ModelAttribute MataKuliahModel mataKuliah, Model model) {
        if (mataKuliah.getDosenMataKuliah() == null || mataKuliah.getDosenMataKuliah().size() == 0) {
            mataKuliah.setDosenMataKuliah(new ArrayList<>());
        }
        mataKuliah.getDosenMataKuliah().add(new DosenMataKuliahModel());
        List<DosenModel> dosenMatkul = dosenService.getListDosen();

        model.addAttribute("mataKuliah", mataKuliah);
        model.addAttribute("listPengajarExisting", dosenMatkul);
    
        return "form-add-mata-kuliah";
    }

    @PostMapping(value = "/mata-kuliah/create-matakuliah", params = {"deleteRowPengajar"})
    private String deleteRowPengajarMultiple(@ModelAttribute MataKuliahModel mataKuliah, @RequestParam("deleteRowPengajar") Integer row, Model model) {
        final Integer rowId = Integer.valueOf(row);
        mataKuliah.getDosenMataKuliah().remove(rowId.intValue());

        List<DosenModel> dosenMatkul = dosenService.getListDosen();

        model.addAttribute("mataKuliah", mataKuliah);
        model.addAttribute("listPengajarExisting", dosenMatkul);

        return "form-add-mata-kuliah";
    }
}
