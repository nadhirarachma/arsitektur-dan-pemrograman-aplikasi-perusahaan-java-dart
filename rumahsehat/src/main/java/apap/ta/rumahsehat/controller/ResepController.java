package apap.ta.rumahsehat.controller;

import apap.ta.rumahsehat.model.ResepModel;
import apap.ta.rumahsehat.model.TagihanModel;
import apap.ta.rumahsehat.model.ObatModel;
import apap.ta.rumahsehat.model.JumlahModel;
import apap.ta.rumahsehat.service.ResepService;
import apap.ta.rumahsehat.service.ObatService;
import apap.ta.rumahsehat.service.TagihanService;
import apap.ta.rumahsehat.model.ApotekerModel;
import apap.ta.rumahsehat.model.DokterModel;
import apap.ta.rumahsehat.model.AppointmentModel;
import apap.ta.rumahsehat.model.UserModel;
import apap.ta.rumahsehat.service.AppointmentService;
import apap.ta.rumahsehat.service.ApotekerService;
import apap.ta.rumahsehat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Controller
public class ResepController {
    @Qualifier("resepServiceImpl")
    @Autowired
    private ResepService resepService;

    @Qualifier("obatServiceImpl")
    @Autowired
    private ObatService obatService;

    @Qualifier("appointmentServiceImpl")
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserService userService;


    @Autowired
    private ApotekerService apotekerService;

    @Autowired
    private TagihanService tagihanService;


    private AppointmentModel appointment;

    @GetMapping("/resep")
    public String listResep(Model model){
        List<ResepModel> listResep = resepService.getListResep();
        model.addAttribute("listResep", listResep);
        return "viewall-resep";
    }

    @GetMapping("/resep/add/{kode}")
    public String addResepFormPage(@PathVariable String kode, Model model) {
        appointment = appointmentService.getAppointmentByCode(kode);
        ResepModel resep = new ResepModel();
        
        List<ObatModel> listObat = obatService.getSortedListObat();
        System.out.println(listObat.get(0).getNamaObat());
        List<JumlahModel> listJumlahNew = new ArrayList<>();

        resep.setJumlah(listJumlahNew);
        resep.getJumlah().add(new JumlahModel());

        model.addAttribute("resep", resep);
        model.addAttribute("listObatExisting", listObat);
        model.addAttribute("appointment", appointment);
        model.addAttribute("kode", kode);
        return "form-add-resep";
    }

    @PostMapping(value="/resep/add/{kode}", params = {"save"})
    public String addResepSubmitPage(@PathVariable String kode, @ModelAttribute ResepModel resep, @ModelAttribute JumlahModel jumlah, Model model) {
        appointment = appointmentService.getAppointmentByCode(kode);
        System.out.println(appointment.getKode());
        if (resep.getJumlah() == null) {
            resep.setJumlah(new ArrayList<>());
        }
        for (JumlahModel jml : resep.getJumlah()) {
            jml.setResep(resep);
        }
        
        resep.setIsDone(false);
        appointment.setResep(resep);
        resep.setAppointment(appointment);
        resepService.addResep(resep);

        model.addAttribute("id", resep.getId());
        model.addAttribute("kode", kode);
        return "add-resep";
    }

    @PostMapping(value="/resep/add/{kode}", params = {"addRow"})
    private String addRowResepMultiple(@PathVariable String kode,
            @ModelAttribute ResepModel resep,
            Model model
    ){
        appointment = appointmentService.getAppointmentByCode(kode);
        if (resep.getJumlah() == null || resep.getJumlah().size()==0){
            resep.setJumlah(new ArrayList<>());
        }

        resep.getJumlah().add(new JumlahModel());
        List<ObatModel> listObat = obatService.getSortedListObat();

        model.addAttribute("resep", resep);
        model.addAttribute("listObatExisting", listObat);
        model.addAttribute("kode", kode);
        return "form-add-resep";
    }

    @PostMapping(value="/resep/add/{kode}", params = {"deleteRow"})
    private String deleteRowResepMultiple(@PathVariable String kode,
            @ModelAttribute ResepModel resep,
            @RequestParam("deleteRow") Integer row,
            Model model
    ){
        appointment = appointmentService.getAppointmentByCode(kode);
        final Integer rowId = Integer.valueOf(row);
        resep.getJumlah().remove(rowId.intValue());

        List<ObatModel> listObat = obatService.getSortedListObat();

        model.addAttribute("resep", resep);
        model.addAttribute("listObatExisting", listObat);
        model.addAttribute("kode", kode);
        return "form-add-resep";
    }

    @GetMapping("/resep/view/{id}")
    public String viewDetailResep(@PathVariable Long id, Model model) {
        ResepModel resep = resepService.getResepById(id);

        String namaApoteker= "";
        if (resep.getApotek() == null) {
            namaApoteker = "Belum ada";
        } else  {namaApoteker = resep.getApotek().getNama();}

        String namaDokter= resep.getAppointment().getDokter().getNama();
        String namaPasien= resep.getAppointment().getPasien().getNama();
        System.out.println(namaDokter);

        model.addAttribute("resep", resep);
        model.addAttribute("namaApoteker", namaApoteker);
        model.addAttribute("namaDokter", namaDokter);
        model.addAttribute("namaPasien", namaPasien);

        return "view-detail-resep";
    }

    @GetMapping("/resep/konfirmasi/{id}")
    public String konfirmasiResep(@PathVariable Long id, Model model) {
        
        ResepModel resep = resepService.getResepById(id);
        if (!cekStokObat(resep.getJumlah())) {
            return "error-konfirmasi-resep";
        }

        TagihanModel tagihan = new TagihanModel();
        int banyakTagihan = kalkulasiTagihan(resep.getJumlah(), resep.getAppointment().getDokter());
        tagihan.setTanggalTerbuat(LocalDateTime.now());
        tagihan.setIsPaid(false);
        tagihan.setJumlahTagihan(banyakTagihan);
        tagihan.setAppointment(resep.getAppointment());
        tagihanService.addTagihan(tagihan);

        UserModel user = userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        ApotekerModel apoteker = apotekerService.getApotekerById(user.getUuid());
        resep.setApotek(apoteker);
        resep.setIsDone(true);
        resep.getAppointment().setIsDone(true);
        resep.getAppointment().setTagihan(tagihan);
        resepService.addResep(resep);

        model.addAttribute("resep", resep);
        model.addAttribute("apoteker", apoteker);

        return "konfirmasi-resep";
    }

    private Integer kalkulasiTagihan(List<JumlahModel> listObat, DokterModel dokter) {
        int tarifDokter = dokter.getTarif();
        int hargaObat = 0;

        for (JumlahModel resep: listObat) {
            hargaObat += (resep.getKuantitas() * resep.getObat().getHarga());}

        return tarifDokter + hargaObat;
    }

    private Boolean cekStokObat(List<JumlahModel> listObat) {
        for (JumlahModel resep : listObat) {
            ObatModel obat = obatService.getObatbyId(resep.getObat().getIdObat());
            if (resep.getKuantitas() > obat.getStok()) {
                return false;
            }
        }

        for (JumlahModel resep : listObat) {
            ObatModel obat = obatService.getObatbyId(resep.getObat().getIdObat());
            int jumlahObat = obat.getStok() - resep.getKuantitas();
            obat.setStok(jumlahObat);
            obatService.updateStokObat(obat);
        }
        return true;
    }

}
