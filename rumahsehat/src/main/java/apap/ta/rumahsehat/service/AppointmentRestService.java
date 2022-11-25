package apap.ta.rumahsehat.service;

import apap.ta.rumahsehat.model.AppointmentModel;
import apap.ta.rumahsehat.payload.AppointmentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface AppointmentRestService {

    List<?> getAllDokter();

    ResponseEntity<?> postAppointment(AppointmentDTO appointmentDTO, Authentication authentication);
}
