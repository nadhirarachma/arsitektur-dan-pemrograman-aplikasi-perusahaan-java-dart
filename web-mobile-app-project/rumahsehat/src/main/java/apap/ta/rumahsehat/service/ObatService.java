package apap.ta.rumahsehat.service;

import apap.ta.rumahsehat.model.ObatModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ObatService {
    ObatModel updateStokObat(ObatModel obat);
    List<ObatModel> getSortedListObat();
    ObatModel getObatbyId(String id);

}
