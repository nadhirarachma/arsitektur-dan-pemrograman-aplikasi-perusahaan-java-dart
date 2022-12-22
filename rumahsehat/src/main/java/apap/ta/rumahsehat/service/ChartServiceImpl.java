package apap.ta.rumahsehat.service;

import apap.ta.rumahsehat.model.AppointmentModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChartServiceImpl implements ChartService{


    @Override
    public int[] getTagihan(List<AppointmentModel> appointmentModelList, LocalDateTime start, LocalDateTime end) {

        var tagihan = new int[end.getDayOfMonth()];

        for (AppointmentModel appointmentModel:appointmentModelList) {
            tagihan[appointmentModel.getWaktuAwal().getDayOfMonth()-1]+=appointmentModel.getTagihan().getJumlahTagihan();
        }
        return tagihan;
    }
}
