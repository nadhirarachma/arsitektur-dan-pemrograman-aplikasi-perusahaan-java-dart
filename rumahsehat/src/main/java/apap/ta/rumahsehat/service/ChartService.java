package apap.ta.rumahsehat.service;

import apap.ta.rumahsehat.model.AppointmentModel;

import java.util.List;

public interface ChartService {
    int[] getPendapatan(List<AppointmentModel> appointmentModelList);
}
