package apap.ta.rumahsehat.service;

import java.util.List;

import apap.ta.rumahsehat.model.PasienModel;
import apap.ta.rumahsehat.model.TagihanModel;

public interface TagihanService {
    TagihanModel addTagihan(TagihanModel tagihan);
    List<TagihanModel> getListTagihan(PasienModel pasien);
    TagihanModel getTagihan(String kode);
}
