package apap.ta.rumahsehat.controller;

import apap.ta.rumahsehat.model.AppointmentModel;
import apap.ta.rumahsehat.service.AppointmentService;
import apap.ta.rumahsehat.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ChartController {
    @Autowired
    AppointmentService appointmentService;

    @Autowired
    ChartService chartService;

    @GetMapping("/chart")
    public String chart(Model model) {
        var dateTime=LocalDateTime.now();
        var startDate = LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(),1,0,0);
        LocalDateTime endDate = startDate.plusMonths(1).minusMinutes(1);

        int day = startDate.plusMonths(1).minusMinutes(1).getDayOfMonth();
        var days = new int[endDate.getDayOfMonth()];
        for(var i=0;i<day;i++){
            days[i] = i+1;
        }

        List<AppointmentModel> appointmentModelList = appointmentService.getAppointmentAMonth(startDate,endDate);

        model.addAttribute("tagihan",chartService.getTagihan(appointmentModelList,startDate,endDate));
        model.addAttribute("month",dateTime.getMonth());
        model.addAttribute("labels",days);

        return "chart";
    }
}
