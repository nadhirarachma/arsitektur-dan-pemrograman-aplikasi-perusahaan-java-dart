package apap.ta.rumahsehat.service;

import apap.ta.rumahsehat.model.AppointmentModel;
import apap.ta.rumahsehat.payload.AppointmentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface AppointmentService {
    void addAppointment(AppointmentModel appointment);
    String generateCode();
    List<AppointmentModel> getListAppointment();
    AppointmentModel getAppointmentByCode(String kode);

    List<AppointmentModel> getListAppointmentByDokter(String dokter);


    ResponseEntity<?> createAppointment(AppointmentDTO appointmentDTO, Authentication authentication);

}
