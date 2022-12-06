package apap.ta.rumahsehat.service;

import org.springframework.stereotype.Service;
import apap.ta.rumahsehat.model.TagihanModel;
import apap.ta.rumahsehat.repository.TagihanDb;
import org.springframework.beans.factory.annotation.Autowired;

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

}
