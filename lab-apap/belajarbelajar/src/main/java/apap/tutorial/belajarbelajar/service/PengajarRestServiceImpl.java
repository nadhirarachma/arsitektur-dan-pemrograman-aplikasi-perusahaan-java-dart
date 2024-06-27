package apap.tutorial.belajarbelajar.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.tutorial.belajarbelajar.model.CourseModel;
import apap.tutorial.belajarbelajar.model.PengajarModel;
import apap.tutorial.belajarbelajar.repository.PengajarDb;
import apap.tutorial.belajarbelajar.rest.Setting;

import org.springframework.web.reactive.function.client.WebClient;
import java.util.HashMap;

@Service
@Transactional
public class PengajarRestServiceImpl implements PengajarRestService {

    @Autowired
    private PengajarDb pengajarDb;

    private final WebClient webClient;

    @Override
    public PengajarModel createPengajar(PengajarModel pengajar)  {
        return pengajarDb.save(pengajar);
    }

    @Override
    public List<PengajarModel> retrieveListPengajar() {
        return pengajarDb.findAll();
    }

    @Override
    public PengajarModel getPengajarNoPengajar(Long noPengajar) {
        Optional<PengajarModel> pengajar = pengajarDb.findPengajarByNoPengajar(noPengajar);
        if (pengajar.isPresent()) {
            return pengajar.get();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public PengajarModel updatePengajar(Long noPengajar, PengajarModel pengajarUpdate) {
        PengajarModel pengajar = getPengajarNoPengajar(noPengajar);
        CourseModel course = pengajar.getCourse();

        if (isClosed(course.getTanggalDimulai(), course.getTanggalBerakhir())) {
            pengajar.setNamaPengajar(pengajarUpdate.getNamaPengajar());
            pengajar.setIsPengajarUniversitas(pengajarUpdate.getIsPengajarUniversitas());
            return pengajarDb.save(pengajar);    
        }
        else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public void deletePengajar(Long noPengajar) {
        PengajarModel pengajar = getPengajarNoPengajar(noPengajar);
        CourseModel course = pengajar.getCourse();
        if (isClosed(course.getTanggalDimulai(), course.getTanggalBerakhir())) {
            pengajarDb.delete(pengajar);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public boolean isClosed(LocalDateTime tanggalDimulai, LocalDateTime tanggalBerakhir) {
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(tanggalDimulai) || now.isAfter(tanggalBerakhir)){
            return true;
        }
            return false;
    }

    public PengajarRestServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient= webClientBuilder.baseUrl(Setting.genderUrl).build();
    }

    @Override
    public PengajarModel getGender(Long noPengajar) {
        PengajarModel pengajar = getPengajarNoPengajar(noPengajar);
        String namaPengajar = pengajar.getNamaPengajar();
        String namaDepan = namaPengajar;
        HashMap<String, String> result = new HashMap<String, String>();

        CourseModel course = pengajar.getCourse();
        if (isClosed(course.getTanggalDimulai(), course.getTanggalBerakhir())) {
            if (namaPengajar.contains(" ")) {
                namaDepan = namaPengajar.substring(0, namaPengajar.indexOf(' '));
            }
    
            result = this.webClient.get().uri("?name=" + namaDepan).retrieve().bodyToMono(HashMap.class).block();
            if (result.get("gender").equals("female")) {
                pengajar.setJenisKelamin(true);
            }
            else {
                pengajar.setJenisKelamin(false);
            }
            
            pengajarDb.save(pengajar);
            return pengajar;
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
