package apap.tutorial.belajarbelajar.service;
import java.util.List;
import apap.tutorial.belajarbelajar.model.PengajarModel;

public interface PengajarRestService {
    PengajarModel createPengajar(PengajarModel pengajar);
    List<PengajarModel> retrieveListPengajar();
    PengajarModel getPengajarNoPengajar(Long noPengajar);
    PengajarModel updatePengajar(Long noPengajar, PengajarModel pengajarUpdate);
    void deletePengajar(Long noPengajar);
    PengajarModel getGender(Long noPengajar);
}