package apap.ta.rumahsehat.service;

import org.springframework.stereotype.Service;

import apap.ta.rumahsehat.model.AppointmentModel;
import apap.ta.rumahsehat.model.PasienModel;
import apap.ta.rumahsehat.model.TagihanModel;
import apap.ta.rumahsehat.repository.TagihanDb;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

@Service
@Transactional
public class TagihanServiceImpl implements TagihanService {
    @Autowired
    TagihanDb tagihanDb;

    @Override
    public TagihanModel addTagihan(TagihanModel tagihan) {
        return tagihanDb.save(tagihan);
    }

    @Override
    public List<TagihanModel> getListTagihan(PasienModel pasien) {
        List<TagihanModel> list = new ArrayList<>();
        for (AppointmentModel appo : pasien.getAppointment()) {
            list.add(appo.getTagihan());
        }
        return list;
    }

}
