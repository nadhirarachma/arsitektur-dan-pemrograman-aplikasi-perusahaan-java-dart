package apap.ta.rumahsehat.payload;

import java.util.List;

import apap.ta.rumahsehat.model.TagihanModel;
import lombok.Data;

@Data
public class ListTagihanDTO {
	private String nama;
	private List<TagihanModel> listTagihan;
}
