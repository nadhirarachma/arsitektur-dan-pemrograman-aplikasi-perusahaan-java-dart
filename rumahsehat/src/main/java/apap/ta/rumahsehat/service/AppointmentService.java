package apap.ta.rumahsehat.service;

import apap.ta.rumahsehat.model.AppointmentModel;
<<<<<<< rumahsehat/src/main/java/apap/ta/rumahsehat/service/AppointmentService.java
=======
import apap.ta.rumahsehat.model.PasienModel;
>>>>>>> rumahsehat/src/main/java/apap/ta/rumahsehat/service/AppointmentService.java
import apap.ta.rumahsehat.payload.AppointmentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface AppointmentService {
    void addAppointment(AppointmentModel appointment);
    String generateCode();
    List<AppointmentModel> getListAppointment();
    AppointmentModel getAppointmentByCode(String kode);
    // AppointmentModel addAppointment(PasienModel pasien, AppointmentModel appointment);
    String generateCode();
    List<AppointmentModel> getListAppointment();
    AppointmentModel getAppointmentByKode(String kode);

    List<AppointmentModel> getListAppointmentByDokter(String dokter);


    ResponseEntity<?> createAppointment(AppointmentDTO appointmentDTO, Authentication authentication);

    List<AppointmentModel> getListAppointmentByPasien(String pasienModel);

    AppointmentModel findById(String kode);
}
