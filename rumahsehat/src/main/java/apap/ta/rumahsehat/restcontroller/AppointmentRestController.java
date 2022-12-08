package apap.ta.rumahsehat.restcontroller;

import apap.ta.rumahsehat.payload.AppointmentDTO;
import apap.ta.rumahsehat.service.AppointmentRestService;
import apap.ta.rumahsehat.service.AppointmentService;
import apap.ta.rumahsehat.service.DokterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/")
public class AppointmentRestController {
    @Autowired
    private AppointmentRestService appointmentRestService;

    @Autowired
    private DokterService dokterService;

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/get-dokter")
    public List<?> getAllDokter(){
        if(dokterService.getListDokter().size()==0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tidak Ada Dokter Tersedia");
        }else {
            return dokterService.getListDokter();
        }
    }

    @PostMapping("/post-appointment")
    public ResponseEntity<?> postAppointment(@Valid @RequestBody
                                             AppointmentDTO appointmentDTO, Authentication authentication){
        try{
            return appointmentService.createAppointment(appointmentDTO, authentication);
        }catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,"Appoinment Tidak Berhasil Dibuat"
            );
        }
        //return null;
    }
}
