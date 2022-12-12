package apap.ta.rumahsehat.controller;

import apap.ta.rumahsehat.model.AppointmentModel;
import apap.ta.rumahsehat.model.DokterModel;
// import apap.ta.rumahsehat.payload.AppointmentDTO;
import apap.ta.rumahsehat.service.AppointmentService;
import apap.ta.rumahsehat.service.DokterService;
import apap.ta.rumahsehat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
// import org.springframework.web.server.ResponseStatusException;

// import javax.validation.Valid;

// import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
// import java.util.NoSuchElementException;
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

    @GetMapping("/appointment")
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

        // AppointmentModel janji = appointmentService.getAppointmentByCode(kode);

        AppointmentModel janji = appointmentService.getAppointmentByCode(kode);
        // Handling view detail appointment
        if (janji.getResep() != null) {
            Long id = janji.getResep().getId();
            model.addAttribute("hasResep", true);
            model.addAttribute("id", id);
        }
        model.addAttribute("appointment", janji);
        return "view-detail-appointment";

    }

//    @GetMapping("/appointment/isDone/{kode}")
//    public String isDoneAppointment(@PathVariable(value="kode") String kode, Model model){
//
//        // AppointmentModel janji = appointmentService.getAppointmentByCode(kode);
//
//        AppointmentModel janji = appointmentService.getAppointmentByCode(kode);
//        janji.setIsDone(true);
//
//        model.addAttribute("appointment", janji);
//        return "isDone-appointment";
//
//    }

}
