package apap.ta.rumahsehat.controller;

import apap.ta.rumahsehat.model.AppointmentModel;
import apap.ta.rumahsehat.model.DokterModel;
import apap.ta.rumahsehat.payload.AppointmentDTO;
import apap.ta.rumahsehat.service.AppointmentService;
import apap.ta.rumahsehat.service.DokterService;
import apap.ta.rumahsehat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
@Controller
//@RestController
public class AppointmentController {
    @Qualifier("appointmentServiceImpl")
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DokterService dokterService;

    @Autowired
    private UserService userService;

    @GetMapping("/appointment/add")
    public String addAppointmentFormPage(Model model){
        AppointmentModel appointment = new AppointmentModel();
        List<DokterModel> listDokter = dokterService.getListDokter();

        model.addAttribute("appointment", appointment);
        model.addAttribute("listDokterExisting", listDokter);
        return "form-add-appointment";
    }

    @PostMapping(value="/appointment/add", params = {"save"})
    public String addAppointmentSubmitPage(@ModelAttribute AppointmentModel appointment, Model model, Authentication authentication){
        appointment.setIsDone(false);
//        appointment.setWaktuAwal(LocalDateTime.now());

//        appointment.setKode(appointmentService.generateCode(appointment));

        appointmentService.addAppointment(appointment);
        model.addAttribute("kode", appointment.getKode());

        return "berhasil-add-appointment";
    }

    @GetMapping("/appointment/all")
    public String listAppointment(Model model, Authentication authentication){
        List<AppointmentModel> listAppointment = new ArrayList<>();

        if(userService.getUserByUsername(authentication.getName()).getRole().equals("Dokter")){
            listAppointment = appointmentService.getListAppointmentByDokter(authentication.getName());
        }else {
            listAppointment = appointmentService.getListAppointment();
        }
        model.addAttribute("listAppointment", listAppointment);
        return "viewall-appointment";
    }

    @GetMapping("/appointment/detail/{kode}")
    public String viewDetailAppointment(@PathVariable(value="kode") String kode, Model model){

        AppointmentModel janji = appointmentService.getAppointmentByCode(kode);
        model.addAttribute("appointment", janji);
        return "view-detail-appointment";

    }

//    @GetMapping("/get-dokter")
//    public List<?> getAllDokter(){
//        if(dokterService.getListDokter().size()==0){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tidak Ada Dokter Tersedia");
//        }else {
//            return dokterService.getListDokter();
//        }
//    }
//
//    @PostMapping("/post-appointment")
//    public ResponseEntity<?> postAppointment(@Valid @RequestBody
//                                             AppointmentDTO appointmentDTO, Authentication authentication){
//        try{
//            return appointmentService.createAppointment(appointmentDTO, authentication);
//        }catch (NoSuchElementException e){
//            throw new ResponseStatusException(
//                    HttpStatus.BAD_REQUEST,"Appoinment Tidak Berhasil Dibuat"
//            );
//        }
//        //return null;
//    }
}
