package apap.ta.rumahsehat.service;

import apap.ta.rumahsehat.payload.AppointmentDTO;
import apap.ta.rumahsehat.repository.AppointmentDb;
import apap.ta.rumahsehat.payload.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class AppointmentRestServiceImpl implements AppointmentRestService{
    private final WebClient webClient;

    @Autowired
    private DokterService dokterService;

    @Autowired
    private AppointmentService appointmentService;

    public AppointmentRestServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient = webClientBuilder.baseUrl(Setting.appointmentUrl).build();
    }

    @Autowired
    private AppointmentDb appointmentDb;

    @Override
    public List<?> getAllDokter(){
        if(dokterService.getListDokter().size()==0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tidak Ada Dokter Tersedia");
        }else {
            return dokterService.getListDokter();
        }
    }

    @Override
    public ResponseEntity<?> postAppointment(@Valid @RequestBody
                                             AppointmentDTO appointmentDTO, Authentication authentication){
        try{
            return appointmentService.createAppointment(appointmentDTO, authentication);
        }catch (NoSuchElementException e){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,"Appoinment Tidak Berhasil Dibuat"
            );
        }
//        return null;
    }
}
