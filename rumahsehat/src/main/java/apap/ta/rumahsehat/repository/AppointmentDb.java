package apap.ta.rumahsehat.repository;

import apap.ta.rumahsehat.model.AppointmentModel;
import apap.ta.rumahsehat.model.DokterModel;
<<<<<<< rumahsehat/src/main/java/apap/ta/rumahsehat/repository/AppointmentDb.java
import apap.ta.rumahsehat.model.PasienModel;
=======
>>>>>>> rumahsehat/src/main/java/apap/ta/rumahsehat/repository/AppointmentDb.java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentDb extends JpaRepository<AppointmentModel, String> {
    List<AppointmentModel> findAppointmentModelsByDokter(DokterModel dokterModel);

    List<AppointmentModel> findAllByDokter(DokterModel dokterModel);

//    List<AppointmentModel> findAllOrderByKodeDesc();

    List<AppointmentModel> findAppointmentModelsByPasien(PasienModel pasienModel);
    Optional<AppointmentModel> findByKode(String kode);

//    List<AppointmentModel> findAllOrderByKodeDesc();
}
