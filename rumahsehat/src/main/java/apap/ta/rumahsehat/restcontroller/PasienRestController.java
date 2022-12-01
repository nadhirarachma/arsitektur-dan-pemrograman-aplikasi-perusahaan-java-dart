package apap.ta.rumahsehat.restcontroller;

import apap.ta.rumahsehat.model.PasienModel;
import apap.ta.rumahsehat.service.PasienRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/")
public class PasienRestController {
    @Autowired
    private PasienRestService pasienRestService;

    @PostMapping(value= "/pasien/add")
    public PasienModel addPasien(@Valid @RequestBody PasienModel pasien, BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Registrasi Belum Berhasil, Mohon Dicoba Kembali."
            );
        } else {
            return pasienRestService.addPasien(pasien);
        }
    }
}
