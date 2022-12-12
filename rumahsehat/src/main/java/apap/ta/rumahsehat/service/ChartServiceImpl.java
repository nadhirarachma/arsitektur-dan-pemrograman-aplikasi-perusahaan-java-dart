package apap.ta.rumahsehat.service;

import apap.ta.rumahsehat.model.AppointmentModel;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ChartServiceImpl implements ChartService{

    @Override
    public int[] getPendapatan(List<AppointmentModel> appointmentModelList) {
        int[] pendapatan = new int[12];

        for (AppointmentModel appointmentModel:appointmentModelList) {
            pendapatan[appointmentModel.getWaktuAwal().getMonthValue()-1]+=appointmentModel.getDokter().getTarif();
        }
        return pendapatan;
    }
}
