package apap.ta.rumahsehat.restcontroller;

import apap.ta.rumahsehat.model.AppointmentModel;
import apap.ta.rumahsehat.payload.AppointmentDTO;
import apap.ta.rumahsehat.payload.AppointmentGetDetailDTO;
import apap.ta.rumahsehat.service.AppointmentService;
import apap.ta.rumahsehat.service.DokterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequestMapping("/api/v1/")
public class AppointmentRestController {

    @Autowired
    private DokterService dokterService;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/get-dokter")
    public List<?> getAllDokter(){
        if(dokterService.getListDokter().isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tidak Ada Dokter Tersedia");
        }else {
            return dokterService.getListDokter();
        }
    }

    @PostMapping("/post-appointment")
    public ResponseEntity<?> postAppointment(@Valid @RequestBody
                                             AppointmentDTO appointmentDTO, Authentication authentication){
        log.info("Received message request appointment end point for user");
        try{
            log.info("Appointment successfull made end point");
            return appointmentService.createAppointment(appointmentDTO, authentication);
        }catch (NoSuchElementException e){
            log.warn("create appointment error");
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,"Appoinment Tidak Berhasil Dibuat"
            );
        }
    }

    @GetMapping("/get-appointment-pasien")
    public List<?> getAppointmentByPasien(Authentication authentication){
        log.info("Received message request list appointment for pasien");
        if(appointmentService.getListAppointmentByPasien(authentication.getName()).isEmpty()){
            log.warn("pasien don't have appointment");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tidak Ada Appointment Tersedia");
        }else {
            log.info("List Appointment successfull show");
            return appointmentService.getListAppointmentByPasien(authentication.getName());
        }
    }

    @GetMapping("/get-appointment-detail-pasien")
    public AppointmentModel getAppointmentDetailPasien(@RequestBody AppointmentGetDetailDTO appointmentGetDetailDTO ){
        log.info("Detail Appointment successfull show");
        return appointmentService.findById(appointmentGetDetailDTO.getKode());
    }
}
