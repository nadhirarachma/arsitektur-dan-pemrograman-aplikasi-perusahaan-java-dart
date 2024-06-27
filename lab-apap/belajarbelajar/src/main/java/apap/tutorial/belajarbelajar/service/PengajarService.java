package apap.tutorial.belajarbelajar.service;

import apap.tutorial.belajarbelajar.model.PengajarModel;

public interface PengajarService {
    void addPengajar(PengajarModel pengajar);

    PengajarModel updatePengajar(PengajarModel pengajar);

    PengajarModel getPengajarByNoPengajarQuery(Long noPengajar);

    PengajarModel deletePengajar(PengajarModel pengajar);
}
