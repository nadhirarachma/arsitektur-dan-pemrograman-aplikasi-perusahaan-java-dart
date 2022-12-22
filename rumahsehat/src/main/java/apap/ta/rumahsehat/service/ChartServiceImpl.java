package apap.ta.rumahsehat.service;

import apap.ta.rumahsehat.model.AppointmentModel;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChartServiceImpl implements ChartService{


    @Override
    public int[] getTagihan(List<AppointmentModel> appointmentModelList, LocalDateTime start, LocalDateTime end) {
        int day = start.plusMonths(1).minusMinutes(1).getDayOfMonth();
        int[] tagihan = new int[end.getDayOfMonth()];
//        for(int i=0;i<day;i++){
//            tagihan[i] = i+1;
//        }

        for (AppointmentModel appointmentModel:appointmentModelList) {
            tagihan[appointmentModel.getWaktuAwal().getDayOfMonth()-1]+=appointmentModel.getTagihan().getJumlahTagihan();
        }
        return tagihan;
    }
}
