package apap.ta.rumahsehat.repository;

import apap.ta.rumahsehat.model.AppointmentModel;
import apap.ta.rumahsehat.model.DokterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentDb extends JpaRepository<AppointmentModel, String> {
    List<AppointmentModel> findAppointmentModelsByDokter(DokterModel dokterModel);

    List<AppointmentModel> findAllByDokter(DokterModel dokterModel);
    Optional<AppointmentModel> findByKode(String kode);

//    List<AppointmentModel> findAllOrderByKodeDesc();
}
