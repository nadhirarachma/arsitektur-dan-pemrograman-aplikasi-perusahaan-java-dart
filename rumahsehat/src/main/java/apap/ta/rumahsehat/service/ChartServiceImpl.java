package apap.ta.rumahsehat.service;

import apap.ta.rumahsehat.model.AppointmentModel;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
<<<<<<< HEAD
import java.time.LocalDateTime;
=======
>>>>>>> 05a4af9c14b4d64f593bc9c19533b7b0acc07967
import java.util.List;

@Service
public class ChartServiceImpl implements ChartService{

<<<<<<< HEAD

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
=======
    @Override
    public int[] getPendapatan(List<AppointmentModel> appointmentModelList) {
        int[] pendapatan = new int[12];

        for (AppointmentModel appointmentModel:appointmentModelList) {
            pendapatan[appointmentModel.getWaktuAwal().getMonthValue()-1]+=appointmentModel.getDokter().getTarif();
        }
        return pendapatan;
>>>>>>> 05a4af9c14b4d64f593bc9c19533b7b0acc07967
    }
}
