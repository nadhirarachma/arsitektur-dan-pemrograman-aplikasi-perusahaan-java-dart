package apap.ta.rumahsehat.controller;

import apap.ta.rumahsehat.model.AppointmentModel;
import apap.ta.rumahsehat.service.AppointmentService;
import apap.ta.rumahsehat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AppointmentController {
    @Qualifier("appointmentServiceImpl")
    @Autowired
    private AppointmentService appointmentService;

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
}
