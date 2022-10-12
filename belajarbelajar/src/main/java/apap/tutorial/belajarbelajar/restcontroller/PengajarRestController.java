package apap.tutorial.belajarbelajar.restcontroller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import apap.tutorial.belajarbelajar.model.PengajarModel;
import apap.tutorial.belajarbelajar.service.PengajarRestService;

@RestController
@RequestMapping("/api/v1")
public class PengajarRestController {
    @Autowired
    private PengajarRestService pengajarRestService;

    //Add
    @PostMapping(value= "/pengajar")
    private PengajarModel createPengajar(@Valid @RequestBody PengajarModel pengajar, BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Request body has invalid type or missing field."
            );
        } else {
            return pengajarRestService.createPengajar(pengajar);
        }
    }

    //Retrieve
    @GetMapping(value = "/pengajar/{noPengajar}")
    private PengajarModel retrievePengajar(@PathVariable("noPengajar") String noPengajar) {
        try {
            Long nomorPengajar = Long.valueOf(noPengajar);
            return pengajarRestService.getPengajarNoPengajar(nomorPengajar);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Pengajar with number " + noPengajar + " not found"
            );
        }
    }

    //Delete
    @DeleteMapping(value= "/pengajar/{noPengajar}")
    private ResponseEntity deletePengajar(@PathVariable("noPengajar") String noPengajar){
        try {
            Long nomorPengajar = Long.valueOf(noPengajar);
            pengajarRestService.deletePengajar(nomorPengajar);
            return ResponseEntity.ok("Pengajar with number " + noPengajar + " has been deleted");
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Pengajar with number " + noPengajar + " not found"
            );
        } catch (UnsupportedOperationException e) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Course is still open"
            );
        }
    }

    //Update
    @PutMapping(value= "/pengajar/{noPengajar}")
    private PengajarModel updatePengajar(@PathVariable("noPengajar") String noPengajar, @RequestBody PengajarModel pengajar) {
        try {
            Long nomorPengajar = Long.valueOf(noPengajar);
            return pengajarRestService.updatePengajar(nomorPengajar, pengajar);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Pengajar with number " + noPengajar + " not found"
            );
        }
    }

    //Retrieve List All
    @GetMapping(value = "/list-pengajar")
    private List<PengajarModel> retrieveListPengajar() {
        return pengajarRestService.retrieveListPengajar();
    }

     //Retrieve
     @GetMapping(value = "/pengajar/jenis-kelamin/{noPengajar}")
     private PengajarModel retrievePengajarGender(@PathVariable("noPengajar") String noPengajar) {
        try {
            Long nomorPengajar = Long.valueOf(noPengajar);
            return pengajarRestService.getGender(nomorPengajar);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Pengajar with number " + noPengajar + " not found"
            );
        } catch (UnsupportedOperationException e) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Course is still open"
            );
        }
     }
}