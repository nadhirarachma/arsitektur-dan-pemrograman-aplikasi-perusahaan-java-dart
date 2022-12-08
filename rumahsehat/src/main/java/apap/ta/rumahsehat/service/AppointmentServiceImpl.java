package apap.ta.rumahsehat.service;

import apap.ta.rumahsehat.model.AppointmentModel;
import apap.ta.rumahsehat.payload.AppointmentDTO;
import apap.ta.rumahsehat.repository.AppointmentDb;
import apap.ta.rumahsehat.repository.DokterDb;
import apap.ta.rumahsehat.model.PasienModel;
import apap.ta.rumahsehat.repository.PasienDb;
import apap.ta.rumahsehat.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    UserDb userDb;

    @Autowired
    DokterDb dokterDb;

    @Autowired
    PasienDb pasienDb;

    @Autowired
    AppointmentDb appointmentDb;

    private List<AppointmentModel> listAppointment;

    public AppointmentServiceImpl(){
        listAppointment = new ArrayList<>();
    }

    @Override
    public void addAppointment(AppointmentModel appointment){
        appointmentDb.save(appointment);
    }
    // @Override
    // public AppointmentModel addAppointment(PasienModel pasien, AppointmentModel appointment){
    //     appointment.setIsDone(false);
    //     appointment.setPasien(pasien);

    //     return appointmentDb.save(appointment);
    // }


    @Override
    public String generateCode() {
        String awal = "APT-";
        List<AppointmentModel> appointmentModelList = appointmentDb.findAll();
        String[] kodesplit = appointmentModelList.get(appointmentModelList.size()-1).getKode().split("-");
        int kodeAkhir = Integer.parseInt(kodesplit[1]);
        awal += kodeAkhir+1;
        return awal;
    }


    @Override
    public List<AppointmentModel> getListAppointment(){
        return appointmentDb.findAll();
    }

    @Override
    public List<AppointmentModel> getListAppointmentByDokter(String dokter) {
        return appointmentDb.findAppointmentModelsByDokter(dokterDb.findByUsername(dokter));
    }

    @Override
    public ResponseEntity<?> createAppointment(AppointmentDTO appointmentDTO, Authentication authentication) {
        try{
            AppointmentModel appointmentModel = new AppointmentModel();
            if(checkAppointment(appointmentDTO.getUsername(),appointmentDTO.getTanggal())){
                appointmentModel.setKode(generateCode());
                appointmentModel.setIsDone(false);
                appointmentModel.setWaktuAwal(appointmentDTO.getTanggal());
                appointmentModel.setDokter(dokterDb.findByUsername(appointmentDTO.getUsername()));
//                appointmentModel.setPasien(pasienDb.findByUsername(authentication.getName()));
                appointmentModel.setPasien(null);
                appointmentDb.save(appointmentModel);
                return ResponseEntity.ok().body("Appointment berhasi ditambahkan");
            }
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Appointment tidak berhasil dibuat, silahkan pilih tanggal lain"
            );
        }catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Appointment tidak berhasil dibuat, silahkan pilih tanggal lain"
            );
        }
    }

    public boolean checkAppointment(String username, LocalDateTime waktuAwal){
        List<AppointmentModel> listAppointment = appointmentDb.findAllByDokter(dokterDb.findByUsername(username));
        for (int i = 0; i < listAppointment.size() ; i++) {
            if(listAppointment.get(i).getWaktuAwal().equals(waktuAwal)){
                return false;
            }
        }
        return true;
    }

    @Override
    public AppointmentModel getAppointmentByKode(String kode){
        Optional<AppointmentModel> appointment = appointmentDb.findByKode(kode);
        if (appointment.isPresent()) {
            return appointment.get();            
        } else return null;
    }
}
