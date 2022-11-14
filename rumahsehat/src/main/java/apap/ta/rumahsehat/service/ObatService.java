package apap.ta.rumahsehat.service;

import apap.ta.rumahsehat.model.ObatModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ObatService {
    List<ObatModel> getSortedListObat();


}
