package apap.ta.rumahsehat.controller;

import apap.ta.rumahsehat.model.AppointmentModel;
import apap.ta.rumahsehat.service.AppointmentService;
import apap.ta.rumahsehat.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ChartController {
    @Autowired
    AppointmentService appointmentService;

    @Autowired
    ChartService chartService;

    @RequestMapping("/chart")
    private String chart(Model model) {
        LocalDateTime dateTime=LocalDateTime.now();
        LocalDateTime startDate = LocalDateTime.of(dateTime.getYear(),1,1,0,0);
        LocalDateTime endDate = LocalDateTime.of(dateTime.getYear(),12,1,0,0).plusMonths(1).minusMinutes(1);

        List<AppointmentModel> appointmentModelList = appointmentService.getAppointmentAYear(startDate,endDate);

        model.addAttribute("pendapatan",chartService.getPendapatan(appointmentModelList));
        model.addAttribute("year",dateTime.getYear());

        return "chart";
    }
}
