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
<<<<<<< HEAD
        LocalDateTime startDate = LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(),1,0,0);
        LocalDateTime endDate = startDate.plusMonths(1).minusMinutes(1);

        int day = startDate.plusMonths(1).minusMinutes(1).getDayOfMonth();
        int[] days = new int[endDate.getDayOfMonth()];
        for(int i=0;i<day;i++){
            days[i] = i+1;
        }

        List<AppointmentModel> appointmentModelList = appointmentService.getAppointmentAMonth(startDate,endDate);

        model.addAttribute("tagihan",chartService.getTagihan(appointmentModelList,startDate,endDate));
        model.addAttribute("month",dateTime.getMonth());
        model.addAttribute("labels",days);
=======
        LocalDateTime startDate = LocalDateTime.of(dateTime.getYear(),1,1,0,0);
        LocalDateTime endDate = LocalDateTime.of(dateTime.getYear(),12,1,0,0).plusMonths(1).minusMinutes(1);

        List<AppointmentModel> appointmentModelList = appointmentService.getAppointmentAYear(startDate,endDate);

        model.addAttribute("pendapatan",chartService.getPendapatan(appointmentModelList));
        model.addAttribute("year",dateTime.getYear());
>>>>>>> 05a4af9c14b4d64f593bc9c19533b7b0acc07967

        return "chart";
    }
}
