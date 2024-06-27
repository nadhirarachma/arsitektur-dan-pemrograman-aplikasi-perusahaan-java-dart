package apap.ta.rumahsehat.service;

import apap.ta.rumahsehat.model.AppointmentModel;

import java.time.LocalDateTime;
import java.util.List;

public interface ChartService {
    int[] getTagihan(List<AppointmentModel> appointmentModelList, LocalDateTime start, LocalDateTime end);
}
